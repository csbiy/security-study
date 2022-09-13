package io.security.corespringsecurity.controller.user;

import io.security.corespringsecurity.domain.Account;
import io.security.corespringsecurity.domain.AccountDto;
import io.security.corespringsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by P-161 at : 2022-09-07
 *
 * 여기에 UserController 클래스에 대한 설명을 기술해주세요
 *
 * @author P-161
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/mypage")
    public String myPage(){
        return "user/mypage";
    }

    @GetMapping("/users")
    public String createUser(){
        return "user/login/register";
    }

    @PostMapping("/users")
    public String createUser(AccountDto accountDto){
        Account account = Account.builder()
                               .age(accountDto.getAge())
                               .password(accountDto.getPassword())
                               .username(accountDto.getUsername())
                               .email(accountDto.getEmail())
                               .role(accountDto.getRole())
                               .build();
        userService.createUser(account);
        return "redirect:/";
    }
}