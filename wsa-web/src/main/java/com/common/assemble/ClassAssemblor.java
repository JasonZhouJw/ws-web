/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.assemble;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.common.entities.WebserviceClassInfo;
import com.common.entities.WsdlInfo;
import com.common.utils.ReflectUtil;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: ClassAssemblor.java, v 0.1 2016年5月4日 下午2:20:24 ZHOUJINGWEI598 Exp $
 */
public class ClassAssemblor {

    public static List<WebserviceClassInfo> assembleClass(WsdlInfo wsdlInfo) throws Exception {
        List<WebserviceClassInfo> webserviceClassInfoList = new ArrayList<WebserviceClassInfo>();
        for (String targetMethodName : wsdlInfo.getOperationList()) {
            Method targetMethod = ReflectUtil.getTargetMethod(
                Class.forName(wsdlInfo.getFacadeClass()), targetMethodName);
            if (targetMethod != null) {
                WebserviceClassInfo webserviceClassInfo = new WebserviceClassInfo(wsdlInfo,
                    targetMethod);
                webserviceClassInfoList.add(webserviceClassInfo);
            }
        }
        return webserviceClassInfoList;
    }
}
