/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.datatemplate.writer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Element;

import com.common.datatemplate.writer.constructors.Constructor;
import com.common.datatemplate.writer.constructors.DefaultConstructor;
import com.common.datatemplate.writer.constructors.ListConstructor;
import com.common.datatemplate.writer.constructors.MapConstructor;
import com.common.entities.ClassInfo;
import com.common.entities.FieldInfo;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: XmlElementFactory.java, v 0.1 2016年5月4日 上午10:56:30 ZHOUJINGWEI598 Exp $
 */
public class XmlElementFactory {

    private static Map<String, Constructor> constructorMap     = new HashMap<String, Constructor>();

    private static Constructor              defaultConstructor = new DefaultConstructor();

    static {
        constructorMap.put("java.util.List", new ListConstructor());
        constructorMap.put("java.util.Map", new MapConstructor());
    }

    public static void constructElement(Element element, FieldInfo field) {

        Constructor constructor = constructorMap.get(field.getType().getName());
        if (constructor == null) {
            constructor = defaultConstructor;
        }
        constructor.initElement(element, field);
    }

    public static void constructElement(Element element, ClassInfo clazz) {
        Iterator<Entry<String, FieldInfo>> fieldIter = clazz.getFieldMap().entrySet().iterator();
        while (fieldIter.hasNext()) {
            Entry<String, FieldInfo> fieldEntry = fieldIter.next();
            constructElement(element, fieldEntry.getValue());
        }
        if (clazz.getFieldMap().isEmpty()) {
            element.setText("");
        }
    }
}
