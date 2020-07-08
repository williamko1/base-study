package me.qinzc.algorithm.linkedlist;

import java.util.LinkedList;

/**
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class No206ReverseLinkedList {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        System.out.println(node1);
//        ListNode listNode = reverseList_2(node1);
//        System.out.println(node5);
        System.out.println(reverseList_3(node1));
    }

    public static ListNode reverseList(ListNode head) {
        //申请节点，pre和 cur，pre指向null
        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp = null;
        while(cur!=null) {
            //记录当前节点的下一个节点
            tmp = cur.next;
            //然后将当前节点指向pre
            cur.next = pre;
            //pre和cur节点都前进一位
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    public static ListNode reverseList_2(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode cur = reverseList_2(head.next);
        head.next.next = head;
        head.next = null;
        return cur;
    }

    /**
     * 创建一个新的链表，遍历原来的链表，每次插入到新链表的头结点
     * 最后原来链表的头结点.next 指向新链表的第一个元素
     * @param head
     * @return
     */
    public static ListNode reverseList_3(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        // 定义一个辅助变量，帮助我们遍历原来的链表
        ListNode pre = new ListNode(0);
        ListNode cur = head;
        ListNode next = null;
        // 遍历原链表，每遍历一个节点，就将其取出，并存放到pre的最前端
        while (cur != null) {
            // 暂时保存当前节点的下一节点
            next = cur.next;
            // 将cur的next指向新链表的下一个
            cur.next = pre.next;
            // 将cur连接到新的链表上
            pre.next = cur;
            cur = next;
        }
        return pre.next;
    }

}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
