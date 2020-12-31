package com.jms.forum.service;

import com.jms.forum.dto.PageResult;
import com.jms.forum.dto.UserDto;
import com.jms.forum.entity.User;

import java.util.List;

/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/15 21:42
 */
public interface UserService {

    UserDto login(String username, String password);

    User selectByToken(String token);

    void updateAvatar(Integer userId, String avatar_url);

    void add(User user);

    List<String> getAllUser();

    User getUserByToken(String token);
}
