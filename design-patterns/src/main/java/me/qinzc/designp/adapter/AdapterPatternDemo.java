package me.qinzc.designp.adapter;

/**
 * desc : 适配器模式
 * 手上有新老2接口和一个老接口实现类
 * 系统要求面向新接口来开发，老接口的实现类不能用
 * 开发一个老接口到新接口的一个适配器
 * 适配器是实现了新接口的，但是适配器中持有老接口实现类实例的引用
 * 适配器的新接口方法的实现，全部基于老接口实现类的老方法来实现即可
 * 对于调用方而言，只要使用适配器来开发即可，就可以通过面向新接口开发，底层是使用老接口
 *
 * 在实际企业开发中的使用场景
 *
 * 1. 在系统不断升级的过程中使用，对已经写好的老的类，写一套适配器来适配老类，但是提供新的接口，
 * 2. 对于已有的第三方类库，比如redis的客户端，或者elasticsearch的客户端，他们提供了一套api，但是我们这里的要求是面向我们这里的接口来编程，
 *    此时可以写一个适配器，将比如redis客户端的接口适配到我们的接口。
 *
 * @author Zane Qin
 * creatTime : 14:17 2021/11/17
 * modifier:
 * modifyTime:
 */
public class AdapterPatternDemo {

    public static void main(String[] args) {
        NewInterface oldObject = new NewInterfaceAdapter(new OldInterfaceImpl());
        NewInterface newObject = new NewInterfaceImpl();
        oldObject.newExecute();
        newObject.newExecute();

    }

    public static class NewInterfaceAdapter implements NewInterface {

        private OldInterface oldObject;

        public NewInterfaceAdapter(OldInterface oldInterface) {
            this.oldObject = oldInterface;
        }

        @Override
        public void newExecute() {
            oldObject.oldExecute();
        }
    }


    /**
     * 老版本接口
     */
    public static interface OldInterface {
        void oldExecute();
    }

    public static class OldInterfaceImpl implements OldInterface {
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
