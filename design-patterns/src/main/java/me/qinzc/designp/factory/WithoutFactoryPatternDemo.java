package me.qinzc.designp.factory;

/**
 * desc : 不用设计模式
 * 问题：
 * 如果直接面向一个类来编程，new来创建实例的话，会死很惨
 * 我们如果对product要更换一个类的实现，
 * 此时就必须在创建这个类实例的地方，都要修改一下这个代码
 * 如果有100个创建的地方，都需要改
 * @author Zane Qin
 * creatTime : 14:57 2021/11/17
 * modifier:
 * modifyTime:
 */
public class WithoutFactoryPatternDemo {

    public static void main(String[] args) {
        Product product = new Product();
        System.out.println(product);

    }

    public static class Product {

        private String name;

        public String getName() {
            return name;
        }

        public Product setName(String name) {
            this.name = name;
            return this;
        }
    }

}
