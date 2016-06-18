/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.demo.filechannel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 
 * @author zhoujingwei598
 * @version $Id: FileChannelDemo.java, v 0.1 2016年5月26日 上午10:16:17 zhoujingwei598 Exp $
 */
public class FileChannelDemo {

    public static void readFile(String file) {
        FileInputStream is = null;
        try {
            is = new FileInputStream(new File(file));
            RandomAccessFile baf=new RandomAccessFile(new File(file),"rw");
            FileChannel inChannel = is.getChannel();
            int index = 0;
            do {
                ByteBuffer buf = ByteBuffer.allocate(index + 10);
                int bytesRead = inChannel.read(buf);
                System.out.println("Bytes num:" + bytesRead);

                byte[] inputBuffer = new byte[buf.position()];
                //准备从字节缓冲中读取数据
                buf.flip();
                buf.get(inputBuffer);
                System.out
                    .println("Input Index " + (++index) + ", Data:" + new String(inputBuffer));
                if (bytesRead <= 0) {
                    break;
                }
            } while (true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
