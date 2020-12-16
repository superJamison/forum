package com.jms.forum.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String telephone;

    private String token;

    private static final long serialVersionUID = 1L;
}