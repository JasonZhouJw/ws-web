/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.verify.impl;

import com.common.entities.Expection;
import com.common.entities.FieldValue;
import com.common.exceptions.validations.ValidateCommonException;
import com.common.verify.AbsVerification;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: StringVerification.java, v 0.1 2016年5月12日 上午10:54:59 ZHOUJINGWEI598 Exp $
 */
public class VerificationAsString extends AbsVerification {

    /** 
     * @see com.common.verify.AbsVerification#verify(java.lang.Object, java.lang.Object, com.common.entities.ClassInfo)
     */
    @Override
    public void verify(Expection expect, FieldValue actual) throws ValidateCommonException {
        String expectValue = String.valueOf(expect.getValue());
        String actualValue = String.valueOf(actual.getValue());
        boolean result = expect.getOperation().verify(expectValue, actualValue);
        if (!result) {
            throw new ValidateCommonException("Compare Field [" + actual.getName()
                                              + "], Expect Value [" + expectValue
                                              + "] , Actual Value [" + actualValue + "]");
        }
    }

}
