package me.qinzc.designp.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 14:35 2021/12/3
 * modifier:
 * modifyTime:
 */
public class WithoutCompositePatternDemo {

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

        // 层级关系，很不方便，嵌套很深
        for (Department child : parent.getChildren()) {
            if (child.getChildren().size() > 0) {
                for (Department leaf : child.getChildren()) {
                    leaf.remove();
                }
            }
        }
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
            System.out.println("删除" + name);
        }
    }
}

