package me.qinzc.designp.templatemethod;

/**
 * desc : 不用模板方法实现
 *
 * 问题： 三种优惠方式计算器里面都有一段通用的计算逻辑，是完全相同的代码
 * 但是相同的一段代码，通过复制黏贴的方式，放到了不同的类中去
 * 一旦说，那段通用的计算逻辑，要修改，就涉及到多个勒种都要去修改那个代码
 * 会造成忘记修改某个类中的那段代码
 * 到了后期，几乎没人记得清楚，那段通用逻辑代码放在了多少个类中，如果要排查，需要将很多类重新读一遍
 * @author Zane Qin
 * creatTime : 14:32 2021/11/17
 * modifier:
 * modifyTime:
 */
public class WithoutTemplateMethodPatternDemo {


    public static void main(String[] args) {
        DiscountCalculator1 calculator1 = new DiscountCalculator1();
        calculator1.calculate();
        DiscountCalculator1 calculator2 = new DiscountCalculator1();
        calculator2.calculate();
        DiscountCalculator1 calculator3 = new DiscountCalculator1();
        calculator2.calculate();
    }

    public static class DiscountCalculator1 {
        public void calculate() {
            System.out.println("通用的计算逻辑");
            System.out.println("优惠计算器1的计算逻辑");
        }
    }

    public static class DiscountCalculator2 {
        public void calculate() {
            System.out.println("通用的计算逻辑");
            System.out.println("优惠计算器2的计算逻辑");
        }
    }

    public static class DiscountCalculator3 {
        public void calculate() {
            System.out.println("通用的计算逻辑");
            System.out.println("优惠计算器3的计算逻辑");
        }
    }
}
