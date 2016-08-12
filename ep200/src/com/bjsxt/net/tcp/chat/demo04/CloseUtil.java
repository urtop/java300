package com.bjsxt.net.tcp.chat.demo04;

import java.io.Closeable;

/**
 * Created by Mark on 2016/8/11.
 */
public class CloseUtil {
    public static void closeAll(Closeable... io) {
        for (Closeable temp : io) {
            try {
                if (null != temp) {
                    temp.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
