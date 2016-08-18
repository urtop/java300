package com.bjsxt.server.demo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket server;

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public void start() {
        try {

            server = new ServerSocket(8888);
            this.receive();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void receive() {
        try {
            Socket socket = server.accept();
            String msg = null;
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while ((msg = reader.readLine()).length() > 0) {
                sb.append(msg+"\r\n");
                if (null == msg) {
                    break;
                }

            }
            String requestinfo = sb.toString().trim();
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {

    }

}

