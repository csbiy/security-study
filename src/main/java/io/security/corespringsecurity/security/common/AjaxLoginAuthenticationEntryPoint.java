package io.security.corespringsecurity.security.common;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by P-161 at : 2022-09-14
 *
 * 여기에 AjaxLoginAuthenticationEntryPoint 클래스에 대한 설명을 기술해주세요
 *
 * @author P-161
 * @version 1.0
 * @since 1.0
 */
public class AjaxLoginAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /***
     * 인증받지 않은 사용자
     * @param req
     * @param res
     * @param ex
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res,
                         AuthenticationException ex) throws IOException, ServletException {

        res.sendError(HttpServletResponse.SC_UNAUTHORIZED,"unAuthorized");

    }


}