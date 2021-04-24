package me.qinzc.practice;

import java.util.Scanner;

/**
 * desc : 用数组实现循环队列
 *
 * @author : Zane Qin
 * creatTime : 14:02 2020/7/8
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

class CycleArrayQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public CycleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        front = 0;
        rear = 0;
        arr = new int[maxSize];
    }

    public void add(int item) {
        if (isFull()) {
            System.out.println("队列满");
            return;
        }
        arr[rear] = item;
        rear = ++rear % maxSize;
    }

    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        int tmp = arr[front];
        front = ++front % maxSize;
        return tmp;
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public int peak() {
        if (isEmpty()) {
            throw new RuntimeException("队列已满");
        }
        return arr[front];
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("队列空");
            return;
        }
        for (int i = front; i < (front + size()); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    public int size() {
        return (rear + maxSize - front) % maxSize;
    }
}
