package datastructure.tree;/**
 * Created by Administrator on 2019/9/19.
 */

import java.util.Stack;

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName BSTree
 * @Description 二分搜索树 - 二叉树的一种，具有二叉树的所有特性。
 *
 * 二分搜索树的特点：二分搜索树的每一个节点的值都大于其左子树的所有节点的值，小于其右子树的所有的值。
 *
 *           5
 *         /  \
 *        3    8
 *       / \  / \
 *      1  4 6  9
 *
 * @Date 2019/9/19 21:18
 **/
// <E extends Comparable<E>> -> 为了满足二分搜索树可比较的性质
public class BSTree<E extends Comparable<E>> {
    private class Node{
        private E val;
        // 左子节点
        private Node left;
        // 右子节点
        private Node right;

        public Node(E val) {
            this.val = val;
            left = null;
            right = null;
        }
    }

    // 根节点
    private Node root;
    // 树的元素数量
    private int size;

    public BSTree(){
        root = null;
        size = 0;
    }

    public void add(E val){
        root = add(root,val);
    }
    // 递归添加二分搜索树的节点
    private Node add(Node node,E val){
        // 递归基 - 每次在树添加元素时，通过比较后到达适合元素存放的节点，此时节点必定为 null
        if (node == null) {
            size++;
            return new Node(val);
        }

        // 根据二分搜索树的性质，到达元素存放的节点
        if (val.compareTo(node.val) > 0) {
            node.right = add(node.right,val);
        } else if (val.compareTo(node.val) < 0) {
            node.left = add(node.left,val);
        } else {

        }
        // 返回当前节点，利用递归的特性来维护树内元素节点的关系
        return node;
    }

    public boolean contains(E val){
        return contains(root,val);
    }

    // 递归方式判断树中是否存在 val
    private boolean contains(Node node,E val){
        if (node == null){
            return false;
        }
        if (val.compareTo(node.val) > 0) {
            return contains(node.right, val);
        } else if (val.compareTo(node.val) < 0) {
            return contains(node.left,val);
        } else {
            return true;
        }
    }

    // 迭代方式判断树中是否存在 val
    public boolean constainsIterator(E val){
        Node ret = root;
        while(ret != null){
            if (val.compareTo(ret.val) > 0) {
                ret = ret.right;
            } else if (val.compareTo(ret.val) < 0) {
                ret = ret.left;
            } else {
                return true;
            }
        }
        return false;
    }

    // 前序遍历
    public void preOrder(){
        preOrder(root);
    }
    private void preOrder(Node node){
        // 递归基
        if (node == null) {
            return;
        }
        System.out.println(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

    // 中序遍历 - 按照顺序输出树中的所有节点
    public void inOrder(){
        inOrder(root);
    }
    private void inOrder(Node node){
        // 递归基
        if (node == null) {
            return;
        }
        preOrder(node.left);
        System.out.println(node.val);
        preOrder(node.right);
    }
    // 后序遍历
    public void postOrder(){
        postOrder(root);
    }

    // ps：前中后序遍历都是深度优先遍历

    // 广度优先遍历（按照树的层次输出元素，从根节点往叶子节点自上向下输出同一层次的元素） - 使用栈
    public void order(){
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        // 如果栈不为空
        while(!stack.isEmpty()){
            Node ret = stack.pop();
            System.out.println(ret.val);
            // 注意顺序，后进先出，先 push 右子节点，再 push 左子节点
            if (ret.right != null)
                stack.push(ret.right);
            if (ret.left != null)
                stack.push(ret.left);
        }
    }
    private void postOrder(Node node){
        // 递归基
        if (node == null) {
            return;
        }
        preOrder(node.left);
        preOrder(node.right);
        System.out.println(node.val);
    }

    /*
    *
    *        5
    *      /  \
    *     3    8
    *    / \
    *   2   4
    *
    *   二分搜索树中，最小节点只存在两种情况：
    *
    *   1. 只有一个右子树
    *   2. 没有子树，最小节点为叶子节点
    * */
    // 递归实现
    public E removeMin(){
        // 需要在此处判断，否则在调用 removeMin(Node node) 时可能出现空指针异常.
        if (root == null){
            size--;
            return null;
        }

        if (root.left == null){
            size--;
            return root.val;
        }

        return removeMin(root);
    }

    // 迭代删除最小节点
    public E removeMinIt(){
        if (root == null)
            return null;
        if (root.left == null){
            return root.val;
        }

        Node retNode = root;
        while(retNode.left != null && retNode.left.left != null){
            retNode = retNode.left;
        }
        E result = retNode.left.val;
        retNode.left = retNode.left.right;
        return result;

    }

    private E removeMin(Node node){
        // 根据上面的树来整理逻辑
        if (node.left.left == null) {
            E result = node.left.val;
            node.left = node.left.right;
            size--;
            return result;
        }
        return removeMin(node.left);
    }

    // 删除树的最大节点与删除树的最小节点类似
    // 这里使用更优雅的代码来实现(递归)
    // ps：迭代方式省略，与删除最小节点类似
    public E removeMax(){
        E result = findMax();
        removeMax(root);
        return result;
    }
    private Node removeMax(Node node){
        if (node.right == null) {
            size--;
            return node.left;
        }

        node.right = removeMax(node.right);
        return node;
    }

    private E findMax(){
        if (root == null)
            return null;

        Node retNode = root;
        while (retNode.right != null) {
            retNode = retNode.right;
        }

        return retNode.val;
    }

    // 递归方式
    public void remove(E val){
        root = remove(root,val);
    }
    private Node remove(Node node,E val){
        if (node == null) {
            return node;
        }

        if (val.compareTo(node.val) > 0) {
            node.right = remove(node.right,val);
        } else if (val.compareTo(node.val) < 0) {
            node.left = remove(node.left,val);
        } else {
            /*
            *  删除树中的节点存在以下几种情况：
            *
            *  1. 节点的值不存在（这种情况不做任何处理，
            *  而且一般来说都是遍历一遍 val 是否存在才继续进行操作，这里就做小小的优化，不执行第二步遍历）
            *  2. 被删除的节点只有左子树(10) - 直接返回左子树
            *  3. 被删除的节点只有右子树(20) - 直接返回右子树
            *  4. 被删除的节点左右子树都有(8、5、13)：
            *     a. 将当前节点的 val 使用右子树的最小值替换，然后删除右子树的最小值
            *     b. 将当前节点的 val 使用左子树的最大值替换，然后删除左子树的最大值
            *
            *
            *                   8
            *                 /   \
            *                5     13
            *              / \     / \
            *             2  4    10  20
            *                    /      \
            *                   9       25
            *
            * */
            if (node.left == null) {
                return node.right;
            } else if(node.right == null) {
                return node.left;
            } else {
                node.val = findMinOfCurrentNode(node.right);
                node.right = removeMinOfCurrentNode(node.right);
            }

        }
        return node;
    }
    // 找到当前节点的最小节点的值
    private E findMinOfCurrentNode(Node currentNode){
        if (currentNode == null)
            return null;
        while (currentNode.left != null) {
            currentNode = currentNode.left;
        }
        return currentNode.val;
    }
    private Node removeMinOfCurrentNode(Node currentNode){
        if (currentNode.left == null) {
            currentNode = currentNode.right;
            return currentNode;
        }
        removeMin(currentNode);
        return currentNode;
    }
    public static void main(String[] args) {
        BSTree<Integer> bsTree = new BSTree<>();
        bsTree.add(2);
        bsTree.add(1);
        bsTree.add(3);

        bsTree.order();
        System.out.println("size = "+bsTree.size);
        bsTree.remove(3);
        System.out.println("size = "+bsTree.size);
        bsTree.order();
    }
}
