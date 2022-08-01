package ru.project.storage.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class JWTUtilTest {
    String name;
    JWTUtil jwtUtil;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        jwtUtil = new JWTUtil();
        name = "Nikolai";

        Field field = jwtUtil.getClass().getDeclaredField("secret");
        field.setAccessible(true);
        field.set(jwtUtil, "woop");
    }

    @Test
    void generateToken() {
        assertEquals(156, jwtUtil.generateToken(name).length());
    }

    @Test
    void validateTokenAndRetrieveSubject() {
        assertEquals(name, jwtUtil.validateTokenAndRetrieveSubject(jwtUtil.generateToken(name)));
    }
}