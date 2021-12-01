package me.qinzc.designp.builder;

import me.qinzc.designp.builder.WithoutBuilderPatternDemo.Product;
/**
 * desc : 开源框架都是这种变种
 *
 * @author Zane Qin
 * creatTime : 15:53 2021/12/1
 * modifier:
 * modifyTime:
 */
public class OptimizeBuilderPatternDemo {

    public static void main(String[] args) {
        Product product = new ConcreteBuilder()
                .field1("值1")
                .field2("值2")
                .field3("值3")
                .create();
        System.out.println(product);
    }
    public interface Builder {
        Builder field1(String value);
        Builder field2(String value);
        Builder field3(String value);
        Product create();
    }

    public static class ConcreteBuilder implements Builder {

        private Product product = new Product();

        @Override
        public Builder field1(String value) {
            System.out.println("在设置filed1之前进行复杂的校验逻辑");
            product.setFiled1(value);
            return this;
        }

        @Override
        public Builder field2(String value) {
            System.out.println("在设置filed2之前进行复杂的校验逻辑");
            product.setFiled2(value);
            return this;
        }

        @Override
        public Builder field3(String value) {
            System.out.println("在设置filed3之前进行复杂的校验逻辑");
            product.setFiled3(value);
            return this;
        }

        @Override
        public Product create() {
            return product;
        }
    }
}
