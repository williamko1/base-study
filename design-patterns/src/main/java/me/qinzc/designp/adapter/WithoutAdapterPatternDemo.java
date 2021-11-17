package me.qinzc.designp.adapter;

/**
 * desc : 不用设计模式实现
 * 如果不用任何设计模式，我们的问题在哪？
 * 问题其实很明显，我们新的代码中，融合新老接口2套接口，很麻烦的一个事情
 * 代码恶心，面向的是规范和风格完全不同的2套接口，理解和维护的成本提高了
 * 其次，假如说，现在都不给你使用老版本接口的机会
 * 直接强制性规范要求按照新版本接口来走，你的老版本接口的实现类就没法用了吗？
 *
 * @author Zane Qin
 * creatTime : 14:11 2021/11/17
 * modifier:
 * modifyTime:
 */
public class WithoutAdapterPatternDemo {

    public static void main(String[] args) {
        OldInterface oldObject = new OldInterfaceImpl();
        NewInterface newObject = new NewInterfaceImpl();
        oldObject.oldExecute();
        newObject.newExecute();
    }

    /**
     * 老版本接口
     */
    public static interface OldInterface {
        void oldExecute();
    }

    public static class OldInterfaceImpl implements OldInterface{
        @Override
        public void oldExecute() {
            System.out.println("old execute");
        }
    }

    /**
     * 新版本接口
     */
    public static interface NewInterface {
        void newExecute();
    }

    public static class NewInterfaceImpl implements NewInterface {
        @Override
        public void newExecute() {
            System.out.println("new execute");
        }
    }



}
