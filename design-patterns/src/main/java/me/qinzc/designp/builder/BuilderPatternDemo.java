package me.qinzc.designp.builder;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 15:40 2021/12/1
 * modifier:
 * modifyTime:
 */
public class BuilderPatternDemo {

    public static void main(String[] args) {
        Director director = new Director(new ConcreteBuilder());
        WithoutBuilderPatternDemo.Product product = director.build("值1", "值2", "值3");
        // 好处1： 通过builder接口将复杂构建步骤拆分成了多个部分，代码逻辑清晰，维护性和扩展性都很好
        // 好处2： 将对象构造的过程，封装在了director里面，director来基于builder进行构建，构造逻辑需改，不需要修改
        // 好处3： 相对于工厂，有一个很好的抽象设计，director和builder
    }

    public interface Builder {
        void field1(String value);
        void field2(String value);
        void field3(String value);
        WithoutBuilderPatternDemo.Product create();
    }

    public static class ConcreteBuilder implements Builder {

        private WithoutBuilderPatternDemo.Product product = new WithoutBuilderPatternDemo.Product();

        @Override
        public void field1(String value) {
            System.out.println("在设置filed1之前进行复杂的校验逻辑");
            product.setFiled1(value);
        }

        @Override
        public void field2(String value) {
            System.out.println("在设置filed2之前进行复杂的校验逻辑");
            product.setFiled2(value);
        }

        @Override
        public void field3(String value) {
            System.out.println("在设置filed3之前进行复杂的校验逻辑");
            product.setFiled3(value);
        }

        @Override
        public WithoutBuilderPatternDemo.Product create() {
            return product;
        }
    }

    /**
     * director面向builder接口来编程的
     * director可以复杂控制构建的一个步骤，具体的每个步骤的逻辑封装在具体的builder类中
     * 如果我们此时要更换一整套的步骤，可以再搞一个builder类
     * 但是我们的整个的构建步骤是没有任何改变的
     *
     * 如果整个构建步骤变化了，但是对构建的逻辑是没有影响的
     */
    public static class Director {
        public Builder builder;

        public Director(Builder builder) {
            this.builder = builder;
        }

        public WithoutBuilderPatternDemo.Product build(String field1, String field2, String field3) {
            builder.field1(field1);
            builder.field2(field2);
            builder.field3(field3);
            return builder.create();
        }
    }


}
