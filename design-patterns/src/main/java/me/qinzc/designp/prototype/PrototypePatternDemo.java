package me.qinzc.designp.prototype;

import java.util.StringJoiner;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 16:05 2021/12/1
 * modifier:
 * modifyTime:
 */
public class PrototypePatternDemo {

    public static void main(String[] args) {

        Product product = new Product("测试产品", new Component("测试组件"));
        try {
            Product copyProduct = (Product) product.clone();
            System.out.println(copyProduct);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        // 原型模式，就是在要拷贝的类理实现一个clone()方法，自己拷贝自己
        // 浅拷贝，和深拷贝
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

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return new Component(this.getName());
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

        @Override
        protected Object clone() throws CloneNotSupportedException {
            // 浅拷贝
//            return new Product(this.getName(), this.getComponent());
            // 深拷贝
            return new Product(this.getName(), (Component) this.getComponent().clone());
        }
    }
}
