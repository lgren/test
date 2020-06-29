package com.lgren.leetcode.medium;

/**
 * 2.两数相加
 * https://leetcode-cn.com/problems/add-two-numbers/
 * @author lgren
 * @create 2019-05-16 09:05
 **/
public class AddTwoNumbers {
    public static class ListNode {
        int val;
        public ListNode next;

        public ListNode(int x) { val = x; }

        @Override
        public String toString() {
            return "{" +
                    "\"val\":" + val +
                    ",\"next\":" + next +
                    "}";
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode tmp = new ListNode(0), tmp1 = l1, tmp2 = l2;
        ListNode result = tmp;
        int carry = 0;
        while (carry > 0 || tmp1 != null || tmp2 != null) {
            int num1 = tmp1 == null ? 0 : tmp1.val, num2 = tmp2 == null ? 0 : tmp2.val;
            tmp.next = new ListNode((num1 + num2 + carry) % 10);
            carry = (num1 + num2 + carry) / 10;
            tmp = tmp.next;
            if (tmp1 != null) tmp1 = tmp1.next;
            if (tmp2 != null) tmp2 = tmp2.next;
        }
        return result.next;
    }


    public static void main(String[] args) {
        AddTwoNumbers.ListNode l1 = new AddTwoNumbers.ListNode(2);
        l1.next = new AddTwoNumbers.ListNode(4);
        l1.next.next = new AddTwoNumbers.ListNode(3);
        AddTwoNumbers.ListNode l2 = new AddTwoNumbers.ListNode(5);
        l2.next = new AddTwoNumbers.ListNode(6);
        l2.next.next = new AddTwoNumbers.ListNode(4);
        System.out.println(AddTwoNumbers.addTwoNumbers(l1, l2));
    }
}
