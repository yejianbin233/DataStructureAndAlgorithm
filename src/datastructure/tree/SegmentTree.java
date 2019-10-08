package datastructure.tree;/**
 * Created by Administrator on 2019/10/8.
 */

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName SegmentTree
 * @Description 线段树
 * @Date 2019/10/8 21:39
 **/
public class SegmentTree<E> {
    private E[] tree;
    private E[] data;
    private Merger<E> merger;
    public SegmentTree(E[] arr,Merger<E> merger){
        data = (E[]) new Object[arr.length];
        for (int i=0;i<data.length;i++) {
            data[i] = arr[i];
        }

        // 线段树在极端情况下，需要原始数组的 4 倍容量才能存放所有元素
        tree = (E[]) new Object[4 * arr.length];
        this.merger = merger;
        buildSegmentTree(0,0,data.length-1);
    }

    /*
    *
    * 构造线段树 - 父节点包含子树的所有节点数据，线段树用于根据业务的不同来获取元素区间中的不同统计数据
    *
    * 如 [1,2,3,4,5,6] 数组，想获取 2~4 区间内的元素总和，构建的线段树如下：
    *
    *                       21
    *                      /  \
     *                    6    15
     *                   / \   / \
      *                 1   5 4   11
        *                  / \    / \
    *                     2   3  5   6
    * 思路：
    *
    * 1. 使用递归
    *
    * 2.
    * */
    private void buildSegmentTree(int treeIndex,int l,int r){
        // 递归基
        if (l == r) {
            // 注意 data[l] = data[r]
            tree[treeIndex] = data[l];
            return;
        }
        // 如果要构建线段树，那么要先自底向上开始构建
        int mid = l + (r - l)/2;
        int leftChild = leftChild(treeIndex);
        int rightChild = rightChild(treeIndex);
        // 自底向上构建，
        buildSegmentTree(leftChild,l,mid);
        buildSegmentTree(rightChild,mid+1,r);

        // 根据业务的不同来定义不同的逻辑来获取区间的数据，示例是获取两个参数（子节点的值）的和来组成父节点的值
        tree[treeIndex] = merger.merge(tree[leftChild],tree[rightChild]);
    }
    public int getSize(){
        return data.length;
    }

    public E query(int queryL,int queryR){
        if (queryL < 0 || queryR >= data.length) {
            throw new IllegalArgumentException("index is illegal");
        }
        return query(0,0,data.length,queryL,queryR);
    }
    private E query(int treeIndex,int l,int r,int queryL,int queryR){
        // 递归基
        if (l == queryL && r == queryR) {
            // 当查找的区间匹配时，tree[treeIndex] 就是目标值
            return tree[treeIndex];
        }
        int mid = l + (r-l) / 2;
        if (queryL >= mid + 1) {
            return query(rightChild(treeIndex),mid+1,r,queryL,queryR);
        } else if (queryR <= mid) {
            return query(leftChild(treeIndex),l,mid,queryL,queryR);
        }

        E leftResult = query(leftChild(treeIndex),l,mid,queryL,mid);
        E rightResult = query(rightChild(treeIndex),mid+1,r,mid+1,r);

        return merger.merge(leftResult,rightResult);
    }

    public E get(int index){
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("index is illegal");
        }

        return data[index];
    }

    private int leftChild(int index){

        return index * 2 + 1;
    }
    private int rightChild(int index){
        return index * 2 + 2;
    }

    public static void main(String[] args) {
        Integer[] arr = {1,2,4,5,6,7,8,9,0};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(arr,new IntegerMerger());

        System.out.println(segmentTree.query(2,4));
    }
}

interface Merger<E>{
    E merge(E e1,E e2);
}
class IntegerMerger implements Merger<Integer>{
    @Override
    public Integer merge(Integer e1, Integer e2) {
        return e1 + e2;
    }
}
