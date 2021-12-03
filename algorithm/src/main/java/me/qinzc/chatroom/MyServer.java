package me.qinzc.chatroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 类 -
 * ============================================================================
 * 2008-2019 hoperun,并保留所有权利。
 * ============================================================================
 *
 * @author : Zane Qin
 * creatTime : 13:35 2019-02-13
 * modifier:
 * modifyTime:
 */
public class MyServer {
    public static List<Socket> socketList = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args)  throws IOException {
        ServerSocket ss = new ServerSocket(30000);
        while(true){
            Socket s = ss.accept();
            socketList.add(s);
            new Thread(new ServerThread(s)).start();
        }
    }
}
class ServerThread implements Runnable{
    Socket s;
    BufferedReader br;
    public ServerThread(Socket s) throws IOException {
        this.s = s;
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }

    @Override
    public void run() {
        try {
            String content;
            while((content = readFromClient()) != null){
                for (Socket d : MyServer.socketList){
                    PrintStream ps = new PrintStream(d.getOutputStream());
                    ps.println(content);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private String readFromClient(){
        try {
           return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            MyServer.socketList.remove(s);
        }
        return null;
    }
}
