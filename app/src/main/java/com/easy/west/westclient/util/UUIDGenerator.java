package com.easy.west.westclient.util;

import java.util.UUID;

/**
 * uuid生成器
 * Created by admin on 2018/4/9.
 */

public class UUIDGenerator {
    private UUIDGenerator() {
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    //获得指定数量的UUID
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUUID();
        }
        return ss;
    }
}
