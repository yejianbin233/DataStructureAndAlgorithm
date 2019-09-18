package datastructure.recursive;/**
 * Created by Administrator on 2019/9/18.
 */

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName SwapTwoNode
 * @Description 两两交换链表中的节点,不能单纯地改变节点的值 ： 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * @Date 2019/9/18 22:19
 **/
public class SwapTwoNode {
    public ListNode swapPairs(ListNode head) {
        // 递归基
        if (head == null || head.next == null) {
            // 不管 head 为 null 还是 head.next = null 同时 head != null，都是返回 head
            return head;
        }

        ListNode headNext = head.next;
        // head 维护后续的节点
        head.next = swapPairs(headNext.next);
        // head 以及 head.next 节点进行两两交换
        headNext.next = head;

        return headNext;
    }
}

