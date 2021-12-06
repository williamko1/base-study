package me.qinzc.designp.strategy;

/**
 * desc :
 * 要点1. 必须将if else 的代码，封装到不同的策略类中
 * 要点2. 将选择哪种策略的逻辑给放到一个工厂类中，选择策略的代码务必很简洁
 * 要点3. context可有可无，具体是看你的策略执行这块如果一行代码调用，不需要，如果context中策略的执行逻辑较为复杂一点，就封装一个context
 *
 * @author Zane Qin
 * creatTime : 13:17 2021/12/6
 * modifier:
 * modifyTime:
 */
public class StrategyPatternDemo {

    public static void main(String[] args) {
        int discountStyle = 2;
        DiscountCalculateStrategy strategy = DiscountCalculateStrategyFactory.getDiscountCalculateStrategy(discountStyle);
        Context context = new Context();
        context.setStrategy(strategy);
        context.calculate();
    }

    public interface DiscountCalculateStrategy {
        void calculate();
    }

    public static class DiscountCalculateStrategyA implements DiscountCalculateStrategy {

        @Override
        public void calculate() {
            System.out.println("优惠1");
        }
    }

    public static class DiscountCalculateStrategyB implements DiscountCalculateStrategy {

        @Override
        public void calculate() {
            System.out.println("优惠2");
        }
    }

    public static class DiscountCalculateStrategyC implements DiscountCalculateStrategy {

        @Override
        public void calculate() {
            System.out.println("优惠3");
        }
    }

    public static class DiscountCalculateStrategyDefault implements DiscountCalculateStrategy {

        @Override
        public void calculate() {
            System.out.println("默认优惠策略");
        }
    }

    public static class DiscountCalculateStrategyFactory {

        public static DiscountCalculateStrategy getDiscountCalculateStrategy(int discountStyle) {
            if (discountStyle == 1) {
                return new DiscountCalculateStrategyA();
            } else if (discountStyle == 2) {
                return new DiscountCalculateStrategyB();
            } else if (discountStyle == 3) {
                return new DiscountCalculateStrategyC();
            } else {
                return new DiscountCalculateStrategyDefault();
            }
        }
    }

    public static class Context {

        private DiscountCalculateStrategy strategy;

        public DiscountCalculateStrategy getStrategy() {
            return strategy;
        }

        public void setStrategy(DiscountCalculateStrategy strategy) {
            this.strategy = strategy;
        }

        public void calculate() {
            strategy.calculate();
        }
    }

}
