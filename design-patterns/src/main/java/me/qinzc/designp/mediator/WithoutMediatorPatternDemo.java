package me.qinzc.designp.mediator;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 16:16 2021/12/1
 * modifier:
 * modifyTime:
 */
public class WithoutMediatorPatternDemo {

    public static void main(String[] args) {
        ModuleA moduleA = new ModuleA();
        ModuleB moduleB = new ModuleB();
        ModuleC moduleC = new ModuleC();
        moduleA.execute();
        moduleB.execute();
        moduleC.execute();

        // 模块之间有非常复杂的相互调用
        // 每个模块都要去care很多其他的模块，相互耦合严重
        // 后面修改代码，不好改
    }

    public static class ModuleA {

        public void execute() {
            System.out.println("调用模块A中的功能");
            ModuleB moduleB = new ModuleB();
            ModuleC moduleC = new ModuleC();
            moduleB.execute("模块A");
            moduleC.execute("模块A");
        }

        public void execute(String invoker) {
            System.out.println(invoker + "在调用模块A的功能");
        }
    }

    public static class ModuleB {

        public void execute() {
            System.out.println("调用模块B中的功能");
            ModuleA moduleA = new ModuleA();
            ModuleC moduleC = new ModuleC();
            moduleA.execute("模块B");
            moduleC.execute("模块B");
        }
        public void execute(String invoker) {
            System.out.println(invoker + "在调用模块B的功能");
        }
    }

    public static class ModuleC {

        public void execute() {
            System.out.println("调用模块C中的功能");
            ModuleA moduleA = new ModuleA();
            ModuleB moduleB = new ModuleB();
            moduleA.execute("模块C");
            moduleB.execute("模块C");
        }
        public void execute(String invoker) {
            System.out.println(invoker + "在调用模块C的功能");
        }
    }
}
