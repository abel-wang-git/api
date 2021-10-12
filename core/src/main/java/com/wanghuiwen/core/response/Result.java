package com.wanghuiwen.core.response;


import com.wanghuiwen.common.JSONUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 * 统一API响应结果封装
 */
public class Result {
    private int code;
    private String message;
    private Object data;

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    public Result() {
    }
    public Result(Exception e) {
        this.code=400;
        this.message = e.getMessage();
    }

    public Result(IResultEnum resultEnum, Object data) {
        this.data = data;
        this.code = resultEnum.getCode();
    }

    public Result(IResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.data = null;
    }

    public Result(int code,String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return JSONUtils.obj2json(this);
    }
}
