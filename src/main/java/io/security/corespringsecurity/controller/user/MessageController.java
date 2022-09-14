package io.security.corespringsecurity.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by P-161 at : 2022-09-07
 *
 * 여기에 MessageController 클래스에 대한 설명을 기술해주세요
 *
 * @author P-161
 * @version 1.0
 * @since 1.0
 */
@Controller
public class MessageController {

    @GetMapping("/messages")
    public String mypage(){
        return "/user/messages";
    }

    @ResponseBody
    @GetMapping("/api/messages")
    public String apiMessage(){
        return "messages ok";
    }

}