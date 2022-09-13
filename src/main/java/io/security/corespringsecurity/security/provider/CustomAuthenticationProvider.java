package io.security.corespringsecurity.security.provider;

import io.security.corespringsecurity.security.common.FormWebAuthenticationDetails;
import io.security.corespringsecurity.security.service.AccountContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by P-161 at : 2022-09-08
 *
 * 여기에 CustomAuthenticationProvider 클래스에 대한 설명을 기술해주세요
 *
 * @author P-161
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        AccountContext accountContext = (AccountContext) userDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password,accountContext.getPassword())){
            //패스워드가 일치하지 않는 경우
            throw new BadCredentialsException("password not matched");
        }
        FormWebAuthenticationDetails details =  (FormWebAuthenticationDetails) authentication.getDetails();
        String secretKey = details.getSecretKey();
        System.out.println("secretKey = " + secretKey);
        if (StringUtils.isEmpty(secretKey) || !"secret".equals(secretKey)) {
            throw new InsufficientAuthenticationException("secret key not matched");
        }

        return new UsernamePasswordAuthenticationToken(accountContext.getAccount(),null,accountContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }



}