package io.security.corespringsecurity.service;

import io.security.corespringsecurity.domain.Account;

/**
 * Created by P-161 at : 2022-09-08
 *
 * 여기에 UserService 인터페이스에 대한 설명을 기술해주세요
 *
 * @author P-161
 * @version 1.0
 * @since 1.0
 */
public interface UserService {

    void createUser(Account account);
}