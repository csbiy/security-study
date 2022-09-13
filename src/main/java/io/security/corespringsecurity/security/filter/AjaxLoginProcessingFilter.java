package io.security.corespringsecurity.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.security.corespringsecurity.domain.AccountDto;
import io.security.corespringsecurity.security.token.AjaxAuthenticationToken;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by P-161 at : 2022-09-13
 *
 * 여기에 AjaxLoginProcessingFilter 클래스에 대한 설명을 기술해주세요
 *
 * @author P-161
 * @version 1.0
 * @since 1.0
 */
public class AjaxLoginProcessingFilter  extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper;

    public AjaxLoginProcessingFilter(ObjectMapper objectMapper) {
        super(new AntPathRequestMatcher("/api/login" , HttpMethod.POST.name()));
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException, IOException {
        if (!isAjax(request)){
            throw new IllegalStateException("Authentication is not supported");
        }
        AccountDto accountDto = objectMapper.readValue(request.getReader(), AccountDto.class);
        if (StringUtils.isEmpty(accountDto.getUsername()) || StringUtils.isEmpty(accountDto.getPassword())){
            throw new IllegalArgumentException("Username or Password is empty");
        }

        AjaxAuthenticationToken token = new AjaxAuthenticationToken(accountDto.getUsername(), accountDto.getPassword());
        return super.getAuthenticationManager().authenticate(token);
    }

    private boolean isAjax(HttpServletRequest request) {
        return request.getHeader("X-Requested-With").equals("XMLHttpRequest");
    }


}