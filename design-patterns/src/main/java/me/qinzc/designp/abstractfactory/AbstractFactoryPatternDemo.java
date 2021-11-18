package me.qinzc.designp.abstractfactory;

/**
 * desc : 抽象工厂模式
 *
 * 这种模式更复杂了，在实际开发场景中就更少见了。
 * 其核心思想是，如果需要一个工厂，这个工厂可以生产出相关联的一批产品，然后不同的工厂实现，会生产出一批不同的产品组合。
 * 这个，大家只能再这里理解一下这种思想，在阶段一，会在业务中实践一下。
 * @author Zane Qin
 * creatTime : 10:37 2021/11/18
 * modifier:
 * modifyTime:
 */
public class AbstractFactoryPatternDemo {
    public static void main(String[] args) {
        // 我们现在要创建产品A1+产品B1的组合
        ProductA productA = Factory1.get().createProductA();
        ProductB productB = Factory1.get().createProductB();
        productA.execute();
        productB.execute();

        ProductA secondProductA = Factory2.get().createProductA();
        ProductB secondProductB = Factory2.get().createProductB();
        secondProductA.execute();
        secondProductB.execute();

        ProductA thirdProductA = Factory3.get().createProductA();
        ProductB thirdProductB = Factory3.get().createProductB();
        thirdProductA.execute();
        thirdProductB.execute();
    }

    public interface ProductA {

        void execute();

    }

    public static class ProductA1 implements ProductA {

        public void execute() {
            System.out.println("产品A1的功能逻辑");
        }

    }

    public static class ProductA2 implements ProductA {

        public void execute() {
            System.out.println("产品A2的功能逻辑");
        }

    }

    public static class ProductA3 implements ProductA {

        public void execute() {
            System.out.println("产品A3的功能逻辑");
        }

    }

    public interface ProductB {

        void execute();

    }

    public static class ProductB1 implements ProductB {

        public void execute() {
            System.out.println("产品B1的功能逻辑");
        }

    }

    public static class ProductB2 implements ProductB {

        public void execute() {
            System.out.println("产品B2的功能逻辑");
        }

    }

    public static class ProductB3 implements ProductB {

        public void execute() {
            System.out.println("产品B3的功能逻辑");
        }

    }

    public interface Factory {
        ProductA createProductA();

        ProductB createProductB();
    }

    public static class Factory1 implements Factory{
        private static final Factory1 instance = new Factory1();

        @Override
        public ProductA createProductA() {
            return new ProductA1();
        }

        @Override
        public ProductB createProductB() {
            return new ProductB1();
        }

        public static Factory get() {
            return instance;
        }
    }
    public static class Factory2 implements Factory{
        private static final Factory2 instance = new Factory2();

        @Override
        public ProductA createProductA() {
            return new ProductA2();
        }

        @Override
        public ProductB createProductB() {
            return new ProductB2();
        }

        public static Factory get() {
            return instance;
        }
    }

    public static class Factory3 implements Factory{
        private static final Factory3 instance = new Factory3();

        @Override
        public ProductA createProductA() {
            return new ProductA3();
        }

        @Override
        public ProductB createProductB() {
            return new ProductB3();
        }

        public static Factory get() {
            return instance;
        }
    }
}
