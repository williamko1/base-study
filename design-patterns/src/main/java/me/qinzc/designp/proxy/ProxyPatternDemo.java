package me.qinzc.designp.proxy;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 13:48 2021/12/3
 * modifier:
 * modifyTime:
 */
public class ProxyPatternDemo {


    public static interface Subject {
        void request();
    }

    public static class ConcreteSubject implements Subject {

        @Override
        public void request() {
            System.out.println("执行请求");
        }
    }

    public static class Proxy implements Subject {

        private Subject subject;

        public Proxy(Subject subject) {
            this.subject = subject;
        }

        @Override
        public void request() {
            System.out.println("执行额外的条件判断，考虑是否需要调用subject的request()方法");
            boolean invoke = true;
            if (invoke) {
                subject.request();
            }
        }
    }


}

