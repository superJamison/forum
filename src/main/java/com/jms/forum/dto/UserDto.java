package com.jms.forum.dto;

import com.jms.forum.entity.User;
import lombok.Data;

/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/21 19:48
 */
@Data
public class UserDto {
    private boolean login;
    private User user;
    private String message;
}
