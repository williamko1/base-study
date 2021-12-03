package me.qinzc.designp.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 14:41 2021/12/3
 * modifier:
 * modifyTime:
 */
public class CompositePatternDemo {

    public static void main(String[] args) {
        Department leaf1 = new Department("叶子部门1");
        Department leaf2 = new Department("叶子部门2");
        Department leaf3 = new Department("叶子部门3");

        Department sub1 = new Department("子部门1");
        Department sub2 = new Department("子部门2");

        sub1.getChildren().add(leaf1);
        sub1.getChildren().add(leaf2);

        sub2.getChildren().add(leaf3);

        Department parent = new Department("父部门");

        parent.getChildren().add(sub1);
        parent.getChildren().add(sub2);

        parent.remove();
        // 组合模式第一要义，就是将树形结构的数据，用一个类，或者少数1，2个类，就可以拼装成一棵树的形状
        // 组合模式第二要以，就是直接对一个父级的数据执行某个操作，这个操作会直接递归调用所有下层的子数据的相关操作
        // 通过这个树形结构递归自己的方式，就将一棵树的操作执行了。
        // 好处，对属性数据的操作，不需调用放组织一个 嵌套循环
    }

    public static class Department {

        private String name;
        private List<Department> children = new ArrayList<>();

        public Department(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Department> getChildren() {
            return children;
        }

        public void setChildren(List<Department> children) {
            this.children = children;
        }

        public void remove() {
            if (children.size() > 0) {
                for (Department child : children) {
                    child.remove();
                }
            }
            System.out.println("删除" + name);
        }
    }
}
