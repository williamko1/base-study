package me.zaneqin.register.client;

/**
 * desc : 负责向 register-server 发起注册申请的线程
 *
 * @author Zane Qin
 * creatTime : 13:29 2022/1/28
 * modifier:
 * modifyTime:
 */
public class RegisterWorker extends Thread {

    private HttpSender httpSender;




    public HttpSender getHttpSender() {
        return httpSender;
    }

    public void setHttpSender(HttpSender httpSender) {
        this.httpSender = httpSender;
    }

    @Override
    public void run() {
        // 获取当前机器的信息
        // 包括ip hostname 端口号 servicename

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setServiceName("serviceName");
        registerRequest.setHostname("123123");
        registerRequest.setIp("ip");
        registerRequest.setPort(6379);
        RegisterResponse response = httpSender.register(registerRequest);
    }
}
