package com.jms.forum.controller;

import com.github.pagehelper.Page;
import com.jms.forum.dto.PageResult;
import com.jms.forum.entity.Question;
import com.jms.forum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/18 20:40
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/toQuestionPage")
    public String toQuestionPage(){
        return "question";
    }

    @GetMapping("/getQuestionById")
    @ResponseBody
    public Question getQuestionById(Integer id){
        return questionService.getQuestionById(id);
    }

}
