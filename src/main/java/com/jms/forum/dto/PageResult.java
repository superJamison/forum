package com.jms.forum.dto;

import com.jms.forum.exception.CustomizeErrorCode;
import com.jms.forum.exception.CustomizeException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.security.krb5.internal.PAData;

import java.io.Serializable;
import java.util.List;

/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/17 13:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult implements Serializable {
    private List data;
    private Long total;
    private String message;
    private boolean success;
    private Integer code;

    public static PageResult errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(), errorCode.getMessage());
    }

    public static PageResult errorOf(CustomizeException e) {
        return errorOf(e.getCode(), e.getMessage());
    }

    private static PageResult errorOf(Integer code, String message) {
        PageResult pageResult = new PageResult();
        pageResult.setCode(code);
        pageResult.setMessage(message);
        return pageResult;
    }
    public static PageResult okOf() {
        PageResult pageResult = new PageResult();
        pageResult.setCode(200);
        pageResult.setMessage("请求成功");
        return pageResult;
    }

    public static <T> PageResult okOf(List<T> t) {
        PageResult pageResult = new PageResult();
        pageResult.setCode(200);
        pageResult.setMessage("请求成功");
        pageResult.setData(t);
        return pageResult;
    }
}
