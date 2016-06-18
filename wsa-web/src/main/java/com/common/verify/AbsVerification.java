/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.verify;

import com.common.entities.Expection;
import com.common.entities.FieldValue;
import com.common.exceptions.validations.ValidateCommonException;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: AbsVerification.java, v 0.1 2016年5月5日 上午11:12:38 ZHOUJINGWEI598 Exp $
 */
public abstract class AbsVerification {

    public abstract void verify(Expection expect, FieldValue actual) throws ValidateCommonException;
}
