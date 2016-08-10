package com.bjsxt.net.tcp.socket;

import com.sun.prism.shader.Solid_ImagePattern_Loader;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Mark on 2016/8/10.
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        serverSocket.setReuseAddress(true);
        Socket socket = serverSocket.accept();
        System.out.println("clinet is : "+socket.getInetAddress());

    }
}
