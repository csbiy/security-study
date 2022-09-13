package io.security.corespringsecurity.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by P-161 at : 2022-09-07
 *
 * 여기에 Account 클래스에 대한 설명을 기술해주세요
 *
 * @author P-161
 * @version 1.0
 * @since 1.0
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {


    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    private String email;

    private String age;

    private String role;




}