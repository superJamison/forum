package com.jms.forum.controller;

import com.jms.forum.dto.PageResult;
import com.jms.forum.service.QuestionService;
import com.jms.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/15 17:18
 */
@RestController
public class IndexController {

    @Autowired
    private QuestionService questionService;


    @GetMapping("/")
    public String index(){

        return "index";
    }

    @GetMapping("/toLoginPage")
    public String toLoginPage(){
        return "login";
    }

    @GetMapping("/toRegisterPage")
    public String register(){
        return "register";
    }

    @GetMapping("/getIndexPage")
    public PageResult getIndexPage(Integer page, Integer limit, String searchContent){
        return questionService.getPage(page, limit, searchContent);
    }

    //获取我的问题，根据用户id获取问题
    @GetMapping("/getMyProblemPage")
    public PageResult getMyProblemPage(Integer page, Integer limit, Integer id){
        return questionService.getMyProblemPage(page, limit, id);
    }

    //获取我的问题，根据用户id获取问题
    @GetMapping("/getMyNewReplyPage")
    public PageResult getMyNewReplyPage(Integer page, Integer limit, Integer id){
        return questionService.getMyNewReplyPage(page, limit, id);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse responses){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", "");
        cookie.setMaxAge(0);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        responses.addCookie(cookie);
        return "redirect:/";
    }
}
