package com.vinci.flashsale.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/12/16
 */
@Data

@AllArgsConstructor
public class GlobalExceptionRecord {

    private Integer code;

    private String message;

}
