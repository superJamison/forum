package com.jms.forum.service;

import com.jms.forum.dto.PageResult;
import com.jms.forum.dto.QuestionDto;
import com.jms.forum.dto.Result;
import com.jms.forum.entity.Comment;
import com.jms.forum.entity.Question;

import java.util.List;

/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/16 23:03
 */
public interface QuestionService {
    List<QuestionDto> list();

    PageResult getPage(Integer page, Integer limit, String searchContent);

    Question getQuestionById(Integer id);

    Result addQuestion(Question question);

    PageResult getMyProblemPage(Integer page, Integer limit, Integer id);

    QuestionDto getQuestionDtoById(Integer id);

    Result updateQuestion(Question question);

    void addViewCount(Question question);


    void addReplyQuestionCount(Comment comment);
}
