package tech.tomberg.forumut.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint,
        Serializable {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse
            response,
                         AuthenticationException authException) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}