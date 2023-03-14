package me.qinzc.base;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * desc : 7种迭代性能对比
 *
 * @author Zane Qin
 * creatTime : 10:52 2022/9/22
 * modifier:
 * modifyTime:
 */
public class HashMapTest {

    @Test
    public void iteratorEntrySet() {
        // 创建并赋值 HashMap
        Map<Integer, String> map = initMap();
        // 遍历
        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }

    @Test
    public void iteratorKeySet() {
        // 创建并赋值 HashMap
        Map<Integer, String> map = initMap();
        // 遍历
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            System.out.println(key);
            System.out.println(map.get(key));
        }
    }

    @Test
    public void forEachEntrySet() {
        // 创建并赋值 HashMap
        Map<Integer, String> map = initMap();
        // 遍历
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }

    @Test
    public void forEachKeySet() {
        // 创建并赋值 HashMap
        Map<Integer, String> map = initMap();
        // 遍历
        for (Integer key : map.keySet()) {
            System.out.println(key);
            System.out.println(map.get(key));
        }
    }

    @Test
    public void lambda() {
        // 创建并赋值 HashMap
        Map<Integer, String> map = initMap();
        map.forEach((key, value) -> {
            System.out.println(key);
            System.out.println(value);
        });
    }

    @Test
    public void streamSerial() {
        // 创建并赋值 HashMap
        Map<Integer, String> map = initMap();
        map.entrySet().stream().forEach( entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        });
    }

    @Test
    public void streamParallel() {
        // 创建并赋值 HashMap
        Map<Integer, String> map = initMap();
        map.entrySet().parallelStream().forEach(entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        });
    }


    private static Map<Integer, String> initMap() {
        // 创建并赋值 HashMap
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Java");
        map.put(2, "JDK");
        map.put(3, "Spring Framework");
        map.put(4, "MyBatis framework");
        map.put(5, "Java中文社群");
        return map;
    }
}
