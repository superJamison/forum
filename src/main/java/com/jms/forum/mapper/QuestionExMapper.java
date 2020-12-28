package com.jms.forum.mapper;

import org.apache.ibatis.annotations.Param;

public interface QuestionExMapper {

    void updateViewCountByQuestionId(@Param("id") Integer id);

    void updateCommentCount(@Param("id") Integer parentId);
}
