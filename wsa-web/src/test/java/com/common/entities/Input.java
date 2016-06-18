/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: Input.java, v 0.1 2016年5月4日 下午3:54:14 ZHOUJINGWEI598 Exp $
 */
public class Input {

    private String                  name;

    private long                    age;

    private List<String>            list = new ArrayList<String>();

    private Map<String, ChildInput> map  = new HashMap<String, ChildInput>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Map<String, ChildInput> getMap() {
        return map;
    }

    public void setMap(Map<String, ChildInput> map) {
        this.map = map;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }
}
