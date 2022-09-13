package io.security.corespringsecurity.security.handler;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by P-161 at : 2022-09-13
 *
 * 여기에 CustiomAuthenticationFailureHandler 클래스에 대한 설명을 기술해주세요
 *
 * @author P-161
 * @version 1.0
 * @since 1.0
 */
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
 										AuthenticationException exception) throws IOException, ServletException {

	    String errorMessage = "Invalid Username or Password";
        if (exception instanceof BadCredentialsException){
            errorMessage = "Invalid Password";
        } else if (exception instanceof InsufficientAuthenticationException) {
            errorMessage = "Invalid Secret Key";
        } else if (exception instanceof UsernameNotFoundException){
            errorMessage  = "Invalid Username";
        }
        setDefaultFailureUrl("/login?error=true&exception="+exception.getMessage());
        super.onAuthenticationFailure(request,response,exception);
    }
}