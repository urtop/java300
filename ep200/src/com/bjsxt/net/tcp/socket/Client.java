package com.bjsxt.net.tcp.socket;

import java.io.*;
import java.net.Socket;

/**
 * Created by Mark on 2016/8/10.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8888);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF("6666");

        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        System.out.println(inputStream.readUTF());


    }
}
