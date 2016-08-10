package com.bjsxt.net.tcp.chat.demo01;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Mark on 2016/8/10.
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8888);
        socket.setReuseAddress(true);

        Socket server = socket.accept();

        //read from client
        DataInputStream inputStream = new DataInputStream(server.getInputStream());
        String msg = inputStream.readUTF();
        System.out.println(msg);

        //send to client
        DataOutputStream outputStream = new DataOutputStream(server.getOutputStream());
        msg = "Server say hi";
        outputStream.writeUTF(msg);
        outputStream.flush();
    }
}
