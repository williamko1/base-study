package me.qinzc.hotspot;

import java.util.Arrays;

/**
 * desc : 虚拟机栈和本地方法栈溢出
 *
 * @author Zane Qin
 * creatTime : 09:33 2023/1/16
 * modifier:
 * modifyTime:
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    private void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {

        JavaVMStackSOF oom = new JavaVMStackSOF();
        int[] arr = new int[]{9, 2, 3, 4, 5, 6, 7, 8};
        int i = arr[5];
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        try

        {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }
}
