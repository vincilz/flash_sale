package com.vinci.flashsale.storage.biz.dao.dataobj;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/10/28
 */
@Data
@TableName("storage_tbl")
public class StorageDO {

    @TableId
    private Integer id;
    private Integer quantity;

}
