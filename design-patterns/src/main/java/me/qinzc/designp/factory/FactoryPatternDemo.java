package me.qinzc.designp.factory;

/**
 * desc : 工厂模式demo
 *
 * 工厂模式的核心思想，其实就是不要自己在代码里手动new一个实现类对象出来，
 * 因为那样的话，调用方就不是面向接口编程了，还得去care实现
 * 所以一般都是用工厂的思想来提供所有实现类的实例，然后调用方面向接口编程即可，接口不变，调用方代码不变
 * spring 就是工厂
 * @author Zane Qin
 * creatTime : 15:01 2021/11/17
 * modifier:
 * modifyTime:
 */
public class FactoryPatternDemo {

    public static void main(String[] args) {
        Product product = ProductFactory.create();
        product.execute();
    }

    public interface Product {
        void execute();
    }

    public static class ProductImpl1 implements Product {
        @Override
        public void execute() {
            System.out.println("产品1的功能实现");
        }
    }

    public static class ProductFactory {
        public static Product create() {
            return new ProductImpl1();
        }
    }


}
