package me.qinzc.designp.builder;

/**
 * desc : 没有用builder 构造器模式的示例
 *
 * @author Zane Qin
 * creatTime : 15:35 2021/12/1
 * modifier:
 * modifyTime:
 */
public class WithoutBuilderPatternDemo {

    public static void main(String[] args) {
        Product product = new Product();
        System.out.println("在设置filed1之前进行复杂的校验逻辑");
        product.setFiled1("值1");
        System.out.println("在设置filed2之前进行复杂的校验逻辑");
        product.setFiled2("值2");
        System.out.println("在设置filed3之前进行复杂的校验逻辑");
        product.setFiled3("值3");

        // 上面是简化的一个逻辑吗，实际上对于一些有十几个字段，甚至上百个字段的复杂对象的构建
        // 上面那段代码会极度膨胀，非常复杂
        // 1. 大量代码堆积，维护性非常差，可读性飞叉叉，
        // 2. 这段代码，这段逻辑，如果地方都有使用的话，一旦这段逻辑出现了一些变化，需要多个地方重构


    }

    public static class Product{
        private String filed1;
        private String filed2;
        private String filed3;

        public String getFiled1() {
            return filed1;
        }

        public void setFiled1(String filed1) {
            this.filed1 = filed1;
        }

        public String getFiled2() {
            return filed2;
        }

        public void setFiled2(String filed2) {
            this.filed2 = filed2;
        }

        public String getFiled3() {
            return filed3;
        }

        public void setFiled3(String filed3) {
            this.filed3 = filed3;
        }
    }
}
