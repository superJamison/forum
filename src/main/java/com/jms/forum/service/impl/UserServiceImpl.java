package com.jms.forum.service.impl;

import com.jms.forum.dto.UserDto;
import com.jms.forum.entity.User;
import com.jms.forum.entity.UserExample;
import com.jms.forum.mapper.UserExMapper;
import com.jms.forum.mapper.UserMapper;
import com.jms.forum.service.UserService;
import com.jms.forum.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        UserDto userDto = new UserDto();
        User user = userExMapper.selectByUsername(username);
        if (user == null || "".equals(user)){
            userDto.setLogin(false);
            userDto.setMessage("用户名不存在！");
            return userDto;
        }
        String password1 = MD5Utils.inputPassToDbPass(password, username);

        if (user.getPassword().equals(password1)){
            userDto.setLogin(true);
            user.setPassword("");
            userDto.setUser(user);
            return userDto;
        }
        userDto.setLogin(false);
        userDto.setMessage("用户名或密码错误！");
        return userDto;
    }

    @Override
    public User selectByToken(String token) {
        return userExMapper.selectByToken(token);
    }

    @Override
    public void updateAvatar(Integer userId, String avatar_url) {
        User user = new User();
        user.setId(userId);
        user.setAvatarUrl(avatar_url);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void add(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    public List<String> getAllUser() {
        return userExMapper.selectAllUsername();
    }


}
