package me.qinzc.designp.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 14:06 2021/12/3
 * modifier:
 * modifyTime:
 */
public class ObserverPatternDemo {

    public static void main(String[] args) {
        Subject subject = new Subject();
        Observer observer = new ConcreteObserver();
        subject.addObserver(observer);
        subject.setState(555);
    }


    public static class Subject extends Observable {
        private Integer state;

        public Integer getState() {
            return this.state;
        }

        public void setState(Integer state) {
            // 在这里状态改变了
            this.state = state;
            // 通知关联的一些观察者，说我的状态变化了
            this.setChanged();
            this.notifyObservers(state);
        }
    }

    public static class ConcreteObserver implements Observer {

        @Override
        public void update(Observable o, Object arg) {
            Integer state = (Integer) arg;
            System.out.println("目标对象的状态变化成：" + state);
        }
    }

}

