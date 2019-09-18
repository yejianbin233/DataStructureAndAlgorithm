package datastructure.recursive;/**
 * Created by Administrator on 2019/9/17.
 */

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName Leetcode203
 * @Description Leetcode 203 移除链表元素
 * @Date 2019/9/17 22:10
 **/
public class Leetcode203 {


    public static ListNode removeElements(ListNode head, int val) {
        // 递归基 -> 遍历完链表后返回
        if (head == null) {
            return null;
        }

        // 移除链表后续仍存在的 val
        ListNode retNode = removeElements(head.next,val);
        // 删除链表中的 val 值，每次仅处理一个节点
        if (head.val == val) {
            // 移除当前节点返回后续的节点
            return retNode;
        } else {
            // 维护当前节点与后续的节点
            head.next = retNode;
            return head;
        }
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(1);
        ListNode listNode4 = new ListNode(2);
        ListNode listNode5 = new ListNode(1);
        ListNode listNode6 = new ListNode(2);
        ListNode listNode7 = new ListNode(1);
        ListNode listNode8 = new ListNode(2);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;
        listNode7.next = listNode8;


        ListNode result = removeElements(listNode1,1);
        while(result != null){
            System.out.println(result.val);
            result = result.next;
        }
    }

}
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}




