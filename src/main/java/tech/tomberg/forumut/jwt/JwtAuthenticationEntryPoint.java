package tech.tomberg.forumut.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tech.tomberg.forumut.dto.response.CustomResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint,
        Serializable {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse
            response,
                         AuthenticationException authException) throws IOException {
        HashMap<Object, Object> data = new HashMap<>(){};
        data.put("status", HttpStatus.UNAUTHORIZED.toString());
        CustomResponse re = CustomResponse.builder().message("Authentication failed").error(true).data(data).build();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        OutputStream responseStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(responseStream, re);
        responseStream.flush();
    }
}