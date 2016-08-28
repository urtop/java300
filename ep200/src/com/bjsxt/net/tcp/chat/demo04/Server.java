package com.bjsxt.net.tcp.chat.demo04;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark on 2016/8/11.
 */
public class Server {
    private List<MyChannel> myChannelList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Server().start();
    }

    public void start() throws IOException {
        ServerSocket socket = new ServerSocket(8888);
        //复用端口
//        socket.setReuseAddress(true);

        while (true) {
            Socket server = socket.accept();
            MyChannel myChannel = new MyChannel(server);
            myChannelList.add(myChannel);  //加入list
            new Thread(myChannel).start();
        }
    }

    private class MyChannel implements Runnable {
        private DataInputStream inputStream;
        private DataOutputStream outputStream;
        private boolean isRunning = true;
        private String name;

        public MyChannel(Socket client) {
            try {
                inputStream = new DataInputStream(client.getInputStream());
                outputStream = new DataOutputStream(client.getOutputStream());

                //拿到客户端的名称
                this.name = inputStream.readUTF();

                //发送欢迎信息
                this.send("欢迎登录:" + this.name);
                sendToOthers(this.name + " 进入聊天室");
            } catch (IOException e) {
                e.printStackTrace();
                CloseUtil.closeAll(inputStream, outputStream);
                isRunning = false;
            }
        }

        private String receive() {
            String msg = "";
            try {
                msg = inputStream.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
                CloseUtil.closeAll(inputStream, outputStream);
                isRunning = false;
            }
            return msg;
        }

        /**
         * 发送数据
         *
         * @param msg
         */
        private void send(String msg) {
            if (null == msg || msg.isEmpty()) {
                return;
            }
            try {
                outputStream.writeUTF(msg);
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
                CloseUtil.closeAll(outputStream, inputStream);
                isRunning = false;
                myChannelList.remove(this);
            }
        }

        /**
         * 发送给其他人
         */
        private void sendToOthers(String msg) {
            if ((msg.indexOf("@") == 0)) {
                String name = msg.substring(1, msg.indexOf(":"));
                String content = msg.substring(msg.indexOf(":")+1);
                for (MyChannel chn : myChannelList) {
                    if (chn.name.equals(name)) {
                        chn.send(name + " 对你私聊: " + content);
                        break;
                    }
                }
            } else {
                //遍历连接对象
                for (MyChannel chn : myChannelList) {
                    //消息发送给除了自己以外的，所有人
                    if (chn == this) {
                        continue;
                    }
                    chn.send(this.name+" 对所有人说: "+msg);
                }
            }

        }


        @Override
        public void run() {
            while (isRunning) {
                sendToOthers(receive());
            }
        }
    }

}
