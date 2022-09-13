package io.security.corespringsecurity.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.security.corespringsecurity.security.filter.AjaxLoginProcessingFilter;
import io.security.corespringsecurity.security.handler.AjaxAuthenticationFailureHandler;
import io.security.corespringsecurity.security.handler.AjaxAuthenticationSuccessHandler;
import io.security.corespringsecurity.security.provider.AjaxAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by P-161 at : 2022-09-13
 *
 * 여기에 AjaxSecurityConfig 클래스에 대한 설명을 기술해주세요
 *
 * @author P-161
 * @version 1.0
 * @since 1.0
 */
@Configuration
@Order(0)
public class AjaxSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(ajaxAuthenticationProvider());
    }
    @Bean
    public AuthenticationProvider ajaxAuthenticationProvider(){
        return new AjaxAuthenticationProvider(passwordEncoder,userDetailsService);
    }

    @Bean
    public AuthenticationSuccessHandler successHandler(){
        return new AjaxAuthenticationSuccessHandler(new ObjectMapper());
    }
    @Bean
    public AuthenticationFailureHandler failureHandler(){
        return new AjaxAuthenticationFailureHandler(new ObjectMapper());
    }
    @Bean
    public AbstractAuthenticationProcessingFilter ajaxLoginProcessingFilter() throws Exception {
        AjaxLoginProcessingFilter ajaxLoginProcessingFilter = new AjaxLoginProcessingFilter(new ObjectMapper());
        ajaxLoginProcessingFilter.setAuthenticationManager(super.authenticationManagerBean());
        ajaxLoginProcessingFilter.setAuthenticationSuccessHandler(successHandler());
        ajaxLoginProcessingFilter.setAuthenticationFailureHandler(failureHandler());
        return ajaxLoginProcessingFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/api/**")
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(ajaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class);

        http.csrf().disable();
    }
}