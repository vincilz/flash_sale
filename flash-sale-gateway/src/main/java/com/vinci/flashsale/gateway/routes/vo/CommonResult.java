package com.vinci.flashsale.gateway.routes.vo;

import com.vinci.flashsale.common.exception.GlobalExceptionConstant;
import lombok.Data;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/12/17
 */
@Data
public class CommonResult <T> {

    private Integer code;
    private String message;
    private T data;

    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.setCode(0);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    public static <T> CommonResult<T> failed(Integer code, String message) {
        CommonResult<T> result = new CommonResult<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

}
