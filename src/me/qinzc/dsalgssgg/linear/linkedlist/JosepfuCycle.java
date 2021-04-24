package me.qinzc.dsalgssgg.linear.linkedlist;

/**
 * desc :
 *
 * @author : Zane Qin
 * creatTime : 17:20 2020/7/24
 * modifier:
 * modifyTime:
 */
public class JosepfuCycle {
    public static void main(String[] args) {
        CycleSingleLinkedList cycle = new CycleSingleLinkedList();
        cycle.add(10);
        cycle.show();
    }

}

class CycleSingleLinkedList {
    // 创建first
    private Boy first = null;

    public void add(int num) {
        if (num < 1) {
            System.out.println("值不正确");
            return;
        }
        // 辅助指针，帮助构建
        Boy cur = null;
        for (int i = 1; i <= num; i++) {
            // 根据编号创建小孩
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);
                cur = first;
            } else {
                cur.setNext(boy);
                boy.setNext(first);
                cur = boy;
            }
        }
    }

    public void show() {
        if (first == null) {
            System.out.println("队列为空");
        }
        Boy cur = first;
        while (true) {

            System.out.println(cur.getNo());
            if (cur.getNext() == first) {
                break;
            }
            cur = cur.getNext();
        }
    }
}

class Boy{
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public Boy setNo(int no) {
        this.no = no;
        return this;
    }

    public Boy getNext() {
        return next;
    }

    public Boy setNext(Boy next) {
        this.next = next;
        return this;
    }
}
