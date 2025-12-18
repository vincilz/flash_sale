package com.vinci.flashsale;

import com.vinci.flashsale.common.constant.HeaderConstant;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.springframework.core.Ordered;

@Activate(group = CommonConstants.CONSUMER)
public class DubboConsumerFilter implements Filter, Ordered {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String userId = RpcContext.getClientAttachment().getAttachment(HeaderConstant.USER_ID);
        if (userId != null) {
            RpcContext.getClientAttachment().setAttachment(HeaderConstant.USER_ID, userId);
        }
        String requestId = RpcContext.getClientAttachment().getAttachment(HeaderConstant.REQUEST_ID);
        if (requestId != null) {
            RpcContext.getClientAttachment().setAttachment(HeaderConstant.REQUEST_ID, requestId);
        }
        return invoker.invoke(invocation);
    }

    @Override
    public int getOrder() {
        return -150;
    }
}
