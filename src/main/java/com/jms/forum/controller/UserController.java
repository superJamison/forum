package com.jms.forum.controller;

import com.jms.forum.entity.User;
import com.jms.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.awt.geom.AreaOp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/15 21:36
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(String username, String password, HttpServletRequest request){
        User user = userService.login(username, password);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        return "redirect:/";
    }
}
