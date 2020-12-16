package com.jms.forum.mapper;


import com.jms.forum.entity.Question;

import java.util.List;

public interface QuestionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);

    List<Question> list();
}