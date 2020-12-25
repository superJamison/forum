package com.jms.forum.service.impl;

import com.jms.forum.dto.Result;
import com.jms.forum.entity.Comment;
import com.jms.forum.mapper.CommentMapper;
import com.jms.forum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/25 23:26
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Result addComment(Comment comment) {
        try {
            comment.setGmtCreate(System.currentTimeMillis());
            comment.setGmtModified(comment.getGmtCreate());
            commentMapper.insertSelective(comment);
            return new Result(true, "回复成功！");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(false, "回复失败！");
    }
}
