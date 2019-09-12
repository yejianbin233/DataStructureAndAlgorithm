package datastructure.stack;
/**
 * Created by Administrator on 2019/9/9.
 */

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName ArrayStack
 * @Description 数组实现的栈
 * @Date 2019/9/9 22:38
 **/
public class ArrayStack<T> {
    //存储数据的 stack 数组
    private Object[] stack;

    private static final int DEFAULT_INIT_CAPACITY = 6;
    private int size;
    public ArrayStack(){
        this(DEFAULT_INIT_CAPACITY);
    }

    public ArrayStack(int init_capacity){

        this.stack = new Object[init_capacity];
        size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void print(){
        for(int i=size-1;i>=0;i--)
            System.out.println(stack[i]);
    }

    public void push(T t){
        if(size == stack.length){
            new Exception("stack capacity is full！");
            //todo 如果不限制容量使用 链表作为 stack 的存储结构
            //Java 中的 LinkedList 有 可以实现 stack 功能的 api
            //Java 有实现了栈功能的类 - Stack
        }

        stack[size++] = t;
    }

    public T peek(){
        return (T)stack[size-1];
    }

    public T pop(){
        T ret = peek();
        stack[--size] = null;

        return ret;
    }
}
