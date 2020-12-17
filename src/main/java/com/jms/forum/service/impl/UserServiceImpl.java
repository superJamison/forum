package com.jms.forum.service.impl;

import com.github.pagehelper.PageHelper;
import com.jms.forum.dto.PageResult;
import com.jms.forum.entity.Question;
import com.jms.forum.mapper.UserDao;
import com.jms.forum.entity.User;
import com.jms.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/15 21:43
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public User login(String username, String password){
        User user = userDao.selectByUsername(username);
        if (user.getPassword().equals(password)){
            return user;
        }
        return null;
    }

    @Override
    public User selectByToken(String token) {
        return userDao.selectByToken(token);
    }



}
