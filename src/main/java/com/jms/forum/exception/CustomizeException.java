package com.jms.forum.exception;

import lombok.Data;

/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/18 18:26
 */
@Data
public class CustomizeException extends RuntimeException {
    private String message;
    private Integer code;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
