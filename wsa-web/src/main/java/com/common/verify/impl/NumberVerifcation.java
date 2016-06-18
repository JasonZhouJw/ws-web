/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.verify.impl;

import java.math.BigDecimal;

import com.common.entities.Expection;
import com.common.entities.FieldValue;
import com.common.exceptions.validations.ValidateCommonException;
import com.common.verify.AbsVerification;

/**
 * 
 * @author zhoujingwei598
 * @version $Id: NumberVerifcation.java, v 0.1 2016年5月18日 上午10:06:22 zhoujingwei598 Exp $
 */
public class NumberVerifcation extends AbsVerification {

    /** 
     * @see com.common.verify.AbsVerification#verify(com.common.entities.Expection, com.common.entities.FieldValue)
     */
    @Override
    public void verify(Expection expect, FieldValue actual) throws ValidateCommonException {
        String expectValue = String.format("%032g",
            new BigDecimal(String.valueOf(expect.getValue())));
        String actualValue = String.format("%032g",
            new BigDecimal(String.valueOf(actual.getValue())));
        boolean result = expect.getOperation().verify(expectValue, actualValue);
        if (!result) {
            throw new ValidateCommonException("Compare Field [" + actual.getName()
                                              + "], Expect Value [" + expectValue
                                              + "] , Actual Value [" + actualValue + "]");
        }
    }
}
