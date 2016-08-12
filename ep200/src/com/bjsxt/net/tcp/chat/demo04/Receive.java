package com.bjsxt.net.tcp.chat.demo04;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Mark on 2016/8/11.
 */
public class Receive implements Runnable {
    private DataInputStream inputStream;
    private boolean isRunning = true;

    public Receive() {
    }

    public Receive(Socket socket) {
        try {
            inputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            isRunning = false;
            CloseUtil.closeAll(inputStream);
        }
    }

    public String doreceive() {
        String msg = "";
        try {
            msg = inputStream.readUTF();
        } catch (IOException e) {
            System.out.println("conn broken");
            isRunning = false;
            CloseUtil.closeAll(inputStream);
        }
        return msg;
    }

    @Override
    public void run() {
        while (isRunning) {
            System.out.println(doreceive());
        }
    }
}
