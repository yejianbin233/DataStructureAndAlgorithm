package datastructure.queue.priority;/**
 * Created by Administrator on 2019/9/24.
 */

import java.util.Random;

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName HeapPrioriptyQueue
 * @Description 堆实现的优先级队列
 * @Date 2019/9/24 22:07
 **/

/*
* 实现思想：将一个数组以二叉树的方式来使用
* */
public class HeapPrioriptyQueue {
    private int[] data;
    private int size;
    public HeapPrioriptyQueue(int capacity){
        data = new int[capacity];
        size = 0;
    }

    public void push(int val){
        if (size >= data.length)
            throw new IndexOutOfBoundsException("队列已满.");
        // 注意，这里意味着使用下标为 0 的数组存储空间
        // 还有一种方式是不使用 0 下标，直接从 1 下标开始。整体思路是一样的，寻找父节点的方式不同。
        data[size++] = val;
        up();
    }
    /*
    * 上浮操作 - 每当添加一个元素到队列时执行一次，将当前元素上浮到合适的位置，
    * 如果新添加的元素是当前队列中的最大值，那么它将上浮到第一位。
    * */
    private void up(){
        int currentIndex = size-1;
        int temp = data[currentIndex];
        while(getFatherNode(currentIndex) >=0 && temp > data[getFatherNode(currentIndex)]){
            data[currentIndex] = data[getFatherNode(currentIndex)];
            currentIndex = getFatherNode(currentIndex);
            if (currentIndex <= 0)
                break;
        }
        data[currentIndex] = temp;
    }
    private int getFatherNode(int index){

        return (index-1) >> 1;
    }
    // 递归实现
    public int pop(){
        if (size == 0){
            throw new IndexOutOfBoundsException("队列已空.");
        }
        int max = data[0];
        data[0] = data[--size];
        down();
        return max;
    }
    private void down(){

        int temp = data[0];
        int childIndex = getChild(0);
        while (childIndex < size) {
            if (childIndex+1 < size && data[childIndex] < data[childIndex+1])
                childIndex++;

            if (temp >= data[childIndex])
                break;
            data[getFatherNode(childIndex)] = data[childIndex];
            childIndex = getChild(childIndex);
        }
        data[childIndex] = temp;
    }
    private void swap(int[] arr,int a,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    private int getChild(int index){
        return (index << 1) + 1;
    }

    public static void main(String[] args) {
        HeapPrioriptyQueue queue = new HeapPrioriptyQueue(100);
        Random random = new Random();
        for (int i=0;i<50;i++){
            int a = random.nextInt(1000);
            System.out.println(a);
            queue.push(a);
        }

        System.out.println("=========");
        for(int i=0;i<40;i++){
//            if (i % 2 == 0)
//                queue.push(random.nextInt(1000));

            System.out.println(queue.pop());
        }

    }
}
