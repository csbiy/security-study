package io.security.corespringsecurity.security.service;

import io.security.corespringsecurity.domain.Account;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by P-161 at : 2022-09-08
 *
 * 여기에 AccountContext 클래스에 대한 설명을 기술해주세요
 *
 * @author P-161
 * @version 1.0
 * @since 1.0
 */
@Getter
public class AccountContext extends User {

    private final Account account;

    public AccountContext(Account account,
                          Collection<? extends GrantedAuthority> authorities) {
        super(account.getUsername(), account.getPassword(), authorities);
        this.account = account;
    }
}