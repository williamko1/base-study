package me.zaneqin.multi.practice;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * desc : 基于executor构建web服务器
 *
 * @author Zane Qin
 * creatTime : 15:15 2022/3/21
 * modifier:
 * modifyTime:
 */
public class TaskExecutionWebServer {

    private static final int NTHREADS = 100;

    private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            final Socket connection = socket.accept();
            Runnable task = () -> handleRequest(connection);
            exec.execute(task);
        }
    }

    private static void handleRequest(Socket connection) {

    }
}
