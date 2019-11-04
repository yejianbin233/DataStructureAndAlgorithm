package datastructure.stack;/**
 * Created by Administrator on 2019/11/4.
 */

import java.util.Iterator;

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName Stack
 * @Description 栈 - 基于后进先出（LIFO）策略的集合类型
 * @Date 2019/11/4 15:43
 **/
/**
 * "栈"是一种线性结构，只能在一端（栈顶）进行插入和删除操作。
 *
 *   1. 可以使用数组或链表实现栈：
 *
 *      a. 数组：
 *
 *          1. 通过维护数组的指针来实现栈操作
 *          2. 指针在数组末端，不用移动元素（添加/删除的时间复杂度 O(1)）
 *          3. 缺点 - 数组大小固定，栈空间有限
 *
 *      b. 链表
 *
 *          1. 有多种链表的实现形式（单向、双向...）
 *          2. 栈空间无限
 *          c. 缺点 - 暂用额外的空间，需要保存下一个节点 next
 * */
public class Stack<T> implements Iterable<T> {
    // first 用于遍历
    private Node first;

    // tail 用于添加
    private Node tail;

    private int size;

    // 私有内部类，栈维护的单向链表，用于存储元素
    // 可以定义其他结构的链表，如双向链表来对栈的操作（删除）进行优化
    private class Node {
        public T data;
        public Node next;

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    // 创建一个空栈
    public Stack() {
        // 初始化
        first = new Node(null, null);
        tail = first;
        size = 0;
    }

    // 添加一个元素
    // note：push 操作不作任何优化
    public void push(T t) {
        // 使用临时节点来迭代至最后一个节点的位置，同时不破坏原有节点的结构
        Node curNode = first;
        // 该插入操作的时间复杂度为 O(n)
        while (curNode.next != null)
            // 迭代至单向链表的尾部
            curNode = curNode.next;
        // 插入操作
        size++;
        curNode.next = new Node(t, null);
    }
    // 栈添加元素的优化 - 增加一个尾节点，直接在为节点上进行添加，并维护尾节点
    // 时间复杂度 O(1)
    public void pushOptimization(T t){
        // 添加
        tail.next = new Node(t,null);
        // 维护尾节点
        tail = tail.next;
        size++;
    }

    // 删除最近添加的元素，不返回值
    // 出栈操作需要删除元素同时维护单向链表的结构，最好使用递归方式来实现（使用迭代方式也可）
    // 时间复杂度 - O(n)
    public void popRecursive() {
        // 递归方式
        // 如果为空，则返回 null（不做非空判断会导致空指针异常）
        if (isEmpty()) return;

        pop(first);
    }

    // 删除操作可以定义双向链表结构，直接断开尾节点与链表的关联，并维护尾节点 - 时间复杂度 O(1)，暂未实现
    private Node pop(Node node) {
        // 删除最后一个节点
        if (node.next == null) return null;

        // 维护单向链表的结构
        node.next = pop(node.next);
        return node;
    }

    // 迭代方式 - 时间复杂度 O(n)
    public T pop() {
        // 如果为空，则返回 null（不做非空判断会导致空指针异常）
        if (isEmpty()) return null;

        Node curNode = first;

        while (curNode.next != null && curNode.next.next != null) {
            curNode = curNode.next;
        }
        T ret = curNode.next.data;
        curNode.next = null;
        size--;
        return ret;
    }

    // 栈是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 栈中的元素的数量
    public int size() {
        return size;
    }

    // 迭代集合元素
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
            T t = current.data;
            current = current.next;
            return t;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack();

        for (int i=0;i<10;i++) {
            stack.pushOptimization(i);
        }
        for (int i : stack) {
            System.out.println(i);
        }

        stack.popRecursive();
        stack.popRecursive();
        stack.popRecursive();
        stack.popRecursive();
        System.out.println("==========");
        for (int i : stack) {
            System.out.println(i);
        }
    }
}
