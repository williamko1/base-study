package me.qinzc.hotspot;

import java.util.ArrayList;
import java.util.List;

/**
 * desc : java 堆溢出
 *
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author Zane Qin
 * creatTime : 10:36 2023/1/10
 * modifier:
 * modifyTime:
 */
public class HeapOOM {

    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
