package me.qinzc.online;

/**
 * desc : 计算器
 * 计算器
 * 四则运算
 *
 * 拓展性
 * @author Zane Qin
 * creatTime : 09:44 2023/4/23
 * modifier:
 * modifyTime:
 */
public class Main {

    // 扩展的话需要维护一个枚举类，调用端需要知道枚举值
    // 添加计算策略
    // 添加计算策略工厂
    public static void main(String[] args) {
        Double n1 = 5.2;
        Double n2 = 3.8;
        // 初始化时候指定计算器
        // 指定加法计算器
        CalculatorContext calculatorContext = new CalculatorContext(CalculateStrategyFactory.getCalculator(CalEnum.ADD));

        Double calculate = calculatorContext.calculate(n1, n2);
        // 指定减法计算器
        calculatorContext.setCalculate(CalculateStrategyFactory.getCalculator(CalEnum.MINUS));
        Double result2 = calculatorContext.calculate(n1, n2);

    }

    public interface Calculate{

        Double calculate(Double n1, Double n2);
    }

    public static class CalculateStrategyFactory {


        public static Calculate getCalculator(CalEnum calEnum) {
            if (calEnum.equals(CalEnum.ADD)) {
                return new AddCal();
            } else if (calEnum.equals(CalEnum.MINUS)) {
                return new MinusCal();
            } else if (calEnum.equals(CalEnum.MUILTI)) {
                return new MuiltiCal();
            } else if (calEnum.equals(CalEnum.DIVIDE)) {
                return new DivideCal();
            }
            return null;
        }
    }


    public enum CalEnum{
        ADD,MINUS,MUILTI,DIVIDE;

        CalEnum() {
        }
    }

    public static class AddCal implements Calculate {

        @Override
        public Double calculate(Double n1, Double n2) {
            return n1 + n2;
        }
    }

    public static class MinusCal implements Calculate {

        @Override
        public Double calculate(Double n1, Double n2) {
            return n1 - n2;
        }
    }

    public static class MuiltiCal implements Calculate {

        @Override
        public Double calculate(Double n1, Double n2) {
            return n1 * n2;
        }
    }

    public static class DivideCal implements Calculate{

        @Override
        public Double calculate(Double n1, Double n2) {
            return n1/n2;
        }
    }



    public static class CalculatorContext {
        private Calculate calculate;

        public CalculatorContext(Calculate calculate) {
            this.calculate = calculate;
        }

        public Calculate getCalculate() {
            return calculate;
        }

        public void setCalculate(Calculate calculate) {
            this.calculate = calculate;
        }

        public Double calculate(Double n1, Double n2) {
            return calculate.calculate(n1, n2);
        }
    }
}
