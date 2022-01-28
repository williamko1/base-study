package me.zaneqin.register.client;

/**
 * desc : 在服务上被创建和启动
 *
 * 主要负责和register-server通讯
 *
 * @author Zane Qin
 * creatTime : 13:28 2022/1/28
 * modifier:
 * modifyTime:
 */
public class RegisterClient {

    public void start() {
        // 一旦启动这个组件之后
        // 开启后台线程，向register-server发送注册请求

        new RegisterWorker().start();
        // 在注册成功之后就会开启另一个线程 开启心跳服务

    }
}
