package me.qinzc;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 类 -
 * ============================================================================
 * 2008-2019 hoperun,并保留所有权利。
 * ============================================================================
 *
 * @author : Zane Qin
 * creatTime : 10:47 2019-02-13
 * modifier:
 * modifyTime:
 */
public class NetServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(30000);
        int i = 0;
        while(true){
            i++;
            Socket s = ss.accept();
            s.getInputStream();
            PrintStream ps = new PrintStream(s.getOutputStream());
            ps.println("你好，你收到了服务器的新年祝福" + i +"\n 来来来");
            ps.close();
            s.close();
        }
    }
}
