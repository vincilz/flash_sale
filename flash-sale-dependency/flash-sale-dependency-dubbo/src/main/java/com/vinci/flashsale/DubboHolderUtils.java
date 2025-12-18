package com.vinci.flashsale;

import com.vinci.flashsale.common.constant.HeaderConstant;
import org.apache.dubbo.rpc.RpcContext;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/12/18
 */
public class DubboHolderUtils {

    public static Long getUserId() {
        // Provider 侧从服务上下文读取透传的用户 ID
        String userId = RpcContext.getServiceContext().getAttachment(HeaderConstant.USER_ID);
        return Long.valueOf(userId);
    }

    public static String getRequestId() {
        return RpcContext.getServiceContext().getAttachment(HeaderConstant.REQUEST_ID);
    }

}
