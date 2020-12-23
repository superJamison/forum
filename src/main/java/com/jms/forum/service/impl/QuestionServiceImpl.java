package com.jms.forum.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jms.forum.dto.PageResult;
import com.jms.forum.dto.QuestionDto;
import com.jms.forum.dto.Result;
import com.jms.forum.entity.Question;
import com.jms.forum.entity.QuestionExample;
import com.jms.forum.entity.User;
import com.jms.forum.entity.UserExample;
import com.jms.forum.mapper.QuestionMapper;
import com.jms.forum.mapper.UserMapper;
import com.jms.forum.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/16 23:03
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<QuestionDto> list() {
        List<QuestionDto> questionDtoList = new ArrayList<>();
        List<Question> list = questionMapper.selectByExample(new QuestionExample());
        for (Question question : list) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        return questionDtoList;
    }

    @Override
    public PageResult getPage(Integer page, Integer limit) {
        Page<Object> objectPage = PageHelper.startPage(page, limit);
        List<Question> list = questionMapper.selectByExample(new QuestionExample());
        PageResult result = new PageResult();
        result.setData(list);
        result.setTotal(objectPage.getTotal());
        return result;
    }

    @Override
    public Question getQuestionById(Integer id) {
        return questionMapper.selectByPrimaryKey(id);
    }

    @Override
    public Result addQuestion(Question question) {
        try {
            question.setViewCount(0);
            question.setCommentCount(0);
            question.setLikeCount(0);
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
            return new Result(true, "问题发起成功！");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(false, "问题发起失败！");
    }

    @Override
    public PageResult getMyProblemPage(Integer page, Integer limit, Integer id) {
        Page<Object> objectPage = PageHelper.startPage(page, limit);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(id);
        List<Question> list = questionMapper.selectByExample(questionExample);
        PageResult result = new PageResult();
        result.setData(list);
        result.setTotal(objectPage.getTotal());
        return result;
    }

    @Override
    public QuestionDto getQuestionDtoById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(question.getCreator());
        List<User> users = userMapper.selectByExample(userExample);
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(question, questionDto);
        questionDto.setUser(users.get(0));
        return questionDto;
    }
}
