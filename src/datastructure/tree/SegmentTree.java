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
    // 构建的线段树
    private E[] tree;
    // 传入的原始数据
    private E[] data;
    // 根据不同的 Merger 实例，计算不同的合并结果
    private Merger<E> merger;

    public SegmentTree(E[] arr,Merger<E> merger){
        data = (E[]) new Object[arr.length];
        for (int i=0;i<data.length;i++) {
            data[i] = arr[i];
        }

        // 线段树在极端情况下，需要原始数组的 4 倍容量才能存放所有元素
        //TODO 为什么要 4 倍？两倍不行吗？线段树构造时的深度如何延伸的？
        tree = (E[]) new Object[4 * arr.length];

        this.merger = merger;
        // 构建线段树
        buildSegmentTree(0,0,data.length-1);
    }

    /*
    *
    * 构造线段树 - 父节点包含子树的所有节点数据，线段树用于根据业务的不同来获取元素区间中的不同统计数据
    *
    * 如 [1,2,3,4,5,6] 数组，想获取 2~4 区间内的元素总和，构建的线段树如下：
    *
    *                        21
    *                      /    \
     *                    6      15
     *                   / \     / \
      *                 1   5   4   11
        *                  / \     / \
    *                     2   3   5   6
    *
    *                   ps：所有的叶子节点组成了原始的 data 数组.
    * 思路：
    *
    * 1. 使用递归
    *
    * 2. 通过当前节点的左右子节点的值来得出当前节点的目标值
    * */
    private void buildSegmentTree(int treeIndex,int l,int r){
        // 递归基，当区间缩小为 1 时，就可以直接该区间内的区域目标值
        if (l == r) {
            // 注意 data[l] = data[r]
            tree[treeIndex] = data[l];
            return;
        }
        // 如果要构建线段树，那么要先自底向上开始构建
        int mid = l + (r - l)/2;
        int leftChild = leftChild(treeIndex);
        int rightChild = rightChild(treeIndex);
        // 自底向上构建
        buildSegmentTree(leftChild,l,mid);
        buildSegmentTree(rightChild,mid+1,r);

        // 计算当前节点的目标值
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
    /*
    * 查询区间的目标值有三种情况：
    *
    * 当目标区间全在一边（1. 左边 / 2.右边）
    *
    * 3. 当目标区间同时存在左右两边
    * */
    private E query(int treeIndex,int l,int r,int queryL,int queryR){
        // 递归基，当查找的区间与节点的区间相匹配
        if (l == queryL && r == queryR) {
            // 当查找的区间匹配时，tree[treeIndex] 就是目标值
            return tree[treeIndex];
        }
        int mid = l + (r-l) / 2;

        if (queryL >= mid + 1) {
            // 目标区间全在右半区间
            return query(rightChild(treeIndex),mid+1,r,queryL,queryR);
        } else if (queryR <= mid) {
            // 目标区间全在左半区间
            return query(leftChild(treeIndex),l,mid,queryL,queryR);
        }

        // 目标区间同时在左右区间内
        E leftResult = query(leftChild(treeIndex),l,mid,queryL,mid);
        E rightResult = query(rightChild(treeIndex),mid+1,r,mid+1,r);

        // 合并结果
        return merger.merge(leftResult,rightResult);
    }

    public void set(int index, E val){
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("index is illegal.");
        }
        //TODO 为什么是 data.length-1 而不是 tree.length - 1???
        set(0,0,data.length-1,index,val);
    }
    /*
    * 更新线段树中的某个节点的值与构建线段树非常的思路类似
    *
    * 在线段树的区间中不断的向下寻找的 l = r 的节点，该节点就是需要更新的值。
    *
    * 然后在从该节点自底向上维护线段树中经过的父节点的值即可。
    * */
    private void set(int treeIndex, int l, int r, int targetIndex, E val){
        if ( l == r) {
            tree[treeIndex] = val;
            return;
        }

        int mid = l + (r-l)/2;
        int leftChild = leftChild(treeIndex);
        int rightChild = rightChild(treeIndex);

        if (targetIndex >= mid + 1) {
            set(rightChild,mid + 1,r,targetIndex,val);
        } else {
            set(leftChild,l,mid,targetIndex,val);
        }

        // 维护父节点的值
        tree[treeIndex] = merger.merge(tree[leftChild],tree[rightChild]);
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
        segmentTree.set(2,3);
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
