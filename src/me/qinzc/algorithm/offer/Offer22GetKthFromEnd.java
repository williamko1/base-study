package me.qinzc.algorithm.offer;

/**
 * desc : 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 * <p>
 * 返回链表 4->5.
 *
 * @author : Zane Qin
 * creatTime : 17:12 2020/7/6
 * modifier:
 * modifyTime:
 */
public class Offer22GetKthFromEnd {


    /**
     * 双指针，前指针先走k步，之后双指针一起移动，当前指针走到链表末端，后指针就是所需元素
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd_best(ListNode head, int k) {
        ListNode first = head;
        ListNode second = head;
        for (int i = 0; i < k; i++) {
            if (first == null) {
                return null;
            }
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        return second;
    }

    /**
     * 自己的思路， 先算链表长度，长度-k，得到index
     * 时间复杂度O(2n -k)
     * 空间复杂度O(1)
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        int size = length(head);
        int index = size - k;
        if (k < 1 || index < 0) {
            return null;
        }
        ListNode tmp = head;
        for (int i = 0; i <= index; i++) {
            if (i == index) {
                return tmp;
            }
            tmp = tmp.next;
        }
        return null;
    }

    public int length(ListNode head) {
        if (head == null) {
            return 0;
        }
        int size = 0;
        ListNode tmp = head;
        while (tmp != null) {
            size++;
            tmp = tmp.next;
        }
        return size;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
