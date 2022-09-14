package io.security.corespringsecurity.security.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by P-161 at : 2022-09-14
 *
 * 여기에 AjaxAccessDeniedHandler 클래스에 대한 설명을 기술해주세요
 *
 * @author P-161
 * @version 1.0
 * @since 1.0
 */
public class AjaxAccessDeniedHandler implements AccessDeniedHandler {

    /***
     * 인증예외발생시 처리로직
     */
    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res,
                       AccessDeniedException ex) throws IOException, ServletException {
        res.sendError(HttpServletResponse.SC_FORBIDDEN , "Access is denied");
    }
}