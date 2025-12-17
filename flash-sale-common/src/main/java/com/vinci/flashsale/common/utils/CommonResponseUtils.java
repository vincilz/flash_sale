package com.vinci.flashsale.common.utils;

import com.google.protobuf.Any;
import com.vinci.flashsale.common.dto.CommonResponse;
import com.vinci.flashsale.common.exception.GlobalException;
import com.vinci.flashsale.common.exception.GlobalExceptionRecord;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/12/16
 */
public class CommonResponseUtils {

    public static CommonResponse success() {
        return CommonResponse.newBuilder()
                .setCode(0)
                .setMessage("success")
                .build();
    }

    public static CommonResponse failed(Throwable throwable) {
        if (throwable instanceof GlobalException ge) {
            return failed(ge.getExceptionRecord());
        }
        GlobalException globalException = new GlobalException(throwable);
        return failed(globalException);
    }

    public static CommonResponse failed(GlobalExceptionRecord exceptionRecord) {
        return failed(exceptionRecord.getCode(), exceptionRecord.getMessage());
    }

    public static CommonResponse failed(Integer code, String message) {
        return CommonResponse.newBuilder()
                .setCode(code)
                .setMessage(message)
                .build();
    }
}
