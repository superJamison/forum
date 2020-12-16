package com.jms.forum.service;

import com.jms.forum.entity.User;

/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/15 21:42
 */
public interface UserService {

    User login(String username, String password);
}
