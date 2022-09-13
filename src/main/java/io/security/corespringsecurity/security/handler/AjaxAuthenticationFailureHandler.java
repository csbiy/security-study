package io.security.corespringsecurity.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by P-161 at : 2022-09-13
 *
 * 여기에 AjaxAuthenticationFailureHandler 클래스에 대한 설명을 기술해주세요
 *
 * @author P-161
 * @version 1.0
 * @since 1.0
 */
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper;

    public AjaxAuthenticationFailureHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res,
                                        AuthenticationException ex) throws IOException, ServletException {
        String errMsg = "Invalid Username or Password";
        res.setStatus(HttpStatus.UNAUTHORIZED.value());
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        if (ex instanceof BadCredentialsException){
            errMsg = "Invalid Password";
        } else if (ex instanceof InsufficientAuthenticationException) {
            errMsg = "Invalid Secret Key";
        } else if (ex instanceof UsernameNotFoundException){
            errMsg  = "Invalid Username";
        }
        objectMapper.writeValue(res.getWriter(),errMsg);
    }
}