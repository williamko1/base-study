package me.qinzc.chatroom;

/**
 * 聊天协议
 */
public interface Protocol {

    /**
     * 字符长度
     */
    int PROTOCOL_LEN = 1;
    /**
     * 消息描述
     */
    String MSG_ROUND = "®";
    /**
     * 用户描述
     */
    String USER_ROUND = "©";
    /**
     * 登录成功
     */
    String LOGIN_SUCCESS = "1";
    /**
     * 登出成功
     */
    String LOGOUT_SUCCESS = "登出成功";
    /**
     * 登出
     */
    String LOGOUT = "ℹ︎";
    /**
     * 重复用户名
     */
    String NAME_REP = "℥";
    /**
     * 私聊描述
     */
    String PRIVATE_ROUND = "℗";
    /**
     * 分割
     */
    String SPLIT_SIGN = "ℨ";
}
