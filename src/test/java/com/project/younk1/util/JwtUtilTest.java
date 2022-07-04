package com.project.younk1.util;

import io.jsonwebtoken.Claims;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

class JwtUtilTest {

    private static final String SECRET = "12345678901234567890123456789012";

    private JwtUtil jwtUtil;

    @BeforeEach
    public void setUp() {
        jwtUtil = new JwtUtil(SECRET);
    }


    @Test
    public void createToken() {

        String token = jwtUtil.createToken(1004L, "Jone");
        System.out.println(token);
        assertThat(token, containsString("."));
    }

    @Test
    public void getClaims() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJKb25lIn0.6cSuWCe_vw4urwglRZvr6milHYtv2XvlVyGEBscmITA";

        Claims claims = jwtUtil.getClaims(token);

        assertThat(claims.get("userId", Long.class), equalTo(1004L));
        assertThat(claims.get("name"), equalTo("Jone"));
    }

}