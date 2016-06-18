/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.enums;

import com.common.verify.AbsVerification;
import com.common.verify.impl.NumberVerifcation;
import com.common.verify.impl.VerificationAsString;

/**
 * 
 * @author zhoujingwei598
 * @version $Id: ClassTyps.java, v 0.1 2016年5月18日 上午10:00:30 zhoujingwei598 Exp $
 */
public enum ClassTypes {

    STRING("java.lang.String", new VerificationAsString()),
    Long("long", new NumberVerifcation()),
    LONG("java.lang.Long", new NumberVerifcation());

    private String          type;

    private AbsVerification verification;

    private ClassTypes(String type, AbsVerification verification) {
        this.type = type;
        this.verification = verification;
    }

    public String getType() {
        return type;
    }

    public static AbsVerification getVerication(String type) {
        return STRING.verification;
    }
}
