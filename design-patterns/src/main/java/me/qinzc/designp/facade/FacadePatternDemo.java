package me.qinzc.designp.facade;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 11:23 2021/11/18
 * modifier:
 * modifyTime:
 */
public class FacadePatternDemo {

    public static void main(String[] args) {
        // 假设是子系统2
        SystemFacade systemFacade = new SystemFacade();
        systemFacade.execute();

        // 好处1： 子系统2不需要care太多的模块，只要care一个门面类的模块
        // 好处2： 如果多个地方都要调用这段逻辑，那么如果这个逻辑变了，只需要在门面类的一个地方修改就可以了
    }


    /**
     * 子系统1的门面类
     */
    public static class SystemFacade {
        public void execute() {
            WithoutFacadePatternDemo.ModuleA moduleA = new WithoutFacadePatternDemo.ModuleA();
            WithoutFacadePatternDemo.ModuleB moduleB = new WithoutFacadePatternDemo.ModuleB();
            WithoutFacadePatternDemo.ModuleC moduleC = new WithoutFacadePatternDemo.ModuleC();
            moduleA.execute();
            moduleB.execute();
            moduleC.execute();
        }
    }
}

