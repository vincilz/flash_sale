package com.vinci.flashsale.storage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/11/7
 */
@Data
@Builder
public class StorageReduceSagaRequest {

    private String commodityCode;

    private Integer count;

}
