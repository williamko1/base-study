package me.zaneqin.register.client;

/**
 * desc : 服务注册请求实体
 *
 * @author Zane Qin
 * creatTime : 13:31 2022/1/28
 * modifier:
 * modifyTime:
 */
public class RegisterRequest {

    /** 服务名称 */
    private String serviceName;
    /** 服务所在机器ip地址 */
    private String ip;
    /** 主机名 */
    private String hostname;
    /** 服务监听的端口号 */
    private int port;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
