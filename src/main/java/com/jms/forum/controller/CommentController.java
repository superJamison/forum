package com.jms.forum.controller;

import com.jms.forum.dto.CommentDto;
import com.jms.forum.dto.Result;
import com.jms.forum.entity.Comment;
import com.jms.forum.entity.Question;
import com.jms.forum.entity.User;
import com.jms.forum.service.CommentService;
import com.jms.forum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/25 23:23
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private QuestionService questionService;

    @PostMapping("/addComment")
    public Result addComment(Comment comment, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            return new Result(false, "您还未登录！请先登录后再重试！~~~~(>_<)~~~~");
        }
        comment.setCommentator(user.getId());
        questionService.addReplyQuestionCount(comment);
        return commentService.addComment(comment);
    }

    @GetMapping("/getReplyById")
    public List<CommentDto> getReplyById(Integer questionId){
        return commentService.getReplyById(questionId);
    }

    @PostMapping("/addLikeCount")
    public Result addLikeCount(Comment comment, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            return new Result(false, "您还未登录！请先登录后再重试！~~~~(>_<)~~~~");
        }
        return commentService.addLikeCount(comment);
    }
}
