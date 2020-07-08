package me.qinzc.dsalgssgg.linear.linkedlist;

/**
 * desc :
 *
 * @author : Zane Qin
 * creatTime : 14:13 2020/7/6
 * modifier:
 * modifyTime:
 */
public class LinkedListDemo {

    public static void main(String[] args) {
        // 创建节点
        HeroNode hero1 = new HeroNode(1, "松江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        SingLinkedList linkedList = new SingLinkedList();
        linkedList.addByOrder(hero4);
        linkedList.addByOrder(hero2);
        linkedList.addByOrder(hero4);
        linkedList.addByOrder(hero3);
        linkedList.addByOrder(hero1);
        linkedList.show();
        linkedList.update(new HeroNode(2, "我改了", "我改了"));
        linkedList.show();
//        linkedList.del(5);
//        linkedList.del(3);
//        linkedList.del(4);
//        linkedList.del(1);
//        linkedList.del(2);
//        linkedList.show();
        System.out.println("长度为：" + length(linkedList.getHead()));
        System.out.printf("倒数第%d个元素为：%s\n" , 3, getLastK(3, linkedList.getHead()));
    }

    /**
     * 获取 单链表中有效节点个数，去除头结点
     *
     * @return
     */
    public static int length(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    /**
     * 查找单链表中倒数第k个节点
     * @param k
     * @return
     */
    public static HeroNode getLastK(int k, HeroNode head) {
        int length = length(head);
        int index = length - k;
        if (index < 0) {
            return null;
        }
        int i = 0;
        HeroNode cur = head.next;
        while (i <= index) {
            if (i == index) {
                return cur;
            }
            i++;
            cur = cur.next;
        }
        return null;
    }
}

/**
 * 链表
 * 1. 链表是以节点的方式存储
 * 2. 每个节点包括data域，next域
 * 3. 链表的节点内存分布不一定是按照链表顺序连续的
 * 4. 链表分带头结点和没有头节点的链表
 */
class SingLinkedList {

    private HeroNode head = new HeroNode(0, "", "");

    private HeroNode end = head;

    /**
     * 添加节点到单项链表
     * 思路，不考虑编号的顺序时
     * 1. 找到当前链表的最后节点
     * 2. 将最后的节点的next指向新的节点
     * @param node
     */
    public void add(HeroNode node) {
        end.next = node;
        end = end.next;
    }

    /**
     * 添加节点到单项链表
     * 思路，考虑编号的顺序
     * 1. 先找到节点需要添加的位置
     * 2. 前序节点.next 指向 node，node.next 指向之前前序节点.next
     * @param node
     */
    public void addSorted(HeroNode node) {

        HeroNode cur = head;
        HeroNode next = head.next;
        while (next != null) {
            if (next.no == node.no) {
                System.out.printf("加入的编号%d重复\n", node.no);
                return;
            }

            if (next.no > node.no) {
                cur.next = node;
                node.next = next;
                return;
            }
            cur = next;
            next = next.next;
        }
        cur.next = node;
    }

    /**
     * 根据排名插入
     * 如果有了，添加失败给提示
     * @param node
     */
    public void addByOrder(HeroNode node) {
        HeroNode cur = head;
        boolean canAdd = true;
        while (cur.next != null) {
            if (cur.next.no > node.no) {
                break;
            } else if (cur.next.no == node.no) {
                canAdd = false;
                break;
            }
            cur = cur.next;
        }
        if (canAdd) {
            node.next = cur.next;
            cur.next = node;
        } else {
            System.out.printf("加入的编号%d重复\n", node.no);
        }
    }

    // 根据编号来修改，编号不能修改
    // 根据node.no来修改
    public void update(HeroNode node) {
        if (isEmpty()) {
            System.out.println("链表为空");
            return;
        }
        // 找到需要修改的节点，根据no
        HeroNode tmp = head.next;
        while (tmp != null) {
            if (tmp.no == node.no) {
                tmp.name = node.name;
                tmp.nickname = node.nickname;
                return;
            }
            tmp = tmp.next;
        }
        System.out.printf("没有找到编号为%d的节点\n", node.no);
    }

    /**
     * 因为是单向链表，需要找到被删除节点的前一个节点。
     * @param no
     */
    public void del(int no) {
        if (isEmpty()) {
            System.out.println("链表为空");
            return;
        }
        // 找到需要删除的节点
        HeroNode tmp = head;
        while (tmp.next != null) {
            if (tmp.next.no == no) {
                tmp.next = tmp.next.next;
                return;
            }
            tmp = tmp.next;
        }
        System.out.printf("删除失败，没有找到节点编号为%d的节点\n", no);
    }

    // 显示链表
    public void show() {
        if (isEmpty()) {
            System.out.println("链表为空");
            return;
        }
//        System.out.println(head);
        HeroNode tmp = head.next;
        while (true) {
            if (tmp == null) {
                break;
            }
            System.out.println(tmp);
            tmp = tmp.next;
        }

    }

    public boolean isEmpty() {
        return head.next == null;
    }

    public HeroNode getHead() {
        return head;
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
//                ", \nnext=" + next +
                '}';
    }
}
