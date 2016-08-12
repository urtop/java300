package com.bjsxt.net.tcp.chat.demo04;

import sun.java2d.pipe.SpanIterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Mark on 2016/8/11.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("请输入名称");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String name = bufferedReader.readLine();
        Socket client = new Socket("localhost", 8888);
//        client.setReuseAddress(true);
        //套接字对象给send对象
        Send sender = new Send(client,name);
        //放入线程
        Thread senderThread = new Thread(sender);
        senderThread.start();

        //套接字对象给receive对象
        Receive receiver = new Receive(client);
        //放入线程
        Thread   receiverThread = new Thread(receiver);
        receiverThread.start();
    }
}
