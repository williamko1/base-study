package me.qinzc.designp.state;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 13:31 2021/12/6
 * modifier:
 * modifyTime:
 */
public class StatePatternDemo {

    public static void main(String[] args) {
        Context context = new Context(new NewState());
        context.execute(1);
        context.execute(2);
        context.execute(3);
        // 适合场景，数据有状态，状态就一定会流转，从状态1变成状态2
        // 将不同的状态要执行的代码逻辑封装在不通的state类中
        // 有个context类，负责根据传入的参数，决定这份数据的状态流转到了什么状态
        // 同事负责执行那个新状态的代码逻辑
    }


    public interface State {
        void execute();
    }

    public static class NewState implements State {

        @Override
        public void execute() {
            System.out.println("执行销售出库单新建状态的逻辑");
        }
    }

    public static class ApprovingState implements State {

        @Override
        public void execute() {
            System.out.println("执行销售出库单待审批状态的逻辑");
        }
    }

    public static class ApprovedState implements State {

        @Override
        public void execute() {
            System.out.println("执行销售出库单已审批状态的逻辑");
        }
    }

    public static class FinishState implements State {

        @Override
        public void execute() {
            System.out.println("执行销售出库单完成状态的逻辑");
        }
    }

    public static class Context{

        private State state;

        public Context(State state) {
            this.state = state;
        }

        public void execute(int stateType) {
            if (stateType == 1) {
                this.state = new ApprovingState();
                this.state.execute();
            } else if(stateType == 2) {
                this.state = new ApprovedState();
                this.state.execute();
            } else if(stateType == 3) {
                this.state = new FinishState();
                this.state.execute();
            }
        }
    }

}

