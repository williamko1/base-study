package me.qinzc.orientprocess.lesson1;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.Callable;

/**
 * desc : 内置类实例
 * 典型场景
 * 1. Java Event/ Listener
 * 2. Java Concurrent
 * 3. Spring Template
 *
 * @author : Zane Qin
 * creatTime : 13:15 2020/4/9
 * modifier:
 * modifyTime:
 */
public class InnerClassDemo {
    // 静态代码块
    static {
        new Runnable() {
            @Override
            public void run() {

            }
        };
    }
    // 实例块
    {
        new Callable(){

            @Override
            public Object call() throws Exception {
                return null;
            }
        };
    }

    public static void main(String[] args) {
        // 方法或实例
        // 匿名内置类（listener)
        PropertyChangeListener propertyChangeListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {


            }
        };

    }

    // 内置类
    static class PropertyChangeListenerImpl implements PropertyChangeListener{

        @Override
        public void propertyChange(PropertyChangeEvent evt) {

        }
    }
}
