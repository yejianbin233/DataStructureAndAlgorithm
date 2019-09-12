package datastructure.queue;/**
 * Created by Administrator on 2019/9/10.
 */

import datastructure.Array;

/**
 * @author yejainbin
 * @version 1.0
 * @ClassName ArrayQueue
 * @Description 利用 Array（动态数组）来实现队列
 * @Date 2019/9/10 21:24
 **/
public class ArrayQueue<E> {

    public static void main(String[] args) throws Exception {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>(10);
        for (int i=0;i<10;i++) {
            arrayQueue.enqueue(i);
        }
        for (int i=0;i<10;i++){
            System.out.println(arrayQueue.dequeue());
        }
    }

    private Array<E> elements;
    private int length;

    public ArrayQueue(){
        elements = new Array<>(10);
    }
    private ArrayQueue(int capacity){
        elements = new Array<E>(capacity);
        this.length = capacity;
    }
    public void enqueue(E e) throws Exception {
        if (length == elements.getSize()) {
            throw new Exception("ArrayQueue is full.");
        }
        elements.add(e);
    }
    public E dequeue() throws Exception {
        if (isEmpty()) {
            throw new Exception("队列为空.");
        }
        E ret = elements.get(0);
        elements.remove(0);
        return ret;
    }
    public E getFront() throws Exception {
        if (isEmpty()) {
            throw new Exception("队列为空.");
        }
        return elements.get(0);
    }
    public int getSize(){
        return elements.getSize();
    }
    public boolean isEmpty(){
        return elements.getSize() == 0;
    }
}
