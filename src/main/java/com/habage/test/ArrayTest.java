package com.habage.test;

import io.netty.buffer.ByteBuf;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ArrayTest {
    public static void main(String[] args) {
        int[] a = new int[10];
        System.out.println(Arrays.toString(a));
        System.out.println(args.length);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> cmap = new ConcurrentHashMap<>();

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        buffer.clear();
    }
}
