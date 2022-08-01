package ru.project.storage.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.project.storage.service.UserDetailsServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JWTFilterTest {

    @Autowired
    JWTUtil jwtUtil;
    @MockBean
    FilterChain filterChain;

    @MockBean
    UserDetailsServiceImpl userDetailsService;

    @Test
    void doFilterInternal() throws ServletException, IOException {
        JWTFilter jwtFilter = new JWTFilter(userDetailsService,jwtUtil);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getParameter("firstName")).thenReturn("Mockito");
        when(request.getParameter("lastName")).thenReturn("Test");

        jwtFilter.doFilterInternal( request, response, filterChain);
    }
}