/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.utils;

import java.lang.reflect.Method;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: ReflectUtil.java, v 0.1 2016年4月15日 上午11:26:49 ZHOUJINGWEI598 Exp $
 */
public class ReflectUtil {

    @SuppressWarnings("rawtypes")
    public static Method getTargetMethod(Class clazz, String targetMethodName) {
        Method targetMethod = null;
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(targetMethodName)) {
                targetMethod = method;
                break;
            }
        }
        return targetMethod;
    }
}
