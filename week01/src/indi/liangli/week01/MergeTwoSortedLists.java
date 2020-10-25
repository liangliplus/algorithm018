//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 
//
// 示例： 
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4
// 
// Related Topics 链表 
// 👍 1341 👎 0


package indi.liangli.week01;

public class MergeTwoSortedLists {
    public static void main(String[] args) {

        Solution solution = new MergeTwoSortedLists().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            //解法1：迭代 声明一个头节点，头节点，然后比较l1 和 l2.val
            //解法2：递归 l1.next = mergeTwoLists
            if (l1 == null) {
                return l2;
            } else if (l2 == null) {
                return l1;
            } else {
                if (l1.val < l2.val) {
                    l1.next = mergeTwoLists(l1.next, l2);
                    return l1;
                } else {
                    l2.next = mergeTwoLists(l1, l2.next);
                    return l2;
                }
            }

        /*ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev =  prev.next;
        }

        prev.next = l1 == null ? l2 : l1;

        return dummy.next; */
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}