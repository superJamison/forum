package com.jms.forum.mapper;

import com.jms.forum.entity.Comment;
import com.jms.forum.entity.CommentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentExMapper {

    void addLikeCount(@Param("id") Integer id);
}
