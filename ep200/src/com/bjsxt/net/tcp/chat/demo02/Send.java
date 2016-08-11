package com.bjsxt.net.tcp.chat.demo02;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Mark on 2016/8/11.
 */
public class Send implements Runnable {
    private BufferedReader console;
    private DataOutputStream outputStream;
    private boolean isRunning = true;

    public Send() {
        console = new BufferedReader(new InputStreamReader(System.in));
    }

    //拿到套接字对象
    public Send(Socket socket) {
        this();
        try {
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            isRunning = false;

            //close all if wrong
            CloseUtil.closeAll(outputStream, console);
        }
    }

    private String getMsgFromConsole() {

        try {
            return console.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void doSend() {
        String msg = getMsgFromConsole();
        if (null != msg) {
            try {
                outputStream.writeUTF(msg);
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
                isRunning = false;
                CloseUtil.closeAll(outputStream);
            }

        }
    }

    @Override
    public void run() {
        while (isRunning) {
            doSend();
        }
    }
}
