package me.qinzc.chatroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.Buffer;

/**
 * 类 -
 * ============================================================================
 * 2008-2019 hoperun,并保留所有权利。
 * ============================================================================
 *
 * @author : Zane Qin
 * creatTime : 13:53 2019-02-13
 * modifier:
 * modifyTime:
 */
public class MyClient {

    public static void main(String[] args) throws Exception {
        Socket s = new Socket();
        try {
            s.connect(new InetSocketAddress("127.0.0.1", 30000), 100000);
        } catch (IOException e) {
            System.out.println("服务器连接超时");
        }
        new Thread(new ClientThread(s)).start();

        PrintStream ps = new PrintStream(s.getOutputStream());
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while ((line = br.readLine()) != null){
            ps.println(line);
        }
    }
}

class ClientThread implements Runnable{
    private Socket s;
    BufferedReader br;

    public ClientThread(Socket s) throws IOException{
        this.s = s;
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }

    @Override
    public void run() {
        try {
            String content;
            while((content = br.readLine()) != null){
                System.out.println(content);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}