package com.lighting.metal.exception;

import lombok.Getter;

/**
 * 自定义业务异常（跨境业务场景专用）
 */
@Getter
public class BusinessException extends RuntimeException {
    private final int code;

    public BusinessException(String message) {
        super(message);
        this.code = 400;
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }
}