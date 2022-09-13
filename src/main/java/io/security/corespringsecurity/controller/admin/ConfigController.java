package io.security.corespringsecurity.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by P-161 at : 2022-09-07
 *
 * 여기에 ConfigController 클래스에 대한 설명을 기술해주세요
 *
 * @author P-161
 * @version 1.0
 * @since 1.0
 */
@Controller
public class ConfigController {

    @GetMapping("/config")
    public String config(){
        return "admin/config";
    }
}