package datastructure.queue;
/**
 * Created by Administrator on 2019/9/10.
 */

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName LoopQueue
 * @Description 数组实现的循环队列，用于解决数组实现的队列在删除数据时的时间复杂度为 O(n) 的问题
 * @Date 2019/9/10 22:11
 **/
public class LoopQueue<E> {
    private E[] data;
    // front 用于定位队首
    private int front;
    // tail 用于定位队尾
    private int tail;

    public LoopQueue(){
        this(10);
    }
    // capacity + 1，由于利用 (tail + 1) % data.length == front 来判断队列是否为空，将导致闲置了一个空间单位
    public LoopQueue(int capacity){
        data = (E[]) new Object[capacity+1];
    }

    // 当 front == tail，data 数组为空，当 tail + 1 % data.length = front 时，说明 queue is full
    public boolean isEmpty(){
        return front == tail;
    }

    public int getSize(){
        return ((tail + data.length) - front) % data.length;
    }
    private int getCapacity(){
        return data.length - 1;
    }

    public void enqueue(E e){
        // 判断队列是否已满，满则进行扩容
        if ((tail+1)%data.length == front){
            resize(getCapacity() << 2);
        }

        data[tail] = e;
        tail = (tail+1)%data.length;
    }

    public E dequeue() throws Exception {
        if (isEmpty()) {
            throw new Exception("queue is empty");
        }
        E ret = data[front];
        front = (front+1) % data.length;
        // 如果队列的元素数量减少至队列容量的四分之一，且原容量 / 2 不等于 0，则进行缩容
        if (getSize() == getCapacity() / 4 && getSize() / 2 != 0) {
            resize(getCapacity() >> 2);
        }
        return ret;
    }

    public E getFront() throws Exception {
        if (isEmpty()) {
            throw new Exception("queue is empty");
        }
        return data[front];
    }
    private void resize(int capacity){
        // 扩容两倍
        E[] newData = (E[]) new Object[capacity+1];

        // 复制数据
        for (int i=0;i<getSize();i++) {
            newData[i] = data[(front+i) % data.length];
        }
        // 将扩容的队尾维护为未扩容时的元素数量
        tail = getSize();
        data = newData;
        front = 0;
    }

    public static void main(String[] args) throws Exception {
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        for (int i=0;i<10;i++) {
            loopQueue.enqueue(i);

        }
//
//        loopQueue.dequeue();
//        loopQueue.dequeue();
//        loopQueue.dequeue();
        loopQueue.enqueue(10);
        loopQueue.enqueue(11);
        loopQueue.enqueue(12);
        for (int i=0;i<10;i++) {
            System.out.println(loopQueue.dequeue());
        }
    }
}
