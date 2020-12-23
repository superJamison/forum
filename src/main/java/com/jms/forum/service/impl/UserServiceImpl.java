package com.jms.forum.service.impl;

import com.jms.forum.dto.UserDto;
import com.jms.forum.entity.User;
import com.jms.forum.mapper.UserExMapper;
import com.jms.forum.mapper.UserMapper;
import com.jms.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/15 21:43
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserExMapper userExMapper;


    public UserDto login(String username, String password){
        User user = userExMapper.selectByUsername(username);
        UserDto userDto = new UserDto();
        if (user.getPassword().equals(password)){
            userDto.setLogin(true);
            user.setPassword("");
            userDto.setUser(user);
            return userDto;
        }
        userDto.setLogin(false);
        return userDto;
    }

    @Override
    public User selectByToken(String token) {
        return userExMapper.selectByToken(token);
    }



}
