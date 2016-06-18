package com.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.common.persistence.dataobject.Person;

public class SerializeUtilTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
        SerializeUtil<Person> su = new SerializeUtil<Person>();

        Person p = new Person("name", 11);
        byte[] bytes = su.toByteArray(p);
        Object obj = su.assembleObject(bytes);
        System.out.println(obj);

        p = new Person("name2", 12);
        bytes = su.toByteArray(p);
        obj = su.assembleObject(bytes);
        System.out.println(obj);
    }

    @Test
    public void batchTest() {
        SerializeUtil<Person> su = new SerializeUtil<Person>();
        List<Person> list1 = new ArrayList<Person>();
        Person p = new Person("name", 11);
        list1.add(p);
        for(int i = 0; i < 4; ++i){
            Person p2 = new Person(p.getName() + i, p.getAge() + i);
            list1.add(p2);
        }

        List<byte[]> byteList = null;
        try {
            byteList = su.toByteArrayList(list1);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        byteList = new ArrayList<byte[]>();
//        for(Person pp : list1){
//            byteList.add(su.toByteArray(pp));
//        }
        
        List<Person> list2 = null;
        try {
            list2 = su.assembleObjectList(byteList);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(list2);
        for(Person obj : list2){
            System.out.println(obj);
        }
    }
}
