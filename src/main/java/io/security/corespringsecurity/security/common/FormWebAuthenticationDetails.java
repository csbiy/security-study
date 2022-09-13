package io.security.corespringsecurity.security.common;

import lombok.Getter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by P-161 at : 2022-09-13
 *
 * 여기에 FormWebAuthenticationDetails 클래스에 대한 설명을 기술해주세요
 *
 * @author P-161
 * @version 1.0
 * @since 1.0
 */

@Getter
public class FormWebAuthenticationDetails extends WebAuthenticationDetails{

    // 사용자가 전달하는 추가적인 파라미터를 저장
    private String secretKey;

    public FormWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        this.secretKey = request.getParameter("secretKey");
    }
}