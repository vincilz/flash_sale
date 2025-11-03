package com.vinci.flashsale.account.biz.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vinci.flashsale.account.biz.entity.AccountDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/10/28
 */
@Mapper
public interface AccountMapper extends BaseMapper<AccountDO> {

    default AccountDO findByUserId(String userId) {
        LambdaQueryWrapper<AccountDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AccountDO::getUserId, userId);
        return selectOne(queryWrapper);
    }

}
