package com.jms.forum.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jms.forum.dto.PageResult;
import com.jms.forum.dto.QuestionDto;
import com.jms.forum.dto.Result;
import com.jms.forum.entity.*;
import com.jms.forum.mapper.CommentMapper;
import com.jms.forum.mapper.QuestionExMapper;
import com.jms.forum.mapper.QuestionMapper;
import com.jms.forum.mapper.UserMapper;
import com.jms.forum.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/16 23:03
 */
@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExMapper questionExMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentMapper commentMapper;

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
    public PageResult getPage(Integer page, Integer limit, String searchContent) {
        Page<Object> objectPage = PageHelper.startPage(page, limit);
        QuestionExample questionExample = new QuestionExample();
        if (!"".equals(searchContent)){
            QuestionExample.Criteria criteria1 = questionExample.createCriteria();
            QuestionExample.Criteria criteria2 = questionExample.createCriteria();
            QuestionExample.Criteria criteria3 = questionExample.createCriteria();
            criteria1.andDescriptionLike("%"+searchContent+"%");
            //or
            criteria2.andTitleLike("%"+searchContent+"%");
            criteria3.andTagLike("%"+searchContent+"%");
            questionExample.or(criteria2);
            questionExample.or(criteria3);
        }
        List<Question> list = questionMapper.selectByExample(questionExample);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
                User user = userMapper.selectByPrimaryKey(list.get(i).getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(list.get(i), questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        PageResult result = new PageResult();
        result.setData(questionDtoList);
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
        try {
            Page<Object> objectPage = PageHelper.startPage(page, limit);
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria().andCreatorEqualTo(id);
            List<Question> list = questionMapper.selectByExample(questionExample);
            User user = userMapper.selectByPrimaryKey(id);
            List<QuestionDto> questionDtoList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                QuestionDto questionDto = new QuestionDto();
                BeanUtils.copyProperties(list.get(i), questionDto);
                questionDto.setUser(user);
                questionDtoList.add(questionDto);
            }
            PageResult result = new PageResult();
            result.setData(questionDtoList);
            result.setTotal(objectPage.getTotal());
            return result;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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

    @Override
    public Result updateQuestion(Question question) {
        try {
            question.setGmtModified(System.currentTimeMillis());
           questionMapper.updateByPrimaryKeySelective(question);
           return new Result(true, "问题更新成功！");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(false, "问题更新失败！");
    }

    @Override
    public void addViewCount(Question question) {
        questionExMapper.updateViewCountByQuestionId(question.getId());
    }

    @Override
    public void addReplyQuestionCount(Comment comment) {
        if (comment.getType() == 1){
            questionExMapper.updateCommentCount(comment.getParentId());
        }else if (comment.getType() == 2){
            Comment comment1 = commentMapper.selectByPrimaryKey(comment.getParentId());
            questionExMapper.updateCommentCount(comment1.getParentId());
        }
    }

    @Override
    public PageResult getMyNewReplyPage(Integer page, Integer limit, Integer id) {
        return null;
    }

    @Override
    public String getHotTags() {
        List<String> tags = questionExMapper.selectTopic();
        String hotTopic = "";
        for (int i = 0; i < tags.size(); i++) {
            if (i == tags.size()){
                hotTopic = hotTopic + tags.get(i);
            }else {
                hotTopic = hotTopic + tags.get(i) + ",";
            }
        }
        return hotTopic;
    }
}
