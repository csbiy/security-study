package io.security.corespringsecurity.security.provider;

import io.security.corespringsecurity.security.service.AccountContext;
import io.security.corespringsecurity.security.token.AjaxAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by P-161 at : 2022-09-13
 *
 * 여기에 AjaxAuthenticationProvider 클래스에 대한 설명을 기술해주세요
 *
 * @author P-161
 * @version 1.0
 * @since 1.0
 */
@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public AjaxAuthenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userId = authentication.getName();
        Object credentials = authentication.getCredentials();
        AccountContext context = (AccountContext) userDetailsService.loadUserByUsername(userId);
        if(passwordEncoder.matches((String)credentials,context.getPassword())){
            throw new BadCredentialsException("password not matched");
        }
        return new AjaxAuthenticationToken(context.getAccount(),null,context.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(AjaxAuthenticationToken.class);
    }
}