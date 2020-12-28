package com.jms.forum.service;

import com.jms.forum.dto.CommentDto;
import com.jms.forum.dto.Result;
import com.jms.forum.entity.Comment;

import java.util.List;

/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/25 23:25
 */
public interface CommentService {
    /**
     * 添加评论
     * @param comment
     * @return
     */
    Result addComment(Comment comment);

    /**
     * 根据问题的id查询所有的评论
     * @param questionId
     * @return
     */
    List<CommentDto> getReplyById(Integer questionId);

    /**
     * 添加点赞喜欢
     * @param comment
     * @return
     */
    Result addLikeCount(Comment comment);
}
