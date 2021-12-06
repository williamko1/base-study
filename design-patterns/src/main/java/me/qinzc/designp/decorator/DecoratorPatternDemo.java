package me.qinzc.designp.decorator;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 14:04 2021/12/6
 * modifier:
 * modifyTime:
 */
public class DecoratorPatternDemo {

    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        Component decorator = new Decorator(component);
        decorator.execute();
        // javaIO
        // spring的aop， 基于动态代理的理念，装饰我们的目标对象，然后加入事务控制，打印日志之类的功能
    }


    public interface Component{
        void execute();
    }

    public static class ConcreteComponent implements Component{

        @Override
        public void execute() {
            System.out.println("基础功能");
        }
    }

    public static class Decorator implements Component {
        private Component component;

        public Decorator(Component component) {
            this.component = component;
        }

        @Override
        public void execute() {
            System.out.println("在执行基础功能之前，实现部分功能增强");
            component.execute();
            System.out.println("在执行基础功能之后，实现部分功能增强");
        }
    }

}
