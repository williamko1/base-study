package me.qinzc.dsalgssgg.linear.queue;

import java.util.Scanner;

/**
 * desc :
 *
 * @author : Zane Qin
 * creatTime : 11:08 2020/7/6
 * modifier:
 * modifyTime:
 */
public class CycleArrayQueueDemo {
    public static void main(String[] args) {

        //
        System.out.println("测试一把环形队列的案例~~");
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入队列最大长度");
        int length = Integer.valueOf(scan.next()) + 1;
        CycleArrayQueue queue = new CycleArrayQueue(length);
        char key = ' ';
        boolean loop = true;

        while (loop) {
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加元素");
            System.out.println("g(get)：从队首获取元素");
            System.out.println("p(peak)：查看队头数据");
            key = scan.next().charAt(0);
            switch (key) {
                case 's' :
                    queue.show();
                    break;
                case 'e':
                    scan.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("请输入需要添加的元素");
                    int num = Integer.valueOf(scan.next());
                    queue.add(num);
                    break;
                case 'g':
                    System.out.println("get:" + queue.get());
                    break;
                case 'p' :
                    System.out.println("peak:" + queue.peak());
                    break;
                default:
                    break;
            }
        }
    }
}

/**
 * desc : 用数组模拟的环形队列
 * 先进先出
 * 1. 有front指针表示当前队列的第一个元素, 初始为0；
 * 2. 有rear指针表示当前队列的最后一个元素的后一个位置，初始为0；
 * 3. 当队列满的条件 (rear + 1) % maxSize == front
 * 4. 当队列空的条件 rear == front
 * 5. 队列中有效数据个数 (rear + maxSize - front) % maxSize
 */
class CycleArrayQueue {
    // 队列最大容量
    private int maxSize;

    // front指针表示当前队列的第一个元素, 初始为0；
    private int front;

    // rear指针表示当前队列的最后一个元素的后一个位置，初始为0；
    private int rear;

    // 存放数据
    private int[] arr;

    public CycleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    // 进队列
    public int add(int n) {
        if (isFull()) {
            throw new RuntimeException("队列满");
        }
        arr[rear] = n;
        rear = ++rear % maxSize;
        return n;
    }

    // 出队列
    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        int val = arr[front];
        front = ++front % maxSize;
        return val;
    }

    public void show() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        for (int i = front; i < (front + size()); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    // 显示头部数据
    public int peak() {
        if (isEmpty()) {
            throw new RuntimeException("队列满");
        }
        return arr[front];
    }
}

