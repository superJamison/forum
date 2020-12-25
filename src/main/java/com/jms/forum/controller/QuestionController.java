package com.jms.forum.controller;

import com.github.pagehelper.Page;
import com.jms.forum.dto.PageResult;
import com.jms.forum.dto.QuestionDto;
import com.jms.forum.dto.Result;
import com.jms.forum.entity.Question;
import com.jms.forum.entity.User;
import com.jms.forum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/18 20:40
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/addQuestion")
    public Result addQuestion(Question question, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            return new Result(false, "您还未登录！请先登录后再重试！~~~~(>_<)~~~~");
        }
        question.setCreator(user.getId());
        Result result = questionService.addQuestion(question);
        return result;
    }

    @PostMapping("/updateQuestion")
    public Result updateQuestion(Question question, HttpServletRequest request){
        Result result = questionService.updateQuestion(question);
        return result;
    }

    @PostMapping("/addViewCount")
    public void addViewCount(Question question){
        questionService.addViewCount(question);
    }

    @PostMapping("/getQuestionById")
    public QuestionDto getQuestionDtoById(Question question){
        return questionService.getQuestionDtoById(question.getId());
    }

}
