package com.vinci.flashsale.common.exception;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/12/16
 */
public class GlobalExceptionConstant {

    public static final Integer VALID_EXCEPTION_CODE = 400;

    public static final GlobalExceptionRecord IO_EXCEPTION = new GlobalExceptionRecord(1000, "IO流操作时发生异常！");
    public static final GlobalExceptionRecord LOCK_EXCEPTION = new GlobalExceptionRecord(2000, "获取锁失败！");

}
