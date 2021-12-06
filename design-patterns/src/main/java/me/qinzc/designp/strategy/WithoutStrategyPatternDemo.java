package me.qinzc.designp.strategy;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 13:13 2021/12/6
 * modifier:
 * modifyTime:
 */
public class WithoutStrategyPatternDemo {
    public static void main(String[] args) {
        if ("1".endsWith(args[0])) {
            System.out.println("优惠计价1");
        } else if ("2".endsWith(args[0])) {
            System.out.println("优惠计价2");
        } else if ("3".endsWith(args[0])) {
            System.out.println("优惠计价3");
        }
    }
}
