package me.qinzc.chatroom;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * 类 - 客户端
 * 1. 第一次向服务器发送 用户名注册请求，如有重复会继续
 * 2. 启动客户端线程（发送服务端的输入流)
 * ============================================================================
 * 2008-2019 hoperun,并保留所有权利。
 * ============================================================================
 *
 * @author : Zane Qin
 * creatTime : 16:08 2019-02-13
 * modifier:
 * modifyTime:
 */
public class Client {
    public static final String SERVER_IP = "127.0.0.1";
    public static final int SERVER_PORT = 30000;
    Socket socket;
    PrintStream ps;
    BufferedReader brServer;
    BufferedReader keyIn;

    public void init(){
        keyIn = new BufferedReader(new InputStreamReader(System.in));
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            ps = new PrintStream(socket.getOutputStream());
            brServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String tip = "";
            while(true){
                String userName = JOptionPane.showInputDialog(tip + "请输入用户名");
                ps.println(Protocol.USER_ROUND + userName + Protocol.USER_ROUND);
                String result = brServer.readLine();
                if(result.equals(Protocol.NAME_REP)){
                    tip = "用户名已存在，请重新";
                    continue;
                }
                if(result.equals(Protocol.LOGIN_SUCCESS)){
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("网络异常， 请重新登录");
            releaseResource();
        }
        new Thread(new ChatClientThread(brServer)).start();
    }

    public void readAndSend(){
        String line;
        try{
            while((line = keyIn.readLine()) != null){
                if(line.indexOf(":") > 0 && line.startsWith("/")){
                    line = line.substring(2);
                    ps.println(Protocol.PRIVATE_ROUND + line.split(":")[0]
                            + Protocol.SPLIT_SIGN + line.split(":")[1] + Protocol.PRIVATE_ROUND);
                }else if(line.equals("/quit")){
                    ps.println(Protocol.LOGOUT + line + Protocol.LOGOUT);
                    releaseResource();
                }else{
                    ps.println(Protocol.MSG_ROUND + line + Protocol.MSG_ROUND);
                }
            }

        }catch (IOException e){
            System.out.println("网络异常，请重新登录");
            releaseResource();
        }
    }

    private void releaseResource(){
        try {
            if (socket != null) {
                socket.close();
            }
            System.exit(1);
            if (brServer != null){
                brServer.close();
            }
            if (ps != null){
                ps.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.init();
        client.readAndSend();
    }
}
