/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.enums;

import com.common.entities.Expection;
import com.common.verify.ICustomerVerification;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: Operation.java, v 0.1 2016年5月5日 上午11:00:02 ZHOUJINGWEI598 Exp $
 */
public enum Operation {

    EQUAL("EQ"),
    LOWER_THAN("LT"),
    LOWER_EQUAL("LE"),
    NOT_EQUAL("NE"),
    GREATER_THAN("GT"),
    GREATER_EQUAL("GE"),
    IN("IN"),
    NOT_IN("NI"),
    CUSTOMER("C");

    private String operation;

    private Operation(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

    public boolean verify(Expection expect, Object actual) {
        boolean result = false;
        if (CUSTOMER.getOperation().equals(this.operation)) {
            try {
                ICustomerVerification customerVerification = (ICustomerVerification) Class.forName(
                    String.valueOf(expect.getCustomerClazzName())).newInstance();
                result = customerVerification.verify(expect.getValue(), actual);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            result = this.verify(String.valueOf(expect.getValue()), String.valueOf(actual));
        }
        return result;
    }

    public boolean verify(String expect, String actual) {
        boolean result = false;
        if (LOWER_THAN.getOperation().equals(this.operation)) {
            result = expect.compareTo(actual) < 0;
        } else if (LOWER_EQUAL.getOperation().equals(this.operation)) {
            result = expect.compareTo(actual) <= 0;
        } else if (NOT_EQUAL.getOperation().equals(this.operation)) {
            result = expect.compareTo(actual) != 0;
        } else if (GREATER_THAN.getOperation().equals(this.operation)) {
            result = expect.compareTo(actual) > 0;
        } else if (GREATER_EQUAL.getOperation().equals(this.operation)) {
            result = expect.compareTo(actual) >= 0;
        } else if (IN.getOperation().equals(this.operation)) {
            result = actual.indexOf(expect) >= 0;
        } else if (NOT_IN.getOperation().equals(this.operation)) {
            result = actual.indexOf(expect) < 0;
        } else {
            result = expect.compareTo(actual) == 0;
        }
        return result;
    }
}
