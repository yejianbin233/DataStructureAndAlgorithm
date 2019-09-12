package datastructure;
/**
 * Created by Administrator on 2019/9/11.
 */

/**
 * @author Administrator
 * @version 1.0
 * @ClassName LinkedList
 * @Description TODO
 * @Date 2019/9/11 22:55
 **/
public class LinkedList<E> {
    private class Node{
        private E e;
        private Node next;

        public Node(E e) {
            this.e = e;
        }

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }
    }

    // 虚拟节点，用于充当链表头部哨兵
    private Node dummyNode;
    private int size;

    public LinkedList() {
        this.dummyNode = new Node(null,null);
        size = 0;
    }
    // 创建一个新 Node 节点，next 指向哨兵节点后的第一个 Node
    // 然后维护 哨兵，指向新创建的 Node 节点
    public void addFirst(E e){
        dummyNode.next = new Node(e,dummyNode.next);
        size++;
    }


    public void add(int index,E e){
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index is illegal.not in [0,size]");
        }
        Node prev = dummyNode;
        for (int i=0;i<index;i++) {
            prev = dummyNode.next;
        }

        Node insetNode = new Node(e,prev.next);
        prev.next = insetNode;
        size++;
    }
}
