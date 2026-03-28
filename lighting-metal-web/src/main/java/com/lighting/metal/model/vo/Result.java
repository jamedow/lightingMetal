package com.lighting.metal.model.vo;

import lombok.Data;

/**
 * 通用返回结果
 * 适配跨境接口统一返回格式
 */
@Data
public class Result<T> {
    /**
     * 响应码：200成功，400业务异常，500系统异常
     */
    private int code;
    /**
     * 响应信息
     */
    private String msg;
    /**
     * 响应数据
     */
    private T data;

    public Result(int code, String msg, Object o) {
        this.code = code;
        this.msg = msg;
        this.data = (T) o;
    }

    // 成功返回（无数据）
    public static <T> Result<T> success() {
        return new Result<>(200, "操作成功", null);
    }

    // 成功返回（有数据）
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }

    // 失败返回
    public static <T> Result<T> fail(int code, String msg) {
        return new Result<>(code, msg, null);
    }

    // 失败返回（默认400）
    public static <T> Result<T> fail(String msg) {
        return new Result<>(400, msg, null);
    }
}