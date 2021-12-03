package me.qinzc.chatroom;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 类 - 阻塞聊天室服务端
 * 1. 启动聊天服务器
 * 2. 有client连接（socket)新建线程完成交互
 * ============================================================================
 * 2008-2019 hoperun,并保留所有权利。
 * ============================================================================
 *
 * @author : Zane Qin
 * creatTime : 14:42 2019-02-13
 * modifier:
 * modifyTime:
 */
public class Server {
    public static final int SERVER_PORT = 30000;
    public static SocketMap<String, PrintStream> CLIENTS = new SocketMap<>();

    public void init() {
        ServerSocket ss;
        try {
            ss = new ServerSocket(SERVER_PORT);
            while (true) {
                Socket s = ss.accept();
                new Thread(new ChatServerThread(s)).start();
                for (int i = 0; i < 100; i++) {
                }
            }
        } catch (IOException e) {
            System.out.println("服务器启动异常是否端口" + SERVER_PORT + "被占用");
        }

    }

    public static void main(String[] args) {
        Server server = new Server();
        server.init();
    }

}
