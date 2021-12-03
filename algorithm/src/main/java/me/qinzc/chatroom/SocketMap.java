package me.qinzc.chatroom;

import java.util.*;

/**
 * 类 -
 * ============================================================================
 * 2008-2019 hoperun,并保留所有权利。
 * ============================================================================
 *
 * @author : Zane Qin
 * creatTime : 14:43 2019-02-13
 * modifier:
 * modifyTime:
 */
public class SocketMap<K, V> {


    public Map<K,V> entry = Collections.synchronizedMap(new HashMap<>());

    /**
     * 根据value删除value
     * @param v
     */
    public synchronized void removeByValue (V v){
        for (K k : entry.keySet()){
            if(entry.get(k).equals(v)){
                entry.remove(k);
                break;
            }
        }
    }

    /**
     * 根据value查找key
     * @return
     */
    public synchronized K getKeyByValue(V v){
        for (K k : entry.keySet()){
            if (entry.get(k).equals(v)){
                return k;
            }
        }
        return null;
    }

    /**
     * 实现put方法不允许重复
     * @param key
     * @param value
     */
    public synchronized V put(K key, V value){
        for (V v: entry.values()) {
            if(v.hashCode() == value.hashCode()){
                throw new RuntimeException("不允许有重复value");
            }
        }
        return entry.put(key,value);
    }

//    public synchronized Set<V> valueSet(){
//        Set<V> result = new HashSet<>();
//        CLIENTS.values().forEach((key, value) -> {
//            result.add(value);
//        });
//        return result;
//    }
}
