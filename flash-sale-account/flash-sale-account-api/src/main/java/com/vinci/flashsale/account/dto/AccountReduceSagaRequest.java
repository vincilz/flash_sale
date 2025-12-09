package com.vinci.flashsale.account.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/11/7
 */
@Data
@Builder
public class AccountReduceSagaRequest {

    private String userId;

    private Integer money;

}
