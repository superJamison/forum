package com.jms.forum.controller;

import com.jms.forum.dto.UserDto;
import com.jms.forum.entity.User;
import com.jms.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/15 21:36
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UserDto login(User user){
        return userService.login(user.getUsername(), user.getPassword());

    }
}
