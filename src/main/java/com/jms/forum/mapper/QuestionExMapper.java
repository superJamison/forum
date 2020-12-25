package com.jms.forum.mapper;

import com.jms.forum.entity.Question;
import com.jms.forum.entity.QuestionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionExMapper {

    void updateViewCountByQuestionId(@Param("id") Integer id);
}
