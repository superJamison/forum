package com.jms.forum.controller;

import com.jms.forum.entity.Question;
import com.jms.forum.entity.User;
import com.jms.forum.mapper.QuestionDao;
import com.jms.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/16 16:25
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private UserService userService;

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

        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    user = userService.selectByToken(token);
                    if (user != null){
                        HttpSession session = request.getSession();
                        session.setAttribute("user", user);
                        question.setCreator(user.getId());
                    }
                }
            }
        }

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
        questionDao.insert(question);
        return "redirect:/";
    }
}
