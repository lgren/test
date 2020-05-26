package com.lgren.leetCode;

import org.junit.Test;

/**
 * 2. 两数相加
 * https://leetcode-cn.com/problems/add-two-numbers/
 * @author lgren
 * @since 2020-05-22 2:25 下午
 */
public class Q0002M {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(-1);
        ListNode nodeT = node;
        ListNode l1T = l1;
        ListNode l2T = l2;
        int carry = 0;
        while (l1T != null || l2T != null || carry != 0) {
            int val = carry + (l1T != null ? l1T.val : 0) + (l2T != null ? l2T.val : 0);
            nodeT = nodeT.next = new ListNode(val % 10);
            carry = val / 10;
            l1T = (l1T != null ? l1T.next : null);
            l2T = (l2T != null ? l2T.next : null);
        }
        return node.next;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
    }

    @Test
    public void test1() {
        ListNode node1 = new ListNode(5);
        // node1.next = new ListNode(4);
        // node1.next.next = new ListNode(3);

        ListNode node2 = new ListNode(5);
        // node2.next = new ListNode(6);
        // node2.next.next = new ListNode(4);

        ListNode listNode = addTwoNumbers(node1, node2);
        ListNode listNodeT = listNode;
        while (listNodeT != null) {
            System.out.printf(String.valueOf(listNodeT.val));
            listNodeT = listNodeT.next;
        }
    }
}
