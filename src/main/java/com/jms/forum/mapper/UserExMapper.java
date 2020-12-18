package com.jms.forum.mapper;

import com.jms.forum.entity.User;

public interface UserExMapper {

    User selectByUsername(String username);

    User selectByToken(String token);
}