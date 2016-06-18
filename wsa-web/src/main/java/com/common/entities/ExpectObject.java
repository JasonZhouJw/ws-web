/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: ExpectObject.java, v 0.1 2016年5月16日 下午2:24:04 ZHOUJINGWEI598 Exp $
 */
public class ExpectObject {

    private List<Expection> expectList = new ArrayList<Expection>();

    /**
     * Getter method for property <tt>expectList</tt>.
     * 
     * @return property value of expectList
     */
    public List<Expection> getExpectList() {
        return expectList;
    }

    /**
     * Setter method for property <tt>expectList</tt>.
     * 
     * @param expectList value to be assigned to property expectList
     */
    public void setExpectList(List<Expection> expectList) {
        this.expectList = expectList;
    }

}
