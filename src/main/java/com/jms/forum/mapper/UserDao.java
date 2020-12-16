package com.jms.forum.mapper;

import com.jms.forum.entity.User;
import org.apache.ibatis.annotations.Param;


public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUsername(String username);

    User selectByToken(@Param("token") String token);
}