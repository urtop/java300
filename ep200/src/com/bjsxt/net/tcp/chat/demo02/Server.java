package com.bjsxt.net.tcp.chat.demo02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Mark on 2016/8/11.
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8888);
        //复用端口
        socket.setReuseAddress(true);
        Socket server = socket.accept();
        while (true) {
            //read from client
            DataInputStream inputStream = new DataInputStream(server.getInputStream());
            //send to client
            DataOutputStream outputStream = new DataOutputStream(server.getOutputStream());
            String msg = inputStream.readUTF();
            System.out.println(msg);
            msg = "Server say hi";
            outputStream.writeUTF(msg);
            outputStream.flush();
        }
    }
}
