package com.jms.forum.service.impl;

import com.jms.forum.dto.CommentDto;
import com.jms.forum.dto.Result;
import com.jms.forum.entity.Comment;
import com.jms.forum.entity.CommentExample;
import com.jms.forum.mapper.CommentExMapper;
import com.jms.forum.mapper.CommentMapper;
import com.jms.forum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/25 23:26
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentExMapper commentExMapper;

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

    @Override
    public List<CommentDto> getReplyById(Integer questionId) {
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();
        //获取一级评论
        criteria.andParentIdEqualTo(questionId)
                .andTypeEqualTo(1);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        //获取二级评论
        List<CommentDto> list = new ArrayList<>();
        for (Comment comment : comments) {
            CommentExample commentExample1 = new CommentExample();
            CommentExample.Criteria criteria1 = commentExample1.createCriteria();
            CommentDto commentDto = new CommentDto();
            commentDto.setComment(comment);
            criteria1.andParentIdEqualTo(comment.getId())
                    .andTypeEqualTo(2);
            List<Comment> childrenComment = commentMapper.selectByExample(commentExample1);
            commentDto.setChildren(childrenComment);
            list.add(commentDto);
        }
        return list;
    }

    @Override
    public Result addLikeCount(Comment comment) {
        try {
            commentExMapper.addLikeCount(comment.getId());
            return new Result(true, "点赞成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(false, "点赞失败");
    }
}
