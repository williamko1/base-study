package me.zaneqin.register.client;

/**
 * desc : 服务注册返回体
 *
 * @author Zane Qin
 * creatTime : 13:33 2022/1/28
 * modifier:
 * modifyTime:
 */
public class RegisterResponse {

    /** 注册响应码 */
    private int code;
    /** 注册响应消息 &*/
    private String msg;
    /** 注册状态码 */
    private String status;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

