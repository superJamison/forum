package com.jms.forum.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/17 13:29
 */
@Data
public class PageResult implements Serializable {
    private List data;
    private Long total;
}
