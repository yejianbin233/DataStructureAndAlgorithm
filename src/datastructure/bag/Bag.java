package datastructure.bag;/**
 * Created by Administrator on 2019/10/22.
 */

import datastructure.queue.Queue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName Bag
 * @Description 背包 - Bag - 不支持从中删除元素的集合数据类型（无限添加，只进不出）
 * @Date 2019/10/22 10:13
 **/
public class Bag<T> implements Iterable<T> {
    private Node first;
    private int size;

    private class Node {
        T val;
        Node next;

        public Node(T val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    // 创建一个空的背包
    public Bag() {
        first = new Node(null, null);
        size = 0;
    }

    // 添加一个元素
    // 不做任何优化
    public void add(T t) {
        Node curNode = first;
        while (curNode.next != null) curNode = curNode.next;

        curNode.next = new Node(t, null);
        size++;
    }

    // 判断背包是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 背包中的元素的数量
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        private Node current = first.next;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T t = current.val;
            current = current.next;
            return t;
        }
    }

    public static void main(String[] args) {
        Bag<Integer> bag = new Bag<>();

        for (int i = 0; i < 100; i++) {
            bag.add(i);
        }

        System.out.println(bag.size());

        for (int i : bag) {
            System.out.println(i);
        }
    }
}
