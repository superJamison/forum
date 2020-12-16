package com.jms.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/15 17:18
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/toLoginPage")
    public String toLoginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

}
