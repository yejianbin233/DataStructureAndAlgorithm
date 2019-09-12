package datastructure.stack;
/**
 * Created by Administrator on 2019/9/9.
 */

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName LinketStack
 * @Description 单向链表实现栈结构
 * @Date 2019/9/9 22:03
 **/
public class LinketStack<T> {
    public static void main(String[] args) {
        LinketStack<Integer> linketStack = new LinketStack<>();
        for (int i=0;i<3;i++) {
            linketStack.push(i);
        }
        for (int i=0;i<4;i++) {
            System.out.println(linketStack.peek());
            linketStack.pop();
        }
    }
    private Node top;
    public boolean isEmpty(){
        return top == null;
    }

    public void push(T data){
        if (top == null) {
            top = new Node(data,null);
        } else {
            // 创建 Node 节点，并维护 top
            top = new Node(data,top);
        }
    }

    public T pop(){
        if (top == null) {
            return null;
        }

        Node returnNode = top;
        top = top.next;
        // 维护 Node 链表
        returnNode.setNext(null);
        return (T) returnNode.getData();
    }

    public T peek(){
        if (top == null) {
            return null;
        }
        return (T) top.getData();
    }

    private class Node<T>{
        private T data;
        private Node next;

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
