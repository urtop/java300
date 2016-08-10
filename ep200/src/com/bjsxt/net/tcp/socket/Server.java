package com.bjsxt.net.tcp.socket;

import com.sun.prism.shader.Solid_ImagePattern_Loader;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
            Socket socket = serverSocket.accept();
            System.out.println("clinet is : "+socket.getRemoteSocketAddress());
            String msg = "hi9999";

            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeUTF(msg);

        }

    }
}
