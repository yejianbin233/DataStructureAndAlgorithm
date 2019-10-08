package datastructure.queue.priority;/**
 * Created by Administrator on 2019/9/24.
 */

import datastructure.LinkedList;

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName OrderPriorityQueue
 * @Description 顺序优先级队列
 * @Date 2019/9/24 22:06
 **/
public class OrderPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E>{
    private LinkedList<E> data;

    @Override
    public void enqueue(E val) {
//        data.
    }

    @Override
    public E dequeue() {
        return null;
    }

    @Override
    public E getFront() {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
