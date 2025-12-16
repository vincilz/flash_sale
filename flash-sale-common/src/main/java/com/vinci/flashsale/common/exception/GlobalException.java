package com.vinci.flashsale.common.exception;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {

    private static final Integer GLOBAL_EXCEPTION_CODE = 500;
    private static final String GLOBAL_EXCEPTION_MSG = "系统异常";

    private final GlobalExceptionRecord exceptionRecord;

    public GlobalException() {
        this.exceptionRecord = new GlobalExceptionRecord(GLOBAL_EXCEPTION_CODE, GLOBAL_EXCEPTION_MSG);
    }

    public GlobalException(String message) {
        this.exceptionRecord = new GlobalExceptionRecord(GLOBAL_EXCEPTION_CODE, message);
    }

    public GlobalException(Throwable throwable) {
        this.exceptionRecord = new GlobalExceptionRecord(GLOBAL_EXCEPTION_CODE, throwable.getMessage());
    }

    public GlobalException(GlobalExceptionRecord exceptionRecord) {
        this.exceptionRecord = exceptionRecord;
    }

    public GlobalException(Integer code, String message) {
        this.exceptionRecord = new GlobalExceptionRecord(code, message);
    }

}
