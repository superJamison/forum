package com.jms.forum.controller;

import com.jms.forum.entity.Question;
import com.jms.forum.entity.User;
import com.jms.forum.mapper.QuestionMapper;
import com.jms.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/16 16:25
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(Question question,
                            HttpServletRequest request,
                            Model model){

        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        if (question.getTitle() == null || "".equals(question.getTitle())){
            model.addAttribute("error", "标题不能为空!");
            return "publish";
        }
        if (question.getDescription() == null || "".equals(question.getDescription())){
            model.addAttribute("error", "补充说明不能为空!");
            return "publish";
        }
        if (question.getTag() == null || "".equals(question.getTag())){
            model.addAttribute("error", "标签不能为空!");
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");

        if (user == null){
           model.addAttribute("error", "用户未登录!");
           return "publish";
        }
        question.setLikeCount(0);
        question.setCommentCount(0);
        question.setViewCount(0);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.insertSelective(question);
        return "redirect:/";
    }
}
