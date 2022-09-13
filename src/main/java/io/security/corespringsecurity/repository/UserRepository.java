package io.security.corespringsecurity.repository;

import io.security.corespringsecurity.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by P-161 at : 2022-09-08
 *
 * 여기에 UserRepository 클래스에 대한 설명을 기술해주세요
 *
 * @author P-161
 * @version 1.0
 * @since 1.0
 */
public interface UserRepository  extends JpaRepository<Account,Long> {

    Account findByUsername(String username);


}