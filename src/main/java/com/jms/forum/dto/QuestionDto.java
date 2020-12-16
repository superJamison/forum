package com.jms.forum.dto;

import com.jms.forum.entity.User;
import lombok.Data;

import java.io.Serializable;

/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/16 23:01
 */
@Data
public class QuestionDto implements Serializable {
    private Integer id;

    private String title;

    private String description;

    private Long gmtCreate;

    private Long gmtModified;

    private Integer creator;

    private Integer commentCount;

    private Integer viewCount;

    private Integer likeCount;

    private String tag;

    private User user;

    private static final long serialVersionUID = 1L;
}
