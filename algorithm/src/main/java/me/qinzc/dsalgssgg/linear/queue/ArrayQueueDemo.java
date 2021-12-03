package me.qinzc.dsalgssgg.linear.queue;

/**
 *
 * @author : Zane Qin
 * creatTime : 17:03 2020/7/3
 * modifier:
 * modifyTime:
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(10);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
        queue.add(6);
        queue.add(7);
        queue.add(8);
        queue.add(9);
        queue.add(10);
        queue.show();
        System.out.println(queue.get());
        queue.show();
        System.out.println(queue.peak());
        queue.show();
        System.out.println(queue.get());
        System.out.println(queue.get());
        System.out.println(queue.get());
        queue.show();

    }

}
/**
 * desc : 用数组模拟的队列
 * 缺点：数组只能用一次，用过就不能用了
 * 先进先出
 * 有front指针表示当前队列的第一个元素的前一个位置
 * 有rear指针表示当前队列的最右一个元素的位置
 */
class ArrayQueue {
    // 队列最大容量
    private int maxSize;

    // 指针表示当前出去的下标
    private int front;

    // 指针表示当前进入的下标
    private int rear;

    // 存放数据
    private int[] arr;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        // 指向队列头部并不包含（数据的前一个位置）
        front = -1;
        // 指向队列尾部并包含（数据的当前位置）
        rear = -1;
    }

    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    // 进队列
    public int add(int n) {
        if (isFull()) {
            throw new RuntimeException("队列满");
        }
        arr[++rear] = n;
        return n;
    }

    // 出队列
    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        return arr[++front];
    }

    public void show() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        for (int i = front + 1; i < rear + 1; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }
    // 显示头部数据
    public int peak() {
        if (isEmpty()) {
            throw new RuntimeException("队列满");
        }
        return arr[front + 1];
    }
}
