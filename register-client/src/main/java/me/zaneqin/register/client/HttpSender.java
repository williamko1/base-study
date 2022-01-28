package me.zaneqin.register.client;

/**
 * desc : 负责发送各种http请求的组件
 *
 * @author Zane Qin
 * creatTime : 13:30 2022/1/28
 * modifier:
 * modifyTime:
 */
public class HttpSender {
    public RegisterResponse register(RegisterRequest registerRequest) {
        // 基于httpclient 构造一个请求，放入服务实例信息
        // 通过这个请求发送过去
        System.out.println("发送请求进行注册");

        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setStatus("success");

        return registerResponse;
    }

}
