/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.verify;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: ICustomerVerification.java, v 0.1 2016年5月13日 下午5:53:32 ZHOUJINGWEI598 Exp $
 */
public interface ICustomerVerification {

    public boolean verify(Object expect, Object actual);
}
