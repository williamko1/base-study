package me.qinzc.designp.mediator;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 16:25 2021/12/1
 * modifier:
 * modifyTime:
 */
public class MediatorPatternDemo {

    public static void main(String[] args) {
        Mediator mediator = new Mediator();

        ModuleA moduleA = new ModuleA(mediator);
        ModuleB moduleB = new ModuleB(mediator);
        ModuleC moduleC = new ModuleC(mediator);

        moduleA.execute();
        moduleB.execute();
        moduleC.execute();

        // 好处在哪
        // moduleA 只要知道一个中介者就可以了，具体跟其他模块的交互都封装在中介者里了
        // moduleB,moduleC 同上
        // moduleA,B,C 之间不再有任何耦合，不再有复杂的交互关系，相互之间修改也不会对对方产生影响
    }


    public static class Mediator {

        private ModuleA moduleA;
        private ModuleB moduleB;
        private ModuleC moduleC;

        public ModuleA getModuleA() {
            return moduleA;
        }

        public void setModuleA(ModuleA moduleA) {
            this.moduleA = moduleA;
        }

        public ModuleB getModuleB() {
            return moduleB;
        }

        public void setModuleB(ModuleB moduleB) {
            this.moduleB = moduleB;
        }

        public ModuleC getModuleC() {
            return moduleC;
        }

        public void setModuleC(ModuleC moduleC) {
            this.moduleC = moduleC;
        }

        public void executeModuleA() {
            moduleB.execute("模块A通知中介者");
            moduleC.execute("模块A通知中介者");
        }

        public void executeModuleB() {
            moduleA.execute("模块B通知中介者");
            moduleC.execute("模块B通知中介者");
        }

        public void executeModuleC() {
            moduleA.execute("模块C通知中介者");
            moduleB.execute("模块C通知中介者");
        }
    }


    public static class ModuleA {

        private Mediator mediator;

        public ModuleA(Mediator mediator) {
            this.mediator = mediator;
            mediator.moduleA = this;
        }

        public void execute() {
           mediator.executeModuleA();
        }

        public void execute(String invoker) {
            System.out.println(invoker + "在调用模块A的功能");
        }
    }

    public static class ModuleB {

        private Mediator mediator;

        public ModuleB(Mediator mediator) {
            this.mediator = mediator;
            mediator.moduleB = this;
        }

        public void execute() {
            mediator.executeModuleB();
        }
        public void execute(String invoker) {
            System.out.println(invoker + "在调用模块B的功能");
        }
    }

    public static class ModuleC {

        private Mediator mediator;

        public ModuleC(Mediator mediator) {
            this.mediator = mediator;
            mediator.moduleC = this;
        }

        public void execute() {
            mediator.executeModuleC();
        }
        public void execute(String invoker) {
            System.out.println(invoker + "在调用模块C的功能");
        }
    }
}
