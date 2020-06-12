package me.qinzc.chatroom;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * 类 - 聊天室客户端线程
 * 1. 读取服务端的输入端
 * ============================================================================
 * 2008-2019 hoperun,并保留所有权利。
 * ============================================================================
 *
 * @author : Zane Qin
 * creatTime : 16:06 2019-02-13
 * modifier:
 * modifyTime:
 */
public class ChatClientThread implements Runnable {

    BufferedReader br;

    public ChatClientThread(BufferedReader br) {
        this.br = br;
    }

    @Override
    public void run() {
        String line;
        try{
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
