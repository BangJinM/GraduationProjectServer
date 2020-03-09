package com.netty.server.core;
public class CoderUtil {
    public static int bytesToInt(byte[] data, int offset) {
           int num = 0;
           for (int i = offset; i < offset + 4; i++) {
            num <<= 8;
            num |= (data[i] & 0xff);
           }
           return num;
        }

    public static byte[] intToBytes(int num) {  
        byte[] b = new byte[4];
           for (int i = 0; i < 4; i++) {
            b[i] = (byte) (num >>> (24 - i * 8));
           }
           return b;
    }
 
}