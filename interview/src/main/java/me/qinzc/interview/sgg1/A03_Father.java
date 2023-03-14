package me.qinzc.interview.sgg1;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 10:47 2023/3/14
 * modifier:
 * modifyTime:
 */
public class A03_Father {

    private int i = test();
    private static int j = method();

    static{
        System.out.println("1");
    }
    A03_Father() {
        System.out.println("2");
    }

    {
        System.out.println("3");
    }

    public int test() {
        System.out.println(4);
        return 1;
    }

    public static int method() {
        System.out.println(5);
        return 1;
    }
}
