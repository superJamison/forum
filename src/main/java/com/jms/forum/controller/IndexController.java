package com.jms.forum.controller;

import com.jms.forum.dto.QuestionDto;
import com.jms.forum.entity.Question;
import com.jms.forum.entity.User;
import com.jms.forum.mapper.QuestionDao;
import com.jms.forum.service.QuestionService;
import com.jms.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/15 17:18
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user = userService.selectByToken(token);
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                }
            }
        }

        List<QuestionDto> list = questionService.list();
        model.addAttribute("questions", list);
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
