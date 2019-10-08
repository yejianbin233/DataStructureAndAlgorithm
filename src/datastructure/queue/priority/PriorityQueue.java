package datastructure.queue.priority;/**
 * Created by Administrator on 2019/9/24.
 */

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName PriorityQueue
 * @Description 优先级队列
 * @Date 2019/9/24 22:00
 **/

/*
* 优先级队列提供三种底层数据结构实现方式：
*
* 1. 普通线性结构
* 2. 顺序线性结构
* 3. 堆
*
* 比较其各自实现方法的时间复杂度
* */
public interface PriorityQueue<E extends Comparable<E>> {
    void enqueue(E val);
    E dequeue();
    E getFront();
    int getSize();
    boolean isEmpty();
}
