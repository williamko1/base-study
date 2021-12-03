package me.qinzc.io;

/**
 * 类 -
 * ============================================================================
 * 2008-2019 hoperun,并保留所有权利。
 * ============================================================================
 *
 * @author : Zane Qin
 * creatTime : 13:13 2019-02-18
 * modifier:
 * modifyTime:
 */
public enum NioType {
    CONTINUE(100),
    PROCESSING(102),
    CHENKPOINT(103),
    OK(200),
    CREATED(201),
    ACCEPTED(202),
    FOUND(302),
    UNAUTHORIZED(401),
    CONFLICT(409),
    GONE(410);

    private int code;
    NioType(int code) {
        this.code = code;
    }

    public static void main(String[] args) {
        /**
         * 测试字符串
         */
    }
}
