package com.bjsxt.net.tcp.chat.demo01;

import java.io.*;
import java.net.Socket;

/**
 * Created by Mark on 2016/8/10.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost",8888);
        //send to server
        DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        //read from console
        String msg = console.readLine();
        outputStream.writeUTF(msg);
        outputStream.flush();

        //read from server
        DataInputStream inputStream  = new DataInputStream(client.getInputStream());
        msg =  inputStream.readUTF();
        System.out.println(msg);




    }


}
