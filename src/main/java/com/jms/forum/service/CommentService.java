package com.jms.forum.service;

import com.jms.forum.dto.Result;
import com.jms.forum.entity.Comment;

/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/25 23:25
 */
public interface CommentService {
    Result addComment(Comment comment);
}
