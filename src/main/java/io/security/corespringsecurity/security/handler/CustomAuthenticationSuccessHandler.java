package io.security.corespringsecurity.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by P-161 at : 2022-09-13
 *
 * 여기에 CustomAuthenticationSuccessHandler 클래스에 대한 설명을 기술해주세요
 *
 * @author P-161
 * @version 1.0
 * @since 1.0
 */
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        setDefaultTargetUrl("/");
        /* 사용자가 로그인 이전에 요청했던 정보 */
        SavedRequest cachedRequest = requestCache.getRequest(request, response);
        if (cachedRequest != null) {
            String targetUrl = cachedRequest.getRedirectUrl();
            redirectStrategy.sendRedirect(request, response, targetUrl);
            return;
        }
        redirectStrategy.sendRedirect(request,response,getDefaultTargetUrl());

    }
}