package datastructure;
/**
 * Created by Administrator on 2019/8/25.
 */

import java.util.Arrays;

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName Array
 * @Description 动态数组类
 * @Date 2019/8/25 10:39
 **/
public class Array<E>{
    // 使用泛型 - 定义更通用的数组类
    private E[] data;
    // size - 判断数组内存储的元素数量
    private int size;

//    // 特别定义 - 用于优先级队列
//    private int maxIndex;

    // 自定义初始化容量
    public Array(int capacity){
        data = (E[]) new Object[capacity];
        size = 0;
    }

    // 默认初始化容量
    public Array(){
        this(10);
    }

    // 通用方法
    public int getSize(){
        return size;
    }
    // 获取数组的容量
    public int getCapacity(){
        return data.length;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public boolean contains(E e){
        for(int i = 0 ; i < size ; i ++){
            if(data[i].equals(e))
                return true;
        }
        return false;
    }

    // 增
    // 根据条件 时间复杂度 O(n) / O(1)
    public void add(E val){
        addIndexOf(size, val);
    }
    // 时间复杂度 O(n)
    public void addFirst(E val){
        addIndexOf(0, val);
    }
    /*
    时间复杂度 - O(n)

    分析 - 主要耗时在于移动元素
    for (int i = size; i > index; i--) {
         data[i] = data[i-1];
    }
    在这里做了优化,没有移动整个 data.length(n),但可以视为 size = ?*n(? = 常数),如(1/2 n、1/3n...)
    常数可以忽略,因此时间复杂度为 O(n)
    */
    public void addIndexOf(int index, E val){
        if (index < 0) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        // 动态数组扩展
        if (size >= data.length) {
            resize();
        }
        /*
        * 添加思路：
        * 1. 根据数组的性质 - 当插入新元素时,需要移动插入元素索引后所有元素
        * 2. 优化 - 不需要移动新元素索引前面的元素
        */
        // 移动数组
        // tip：size 是存储元素个个数,而 data.length 是数组的长度,二者概念不同!
        for (int i = size; i > index; i--) {
            data[i] = data[i-1];
        }
        // 存储元素
        data[index] = val;
        size++;
    }
    //  动态数组扩展,默认 2 倍扩展
    // 时间复杂度 O(n)
    private void resize(){
        E[] newArray = (E[]) new Object[data.length * 2];
        // 复制
        for (int i = 0; i < size; i++) {
            newArray[i] = data[i];
        }
        this.data = newArray;
    }
    // 删
    /*
    时间复杂度 - O(n)
    分析 - 查找 O(n),移动 O(n).总时间复杂度 O(2n) = O(n) + O(n) (常数可忽略)-> O(n)
     */
    public int remove(E val){
        Integer delIndex = null;
        // 从索引 0 开始,查找 val
        for (int i=0;i<size;i++) {
            if (data[i].equals(val)) {
                delIndex = i;
            }
        }
        // 遍历 data,如果 val 不存在
        if (delIndex == null) {
            return -1;
        }
        // 移动 val 后续的值,覆盖删除 val
        for (int i=delIndex+1;i<size;i++) {
            data[i-1] = data[i];
        }
        size--;
        reduce();
        return delIndex;
    }
    // 时间复杂度 O(n)
    public int removeLast(E val){
        Integer delIndex = null;
        // 从索引 size-1 开始,逆序查找 val
        for (int i=size-1;i>0;i--) {
            if (data[i].equals(val)) {
                delIndex = i;
                break;
            }
        }
        // 遍历 data,如果 val 不存在
        if (delIndex == null) {
            return -1;
        }
        // 移动 val 后续的值,覆盖删除 val
        for (int i=delIndex+1;i<size;i++) {
            data[i-1] = data[i];
        }
        data[--size] = null;
        reduce();
        return delIndex;
    }

    // 时间复杂度 O(n)
    public E remove(int index){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        E retVal = data[index];
        for (int i = index+1; i < size; i++) {
            data[i-1] = data[i];
        }
        // 维护数组最后一个元素(可选)
        // data[size-1] = null;
        size--;
        reduce();
        return retVal;
    }
    // 时间复杂度 - O(n)
    private void reduce(){
        int threshold = data.length >> 1;
        if (size > threshold) {
            return;
        }
        E[] newData = (E[]) new Object[size];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
    // 改
    // 时间复杂度 O(n)
    public int replace(E oldVal,E newVal){
        // 从索引 0 开始,查找 val
        for (int i=0;i<size;i++) {
            if (data[i].equals(oldVal)) {
                data[i] = newVal;
                return 1;
            }
        }
        return -1;
    }

    // 时间复杂度 O(n)
    public int replaceLast(E oldVal,E newVal){
        // 从索引 size 开始,逆序查找 val
        for (int i=size-1;i>0;i--) {
            if (data[i].equals(oldVal)) {
                data[i] = newVal;
                return 1;
            }
        }
        return -1;
    }

    // 时间复杂度 O(1)
    public void replace(int index, E val){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        data[index] = val;
    }

    // 时间复杂度 O(n)
    public void replaceAll(E oldVal,E newVal){
        Integer startIndex = null;
        for (int i=0;i<size;i++) {
            if (data[i].equals(oldVal)) {
                startIndex = i;
                break;
            }
        }
        if (startIndex == null) {
            return;
        }
        for (int i = startIndex; i<size; i++) {
            E val = data[i];
            if (val.equals(oldVal)){
                data[i] = newVal;
            } else {
                continue;
            }
        }
    }
    // 查
    // 时间复杂度 O(i)
    public E get(int index){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }

        return data[index];
    }

    // 时间复杂度 O(n)
    public int indexOf(E val){
        for (int i=0;i<size;i++) {
            if (data[i].equals(val)) {
                return i;
            }
        }
        return -1;
    }
    // 时间复杂度 O(n)
    public int indexOfLast(E val){
        for (int i=size-1;i>0;i--) {
            if (data[i].equals(val)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Array{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                ", length=" + data.length+
                '}';
    }
}