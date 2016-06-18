/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.verify;

import com.common.entities.Expection;
import com.common.entities.FieldValue;
import com.common.enums.ClassTypes;
import com.common.exceptions.validations.ValidateCommonException;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: VerifyFactory.java, v 0.1 2016年5月5日 上午10:57:07 ZHOUJINGWEI598 Exp $
 */
public class VerifyFactory {


    public static void verify(Expection expect, FieldValue actual) throws ValidateCommonException {
        ClassTypes.getVerication(actual.getType().getName()).verify(expect, actual);
    }
    
}
