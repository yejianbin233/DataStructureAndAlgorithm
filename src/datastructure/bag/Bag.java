package datastructure.bag;/**
 * Created by Administrator on 2019/10/22.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName Bag
 * @Description 背包 - Bag
 * @Date 2019/10/22 10:13
 **/
public class Bag<T> implements Iterable<T> {

    // 链表的首节点
    private Node first;

    public void add(T t) {
        Node oldFirst = first;
        first = new Node(t, oldFirst);
    }

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class Node<T> {
        private T data;
        private Node next;

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private class ListIterator implements Iterator<T> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T item = (T) current.data;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Bag<Integer> b = new Bag<>();
        for (int i = 0; i < 10; i++) {
            b.add(i);
        }

        for (int x : b) {
            System.out.println(x);
        }
    }
}
