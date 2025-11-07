package com.vinci.flashsale.common.vo;

import lombok.Data;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/11/6
 */
@Data
public class CommonResult<T> {

    private Integer code;
    private String message;
    private T data;

    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.setCode(200);
        result.setMessage("OK");
        result.setData(data);
        return result;
    }

    public static <T> CommonResult<T> failed(Integer code, String message) {
        CommonResult<T> result = new CommonResult<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(null);
        return result;
    }

}
