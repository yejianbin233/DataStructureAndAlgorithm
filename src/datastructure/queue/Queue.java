package datastructure.queue;/**
 * Created by Administrator on 2019/11/4.
 */

import datastructure.stack.Stack;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName Queue
 * @Description 队列 - 基于先进先出（FIFO）策略的集合类型
 * @Date 2019/11/4 15:40
 **/
/*
* "队列" - 线性结构，只能从一端（队尾）添加元素，从另一端（队首）取出元素。git
* */
public class Queue<T> implements Iterable<T> {

    private Node first;
    private Node last;
    private int size;

    private class Node {
        public T val;
        public Node next;

        public Node(T val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    // 创建空队列
    public Queue() {
        first = new Node(null,null);
        last = first;
        size = 0;
    }

    // 入队操作
    public void enqueue(T t) {
        last.next = new Node(t,null);
        last = last.next;
        size++;
    }

    // 出队操作
    public T dequeue() {
        if(isEmpty()) return null;
        // first 当做哨兵，不改变
        T result = first.next.val;
        first.next = first.next.next;
        size--;
        return result;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 队列中的元素
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T>{
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
        Queue<Integer> queue = new Queue<>();

        for (int i=0;i<10;i++) {
            queue.enqueue(i);
        }
        System.out.println(queue.dequeue());
        System.out.println("===");
        for (int i : queue) {
            System.out.println(i);
        }
    }
}
