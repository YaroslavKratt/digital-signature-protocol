package com.cryptology.raccoon.utills;

import org.apache.commons.lang.ArrayUtils;

import java.nio.ByteBuffer;

public class Utills {
    public static long reverseOrderOfBytes(long number) {
        byte[] temp = ByteBuffer.allocate(4).putInt((int) number).array();
        ArrayUtils.reverse(temp);

        return ByteBuffer.wrap(temp).getInt();
    }

    public static void printBytes(byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append(String.format("%02X ", b));
        }
        System.out.println(sb.toString());
    }

    public static String byteArrayToString(byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append(String.format("%02X", b));
        }
        return  sb.toString().toLowerCase();
    }
}
