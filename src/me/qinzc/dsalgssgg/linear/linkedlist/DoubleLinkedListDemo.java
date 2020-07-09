package me.qinzc.dsalgssgg.linear.linkedlist;

/**
 * desc :
 *
 * @author : Zane Qin
 * creatTime : 17:40 2020/7/8
 * modifier:
 * modifyTime:
 */
public class DoubleLinkedListDemo {
}

class DoubleLinkedList {
    private HeroNode2 head = new HeroNode2(0, "", "");
    private HeroNode2 end = head;

    public void add() {

    }

    public void update() {

    }

    public void del() {

    }

    public void show() {
        if (isEmpty()) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 tmp = head.next;
        if (tmp != null) {
            System.out.println(tmp);
            tmp = tmp.next;
        }
    }

    public boolean isEmpty() {
        return head.next == null;
    }

    public HeroNode2 getHead() {
        return head;
    }

    public HeroNode2 getEnd() {
        return end;
    }
}

class HeroNode2 {
    int no;
    String name;
    String nickname;
    HeroNode2 next;
    HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}