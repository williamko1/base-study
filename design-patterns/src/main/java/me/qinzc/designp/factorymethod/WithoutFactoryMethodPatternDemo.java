package me.qinzc.designp.factorymethod;

/**
 * desc : 不使用设计模式
 * 问题：
 * 跟模板方法模式问题一模一样
 * 多个工厂类中，有生产产品的相同的通用逻辑，没有抽取
 *
 * @author Zane Qin
 * creatTime : 15:12 2021/11/17
 * modifier:
 * modifyTime:
 */
public class WithoutFactoryMethodPatternDemo {

    public static void main(String[] args) {
        Product product1 = Product1Factory.createProduct();
        product1.execute();
        Product product2 = Product2Factory.createProduct();
        product2.execute();
        Product product3 = Product3Factory.createProduct();
        product3.execute();
    }
    public interface Product {
        void execute();
    }

    public static class Product1 implements Product {
        @Override
        public void execute() {
            System.out.println("产品1的功能实现");
        }
    }

    public static class Product2 implements Product {
        @Override
        public void execute() {
            System.out.println("产品2的功能实现");
        }
    }

    public static class Product3 implements Product {
        @Override
        public void execute() {
            System.out.println("产品3的功能实现");
        }
    }

    public static class Product1Factory {
        public static Product createProduct() {
            System.out.println("生产产品的通用逻辑");
            System.out.println("生产产品1的特殊逻辑");
            return new Product1();
        }
    }

    public static class Product2Factory {
        public static Product createProduct() {
            System.out.println("生产产品的通用逻辑");
            System.out.println("生产产品2的特殊逻辑");
            return new Product2();
        }
    }

    public static class Product3Factory {
        public static Product createProduct() {
            System.out.println("生产产品的通用逻辑");
            System.out.println("生产产品3的特殊逻辑");
            return new Product3();
        }
    }
}
