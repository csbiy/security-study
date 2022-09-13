package io.security.corespringsecurity.security.common;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by P-161 at : 2022-09-13
 *
 * 여기에 FormAuthenticationDetailsSource 클래스에 대한 설명을 기술해주세요
 *
 * @author P-161
 * @version 1.0
 * @since 1.0
 */
@Component
public class FormAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest , WebAuthenticationDetails> {

    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest request) {
        return new FormWebAuthenticationDetails(request);
    }

}