package com.common.datatemplate.reader.handlers.processor.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.common.datatemplate.reader.handlers.XmlReaderHandler;
import com.common.datatemplate.reader.handlers.processor.IXmlNodeProcessor;
import com.common.utils.XmlPropertyNameConstants;

public class NodeArgumentFieldProcessor implements IXmlNodeProcessor {
    private static String              INT     = "int";
    private static String              DOUBLE  = "double";
    private static String              BOOLEAN = "boolean";
    private static String              LONG    = "long";
    private static String              FLOAT   = "float";
    private static String              BYTE    = "byte";
    private static String              CHAR    = "char";
    private static String              SHORT   = "short";
    private static Map<String, String> basicTypeMap;
    static {
        basicTypeMap = new HashMap<String, String>();
        basicTypeMap.put(INT, "java.lang.Integer");
        basicTypeMap.put(DOUBLE, "java.lang.Double");
        basicTypeMap.put(BOOLEAN, "java.lang.Boolean");
        basicTypeMap.put(LONG, "java.lang.Long");
        basicTypeMap.put(FLOAT, "java.lang.Float");
        basicTypeMap.put(BYTE, "java.lang.Byte");
        basicTypeMap.put(CHAR, "java.lang.Character");
        basicTypeMap.put(SHORT, "java.lang.Short");
    }

    @Override
    public void processNode(XmlReaderHandler handler) throws NoSuchFieldException,
                                                     SecurityException, IllegalArgumentException,
                                                     IllegalAccessException {
        String strValue = handler.getCurrentvalue();
        String type = handler.getCurrentAttributes().getValue(XmlPropertyNameConstants.TYPE);

        if (basicTypeMap.keySet().contains(type)) {
            setValue(handler, strValue);
        }
    }

    @Override
    public void fillInObjectRoute(XmlReaderHandler handler) throws Exception {
        Object param = null;
        param = getParamInstance(handler.getCurrentAttributes().getValue(
            XmlPropertyNameConstants.TYPE));
        Object parent = handler.getObjectRoute().getFirst();
        String fieldName = handler.getNodeRoute().getFirst();
        Class<? extends Object> parentType = parent.getClass();
        Field field = null;
        field = parentType.getField(fieldName);
        if (null != field) {
            field.setAccessible(true);
            field.set(parent, param);
        }

        handler.getObjectRoute().addFirst(param);
    }

    private Object getParamInstance(String classType) throws ClassNotFoundException,
                                                     InstantiationException, IllegalAccessException {
        Object param = null;
        String type = basicTypeMap.get(classType);
        if (null != type) {
            Class<?> paramType = Class.forName(type);
            param = paramType.newInstance();
        } else {
            Class<?> paramType = Class.forName(type);
            param = paramType.newInstance();
        }

        return param;
    }

    public void setValue(XmlReaderHandler handler, String strValue) throws NoSuchFieldException,
                                                                   SecurityException,
                                                                   IllegalArgumentException,
                                                                   IllegalAccessException {
        Object parent = handler.getObjectRoute().get(1);
        String fieldName = handler.getNodeRoute().get(1);
        Field field = parent.getClass().getField(fieldName);
        field.setAccessible(true);
        String type = handler.getCurrentAttributes().getValue(XmlPropertyNameConstants.TYPE);
        if (INT.equals(type)) {
            int value = Integer.parseInt(strValue);
            field.setInt(parent, value);
        } else if (DOUBLE.equals(type)) {
            double value = Double.parseDouble(strValue);
            field.setDouble(parent, value);
        } else if (BOOLEAN.equals(type)) {
            boolean value = Boolean.parseBoolean(strValue);
            field.setBoolean(parent, value);
        } else if (LONG.equals(type)) {
            long value = Long.parseLong(strValue);
            field.setLong(parent, value);
        } else if (FLOAT.equals(type)) {
            float value = Float.parseFloat(strValue);
            field.setFloat(parent, value);
        } else if (BYTE.equals(type)) {
            byte value = Byte.parseByte(strValue);
            field.setByte(parent, value);
        } else if (CHAR.equals(type)) {
            char value = strValue.charAt(0);
            field.setChar(parent, value);
        } else if (SHORT.equals(type)) {
            short value = Short.parseShort(strValue);
            field.setShort(parent, value);
        } else {
            System.out.println("NodeArgumentFieldProcessor#setValue: unknown type!");
        }
    }
}
