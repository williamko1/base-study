package me.qinzc.designp.prototype;

import java.util.StringJoiner;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 16:01 2021/12/1
 * modifier:
 * modifyTime:
 */
public class WithoutPrototypePatternDemo {

    public static void main(String[] args) {
        Product product = new Product("测试产品", new Component("测试组件"));
        // 手动拷贝
        Product copyProduct = new Product(product.getName(), product.getComponent());
        System.out.println(copyProduct);

        // 问题是什么
        // 代码的拷贝逻辑，是每个要拷贝的调用方自己来实现的
        // 相同的拷贝逻辑会分散在很多不同的地方，如果拷贝逻辑变了，多个调用的地方都要修改地方
        // 可为维护性，扩展性差
    }

    public static class Component {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Component.class.getSimpleName() + "[", "]")
                    .add("name='" + name + "'")
                    .toString();
        }

        public Component(String name) {
            this.name = name;
        }
    }

    public static class Product {
        private String name;
        private Component component;

        public Product(String name, Component component) {
            this.name = name;
            this.component = component;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Component getComponent() {
            return component;
        }

        public void setComponent(Component component) {
            this.component = component;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
                    .add("name='" + name + "'")
                    .add("component=" + component)
                    .toString();
        }
    }

}

