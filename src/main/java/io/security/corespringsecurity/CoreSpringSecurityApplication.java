package io.security.corespringsecurity;

import io.security.corespringsecurity.domain.Account;
import io.security.corespringsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CoreSpringSecurityApplication {

    @Autowired
    UserService userService;
    public static void main(String[] args) {
        SpringApplication.run(CoreSpringSecurityApplication.class, args);
    }

    @PostConstruct
    public void initUser(){
        userService.createUser(new Account("user","1234","test@email.com","12","USER"));
        userService.createUser(new Account("manager","1234","test@email.com","12","MANAGER"));
        userService.createUser(new Account("admin","1234","test@email.com","12","ADMIN"));

    }

}