package com.jms.forum.dto;

import com.jms.forum.entity.Comment;
import lombok.Data;

import java.util.List;

/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/26 13:04
 */
@Data
public class CommentDto {
    private Comment comment;
    private List<Comment> children;
}
