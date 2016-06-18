/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.ws;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.common.entities.WsdlInfo;
import com.common.utils.Constants;
import com.common.utils.ReflectUtil;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: WebserviceClient.java, v 0.1 2016年4月15日 上午11:23:28 ZHOUJINGWEI598 Exp $
 */
public class WebserviceClient {

    public static void request(WsdlInfo wsdlInfo) {

        try {
            JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
            factory.setServiceClass(Class.forName(wsdlInfo.getFacadeClass()));
            factory.setAddress(wsdlInfo.getAddress());
            Object service = factory.create();
            for (String targetMethodName : wsdlInfo.getOperationList()) {
                Method targetMethod = ReflectUtil.getTargetMethod(
                    Class.forName(wsdlInfo.getFacadeClass()), targetMethodName);
                if (targetMethod != null) {
                    Parameter[] parameters = targetMethod.getParameters();
                    Object[] dataParameters = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        Object parameterObj = parameter.getType().newInstance();
                        //TODO: 组装数据
                        dataParameters[i] = parameterObj;
                    }
                    if (targetMethod.getReturnType() != null) {
                        Object returnObj = targetMethod.invoke(service, dataParameters);
                        //TODO:验证返回
                        System.out.println(wsdlInfo.getFacadeClass() + Constants.DOT
                                           + targetMethodName + ":" + returnObj);
                    } else {
                        targetMethod.invoke(service, dataParameters);
                        System.out.println(wsdlInfo.getFacadeClass() + Constants.DOT
                                           + targetMethodName + " Success");
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }
}
