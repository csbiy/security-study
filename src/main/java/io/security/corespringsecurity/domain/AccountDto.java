package io.security.corespringsecurity.domain;

import lombok.Data;

/**
 * Created by P-161 at : 2022-09-08
 *
 * 여기에 AccountDto 클래스에 대한 설명을 기술해주세요
 *
 * @author P-161
 * @version 1.0
 * @since 1.0
 */
@Data
public class AccountDto {

    private String username;

    private String password;

    private String email;

    private String age;

    private String role;
}