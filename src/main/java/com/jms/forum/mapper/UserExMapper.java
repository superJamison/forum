package com.jms.forum.mapper;

import com.jms.forum.entity.User;

import java.util.List;

public interface UserExMapper {

    User selectByUsername(String username);

    User selectByToken(String token);

    List<String> selectAllUsername();
}
