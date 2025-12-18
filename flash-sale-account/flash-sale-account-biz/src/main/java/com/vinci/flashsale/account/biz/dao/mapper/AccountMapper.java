package com.vinci.flashsale.account.biz.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vinci.flashsale.account.biz.dao.dataobj.AccountDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/10/28
 */
@Mapper
public interface AccountMapper extends BaseMapper<AccountDO> {

    default AccountDO findByUserId(Long userId) {
        LambdaQueryWrapper<AccountDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AccountDO::getId, userId);
        return selectOne(queryWrapper);
    }

}
