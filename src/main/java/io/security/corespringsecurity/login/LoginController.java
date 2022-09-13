package io.security.corespringsecurity.login;

import io.security.corespringsecurity.domain.Account;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by P-161 at : 2022-09-08
 *
 * 여기에 LoginController 클래스에 대한 설명을 기술해주세요
 *
 * @author P-161
 * @version 1.0
 * @since 1.0
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(required = false) Boolean error,
                        @RequestParam(required = false) String exception,
                        Model model){
        model.addAttribute("error",error);
        model.addAttribute("exception",exception);
        return "user/login/login";
    }

    /***
     * Logout 방법
     * 1. form tag + POST METHOD ->
     * 2. a tag  +  GET METHOD -> SecurityContextLogoutHandler
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request , HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }
        return "redirect:/login";
    }

    @GetMapping("/denied")
    public String accessDenied(@RequestParam(required = false) String exception,
                               Model model){
        Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username",account.getUsername());
        model.addAttribute("exception",exception);
        return "user/login/denied";
    }

}