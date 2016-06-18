/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.verify.impl;

import java.util.List;

import com.common.entities.Expection;
import com.common.entities.FieldValue;
import com.common.enums.Operation;
import com.common.enums.Responses;
import com.common.exceptions.validations.ValidateCommonException;
import com.common.verify.AbsVerification;
import com.common.verify.VerifyFactory;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: ListVerification.java, v 0.1 2016年5月13日 下午5:50:41 ZHOUJINGWEI598 Exp $
 */
public class ListVerification extends AbsVerification {

    /** 
     * @see com.common.verify.AbsVerification#verify(com.common.entities.Expection, com.common.entities.FieldValue)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void verify(Expection expect, FieldValue actual) throws ValidateCommonException {
        List expectList = (List) expect.getValue();
        List actualList = (List) actual.getValue();
        if (expect.getOperation().getOperation().equals(Operation.EQUAL.getOperation())) {
            if (expectList.size() != actualList.size()) {
                throw new ValidateCommonException(Responses.LIST_SIZE_NOT_MATCH.getRespCode(),
                    "Compare Field [" + actual.getName() + "], Expect List Size ["
                            + expectList.size() + "]" + "], Actual List Size [" + actualList.size()
                            + "]");
            }
            for (int i = 0; i < actualList.size(); i++) {
                try {
                    verifyElement(expectList.get(i), actualList.get(i));
                } catch (ValidateCommonException e) {
                    throw new ValidateCommonException(Responses.NOT_EQUALS.getRespCode(),
                        "Compare Element Index [" + i + "], Expect Element [" + expectList.size()
                                + "]" + "], Actual Element [" + actualList.size() + "]. "
                                + e.getMessage());
                }
            }

        } else {

        }
    }

    /**
     * 
     * @param object
     * @param object2
     * @return 
     * @return
     * @throws ValidateCommonException 
     */
    private void verifyElement(Object expect, Object actual) throws ValidateCommonException {
        VerifyFactory.verify((Expection) expect, (FieldValue) actual);
    }

}
