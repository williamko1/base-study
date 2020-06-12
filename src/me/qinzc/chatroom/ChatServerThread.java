package me.qinzc.chatroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * 类 - 阻塞聊天服务器线程
 * 1. 获取socket的输入（读） 输出（写）
 * 2. 完成登录，登出，群聊，私聊的协议确认和转发给指定client
 * === =========================================================================
 * 2008-2019 hoperun,并保留所有权利。
 * ============================================================================
 *
 * @author : Zane Qin
 * creatTime : 15:07 2019-02-13
 * modifier:
 * modifyTime:
 */
public class ChatServerThread implements Runnable{
    private Socket socket;
    BufferedReader br;
    PrintStream ps;

    public ChatServerThread(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ps = new PrintStream(socket.getOutputStream());
            String line;
            while ( (line = br.readLine()) != null){
                if(line.startsWith(Protocol.USER_ROUND) && line.endsWith(Protocol.USER_ROUND)){
                    String userName = getRealMsg(line);
                    if(Server.CLIENTS.entry.containsKey(userName)){
                        System.out.println("重复");
                        ps.println(Protocol.NAME_REP);
                    }else{
                        Server.CLIENTS.put(userName, ps);
                        ps.println(Protocol.LOGIN_SUCCESS);
                        System.out.println(Server.CLIENTS.getKeyByValue(ps) + "登录成功");
                    }
                }else if(line.startsWith(Protocol.PRIVATE_ROUND) && line.endsWith(Protocol.PRIVATE_ROUND)){
                    String userAndMsg = getRealMsg(line);
                    String user = userAndMsg.split(Protocol.SPLIT_SIGN)[0];
                    String msg = userAndMsg.split(Protocol.SPLIT_SIGN)[1];
                    Server.CLIENTS.entry.get(user).println(Server.CLIENTS.getKeyByValue(ps) + "悄悄对你说:" + msg);
                }else if(line.startsWith(Protocol.LOGOUT) && line.endsWith(Protocol.LOGOUT)){
                    System.out.println(Server.CLIENTS.getKeyByValue(ps) + "登出成功");
                    Server.CLIENTS.removeByValue(ps);
                }else{
                    String msg = getRealMsg(line);
                    for (PrintStream p : Server.CLIENTS.entry.values()){
                        p.println(Server.CLIENTS.getKeyByValue(ps) + "说:" + msg);
                    }
                }
            }
        } catch (IOException e) {
            Server.CLIENTS.removeByValue(ps);
            System.out.println(Server.CLIENTS.entry.size());
            e.printStackTrace();
            releaseResource();
        }
    }

    private void releaseResource(){
        try {
            if (socket != null) {
                socket.close();
            }
            if (br != null){
                br.close();
            }
            if (ps != null){
                ps.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getRealMsg(String msg){
       return msg.substring(Protocol.PROTOCOL_LEN, msg.length()-Protocol.PROTOCOL_LEN);
    }
}
