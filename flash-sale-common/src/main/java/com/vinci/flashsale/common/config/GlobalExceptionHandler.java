package com.vinci.flashsale.common.config;

import com.vinci.flashsale.common.dto.CommonResponse;
import com.vinci.flashsale.common.exception.GlobalExceptionConstant;
import com.vinci.flashsale.common.utils.CommonResponseUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public CommonResponse handleValidationException(WebExchangeBindException ex) {
        List<String> errorParamList = ex.getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .toList();
        String errorMsg = Strings.join(errorParamList, ',');
        return CommonResponseUtils.failed(GlobalExceptionConstant.VALID_EXCEPTION_CODE, errorMsg);
    }

    @ExceptionHandler(Throwable.class)
    public CommonResponse handleGlobalException(Throwable ex) {
        return CommonResponseUtils.failed(ex);
    }
}
