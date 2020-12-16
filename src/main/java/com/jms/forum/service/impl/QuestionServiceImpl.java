package com.jms.forum.service.impl;

import com.jms.forum.dto.QuestionDto;
import com.jms.forum.entity.Question;
import com.jms.forum.entity.User;
import com.jms.forum.mapper.QuestionDao;
import com.jms.forum.mapper.UserDao;
import com.jms.forum.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.dc.pr.PRError;

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
    private QuestionDao questionDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<QuestionDto> list() {
        List<QuestionDto> questionDtoList = new ArrayList<>();
        List<Question> list = questionDao.list();
        for (Question question : list) {
            User user = userDao.selectByPrimaryKey(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        return questionDtoList;
    }
}
