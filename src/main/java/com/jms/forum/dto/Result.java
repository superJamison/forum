package com.jms.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/21 23:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private boolean success;
    private String message;
}
