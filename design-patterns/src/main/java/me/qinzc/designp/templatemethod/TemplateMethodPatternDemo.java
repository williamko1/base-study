package me.qinzc.designp.templatemethod;

/**
 * desc : 模板方法模式
 *
 * 这个模式一定要多用，尤其是对于有多重不同实现的一些场景，比如说，不同的折扣类型，不同的优惠券类型，不同的商品类型，
 * 那肯定涉及到不同的处理逻辑。但是可以将他们共同的基础逻辑抽取到父类中，然后将要子类实现的逻辑留空，交给子类去实现。
 *
 * 这是最高频使用的设计模式
 * @author Zane Qin
 * creatTime : 14:37 2021/11/17
 * modifier:
 * modifyTime:
 */
public class TemplateMethodPatternDemo {

    public static void main(String[] args) {
        DiscountCalculator1 calculator1 = new DiscountCalculator1();
        calculator1.calculate();
        DiscountCalculator2 calculator2 = new DiscountCalculator2();
        calculator2.calculate();
        DiscountCalculator3 calculator3 = new DiscountCalculator3();
        calculator3.calculate();
    }

    public interface DiscountCalculator {
        void calculate();
    }

    /**
     * 模板方法实现
     */
    public static abstract class AbstractDiscountCalculator implements DiscountCalculator {

        public void calculate() {
            // 完成通用的计算逻辑
            commonCalculate();
            // 完成特殊的计算逻辑
            specificCalculate();
        }

        private void commonCalculate() {
            System.out.println("修改通用的计算逻辑");
        }

        protected abstract void specificCalculate();
    }

    public static class DiscountCalculator1 extends AbstractDiscountCalculator{
        @Override
        protected void specificCalculate() {
            System.out.println("优惠计算器1的计算逻辑");
        }
    }

    public static class DiscountCalculator2 extends AbstractDiscountCalculator{
        @Override
        protected void specificCalculate() {
            System.out.println("优惠计算器2的计算逻辑");
        }
    }
    public static class DiscountCalculator3 extends AbstractDiscountCalculator{
        @Override
        protected void specificCalculate() {
            System.out.println("优惠计算器3的计算逻辑");
        }
    }
}
