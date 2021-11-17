package me.qinzc.designp.factorymethod;

/**
 * desc : 工厂模板方法模式
 *
 * @author Zane Qin
 * creatTime : 15:17 2021/11/17
 * modifier:
 * modifyTime:
 */
public class FactoryMethodPatternDemo {
    public static void main(String[] args) {
        Product product1 = Product1Factory.get().createProduct();
        Product product2 = Product2Factory.get().createProduct();
        Product product3 = Product3Factory.get().createProduct();
        product1.execute();
        product2.execute();
        product3.execute();
    }

    public static abstract class AbstractProductFactory {

        public Product createProduct() {
            commonCreate();
            return specificCreate();
        }

        private void commonCreate() {
            System.out.println("生产产品的通用逻辑");
        }

        protected abstract Product specificCreate();
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

    public static class Product1Factory extends AbstractProductFactory {
        private static final Product1Factory instance = new Product1Factory();

        @Override
        protected Product specificCreate() {
            System.out.println("生产产品1的特殊逻辑");
            return new Product1();
        }
        public static Product1Factory get() {
            return instance;
        }
    }

    public static class Product2Factory extends AbstractProductFactory {
        private static final Product2Factory instance = new Product2Factory();
        @Override
        protected Product specificCreate() {
            System.out.println("生产产品2的特殊逻辑");
            return new Product2();
        }
        public static Product2Factory get() {
            return instance;
        }
    }
    public static class Product3Factory extends AbstractProductFactory {
        private static final Product3Factory instance = new Product3Factory();
        @Override
        protected Product specificCreate() {
            System.out.println("生产产品3的特殊逻辑");
            return new Product3();
        }
        public static Product3Factory get() {
            return instance;
        }
    }
}
