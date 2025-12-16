package com.vinci.flashsale.account.biz.tcc;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/12/16
 */
@LocalTCC
public interface AccountTccService {

    @TwoPhaseBusinessAction(
            name = "AccountTccAction",
            commitMethod = "commit",
            rollbackMethod = "rollback"
    )
    boolean tryFreeze(
            BusinessActionContext actionContext,
            @BusinessActionContextParameter(paramName = "userId") String userId,
            @BusinessActionContextParameter(paramName = "money") Integer money

    );

    boolean commit(BusinessActionContext actionContext);

    boolean rollback(BusinessActionContext actionContext);


}
