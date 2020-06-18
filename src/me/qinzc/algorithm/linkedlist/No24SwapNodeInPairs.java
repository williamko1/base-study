package me.qinzc.algorithm.linkedlist;

/**
 * desc :
 *
 * @author : Zane Qin
 * creatTime : 17:46 2020/6/18
 * modifier:
 * modifyTime:
 */
public class No24SwapNodeInPairs {

    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        ListNode listNode = swapPairs(one);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }

    }

    public static ListNode swapPairs(ListNode head) {
        // 模拟前节点
        ListNode fakeNode = new ListNode(0);
        fakeNode.next = head;
        ListNode pre = fakeNode;
        while(pre.next != null && pre.next.next != null) {
            ListNode start = pre.next;
            ListNode end = pre.next.next;

            // swap
            pre.next = end;
            start.next = end.next;
            end.next = start;

            // 初始化下次交换
            pre = start;
        }
        return fakeNode.next;
    }
    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
}
