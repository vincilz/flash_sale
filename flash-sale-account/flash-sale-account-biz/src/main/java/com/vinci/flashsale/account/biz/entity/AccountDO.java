package com.vinci.flashsale.account.biz.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author vinci
 * @date 2025/10/28
 * @version 1.0
 * 
 */
@Data
@TableName("account_tbl")
public class AccountDO {

    @TableId
    private Integer id;
    private String userId;
    private Integer money;

}
