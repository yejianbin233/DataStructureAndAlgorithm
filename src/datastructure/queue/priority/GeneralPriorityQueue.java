package datastructure.queue.priority;/**
 * Created by Administrator on 2019/9/24.
 */

import datastructure.Array;

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName GeneralPriorityQueue
 * @Description 普通的优先级队列 - 使用数组实现
 * @Date 2019/9/24 22:05
 **/
public class GeneralPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E>{
    private Array<E> data;

    public GeneralPriorityQueue() {
        this.data = new Array();
    }

    // 时间复杂度 0(1)
    @Override
    public void enqueue(E val) {
        data.add(val);
    }

    // 时间复杂度 O(n) - 当要删除 n 个元素时，时间复杂度为 O(n^2)
    @Override
    public E dequeue() {
        int maxIndex = getPriorityMaxIndex();
        return data.remove(maxIndex);
    }
    // 时间复杂度 O(n)
    private int getPriorityMaxIndex(){
        int maxIndex = 0;
        for (int i=0; i<data.getSize(); i++) {
            int flag = data.get(maxIndex).compareTo(data.get(i));
            maxIndex = flag < 1 ? i : maxIndex;
        }
        return maxIndex;
    }

    @Override
    public E getFront() {
        return data.get(getPriorityMaxIndex());
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    public static void main(String[] args) {
        GeneralPriorityQueue<Integer> queue = new GeneralPriorityQueue<>();
        for (int i=0;i<100;i++) {
            queue.enqueue(i);
        }

        for (int i=0;i<100;i++) {
            System.out.println(queue.dequeue());
        }
        System.out.println(queue.isEmpty());
    }
}
