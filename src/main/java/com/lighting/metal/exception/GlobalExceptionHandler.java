package com.lighting.metal.exception;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理器
 * 统一返回异常信息，适配跨境业务异常场景
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleBusinessException(BusinessException e) {
        log.error("业务异常：{}", e.getMessage(), e);
        Map<String, Object> result = new HashMap<>();
        result.put("code", e.getCode());
        result.put("msg", e.getMessage());
        result.put("data", null);
        return result;
    }

    /**
     * 处理参数校验异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleBindException(BindException e) {
        log.error("参数校验异常：{}", e.getMessage(), e);
        String msg = e.getBindingResult().getFieldError() != null
                ? e.getBindingResult().getFieldError().getDefaultMessage()
                : "参数校验失败";
        Map<String, Object> result = new HashMap<>();
        result.put("code", 400);
        result.put("msg", msg);
        result.put("data", null);
        return result;
    }

    /**
     * 处理通用异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleException(Exception e) {
        log.error("系统异常：{}", e.getMessage(), e);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 500);
        result.put("msg", StrUtil.isBlank(e.getMessage()) ? "服务器内部错误" : e.getMessage());
        result.put("data", null);
        return result;
    }
}