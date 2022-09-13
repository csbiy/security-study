package io.security.corespringsecurity.security.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by P-161 at : 2022-09-13
 *
 * 여기에 CustomAccessDeniedHandler 클래스에 대한 설명을 기술해주세요
 *
 * @author P-161
 * @version 1.0
 * @since 1.0
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final String errorPage;

    public CustomAccessDeniedHandler(String errorPage) {
        this.errorPage = errorPage;
    }

    @Override
    /* 인가예외 발생시 로직 */
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                       AccessDeniedException accessDeniedException) throws IOException {
        String deniedUrl = errorPage + "?exception=" +  accessDeniedException.getMessage();
        httpServletResponse.sendRedirect(deniedUrl);
    }

}