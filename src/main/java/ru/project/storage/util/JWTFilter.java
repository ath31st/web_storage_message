package ru.project.storage.util;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.project.storage.service.UserDetailsServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private final UserDetailsServiceImpl userDetailsService;
    private final JWTUtil jwtUtil;

    public JWTFilter(UserDetailsServiceImpl userDetailsService, JWTUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")){
            String jwt = authHeader.substring(7);
            if(jwt == null || jwt.isBlank()){
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }else {
                try{
                    String name = jwtUtil.validateTokenAndRetrieveSubject(jwt);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(name);
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(name, userDetails.getPassword(), userDetails.getAuthorities());
                    if(SecurityContextHolder.getContext().getAuthentication() == null){
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }

                }catch(JWTVerificationException exc){
                    StringBuilder sb = new StringBuilder();
                    sb.append("{ ");
                    sb.append("\"error\": \"Unauthorized\",");
                    sb.append("\"message\": \"Invalid Token\",");
                    sb.append("\"path\": \"")
                            .append(request.getRequestURL())
                            .append("\"");
                    sb.append("} ");
                    response.setContentType("application/json");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write(sb.toString());
                    return;
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
