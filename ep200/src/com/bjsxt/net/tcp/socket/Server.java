package com.bjsxt.net.tcp.socket;

import com.sun.prism.shader.Solid_ImagePattern_Loader;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Mark on 2016/8/10.
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        serverSocket.setReuseAddress(true);

        while (true) {
            //blocked when accept
            Socket socket = serverSocket.accept();

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            System.out.println("Client say :"+inputStream.readUTF());

            System.out.println("clinet is : " + socket.getRemoteSocketAddress());
            String msg = "hi9999";

            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeUTF(msg);

        }

    }
}
