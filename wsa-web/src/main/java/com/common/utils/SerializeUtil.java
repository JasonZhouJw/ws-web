package com.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.Binary;

public class SerializeUtil<T> {

    public byte[] toByteArray(T obj) {
        ObjectOutputStream os = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            os = getObjectOutputStream(outputStream);
            os.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        byte[] bytes = outputStream.toByteArray();
        return bytes;
    }

    public List<byte[]> toByteArrayList(List<T> list) throws IOException {
        List<byte[]> byteArrayList = new ArrayList<byte[]>();
        for(T obj : list){
            byteArrayList.add(toByteArray(obj));
        }
        return byteArrayList;
    }

    @SuppressWarnings("unchecked")
    public T assembleObject(byte[] bytes) {
        InputStream inputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        T obj = null;
        try {
            obj = (T) in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    public T assembleObject(Binary bytes) {
        return this.assembleObject(bytes.getData());
    }

    @SuppressWarnings("unchecked")
    public List<T> assembleObjectList(List<byte[]> list) throws IOException, ClassNotFoundException {
        InputStream inputStream = null;
        ObjectInputStream in = null;
        List<T> result = new ArrayList<T>();
        for (byte[] bytes : list) {
            inputStream = new ByteArrayInputStream(bytes);
            in = new ObjectInputStream(inputStream);
            T obj = (T) in.readObject();
            result.add(obj);

            try {
                inputStream.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public ObjectOutputStream getObjectOutputStream(ByteArrayOutputStream outputStream) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return out;
    }
}
