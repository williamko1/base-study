package me.qinzc.designp.facade;

/**
 * desc :
 *
 * 问题
 * 1. 对于子系统1来说，维护成本太高了，要care多个子系统2的模块。
 * 2. 多个模块组成的功能，在自行他1的多个地方都使用到了，那么那段代码会在多个地方有重复，维护成本高
 * @author Zane Qin
 * creatTime : 11:19 2021/11/18
 * modifier:
 * modifyTime:
 */
public class WithoutFacadePatternDemo {

    public static void main(String[] args) {
        // 假设这里是子系统2，要基于子系统1的三个模块实现一个业务逻辑
        ModuleA moduleA = new ModuleA();
        ModuleB moduleB = new ModuleB();
        ModuleC moduleC = new ModuleC();
        moduleA.execute();
        moduleB.execute();
        moduleC.execute();
    }

    public static class ModuleA {

        public void execute() {
            System.out.println("子系统1的模块A功能");
        }
    }

    public static class ModuleB {

        public void execute() {
            System.out.println("子系统1的模块B功能");
        }
    }

    public static class ModuleC {

        public void execute() {
            System.out.println("子系统1的模块C功能");
        }
    }


}

