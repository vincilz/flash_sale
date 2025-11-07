package com.vinci.flashsale.common.config;

import com.vinci.flashsale.common.vo.CommonResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<CommonResult<String>> handleValidationException(WebExchangeBindException ex) {
        String msg = ex.getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .findFirst()
                .orElse("参数验证失败");
        return Mono.just(CommonResult.failed(400, msg));
    }

    @ExceptionHandler(Throwable.class)
    public Mono<CommonResult<String>> handleGlobalException(Throwable ex) {
        return Mono.just(CommonResult.failed(500, ex.getMessage()));
    }
}
