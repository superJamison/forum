package com.jms.forum.controller;

import com.jms.forum.dto.Result;
import com.jms.forum.entity.Comment;
import com.jms.forum.entity.User;
import com.jms.forum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

    @PostMapping("/addComment")
    public Result addComment(Comment comment, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            return new Result(false, "您还未登录！请先登录后再重试！~~~~(>_<)~~~~");
        }
        comment.setCommentator(user.getId());
        return commentService.addComment(comment);
    }
}
