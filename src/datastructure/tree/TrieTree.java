package datastructure.tree;/**
 * Created by Administrator on 2019/10/10.
 */

import java.util.TreeMap;

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName TrieTree
 * @Description Trie 树，仅用于字符串查找
 * @Date 2019/10/10 22:11
 **/
/*
* Trie 是一个多叉树，但与二叉树的操作方式是相通的
*
* Trie 树仅用于字符串查找，可以使得字符串的查找效率与存储的字符串数量无关，仅与字符串的长度有关
* */
public class TrieTree {
    public class Node{
        // isWord 用于判断当前字符是否为一个字符串的最后一个字符
        public boolean isWord;

        // 通过 TreeMap 一步步索引并匹配完整的字符串
        public TreeMap<Character,Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this.isWord = false;
            next = new TreeMap<>();
        }
    }

    // 根节点
    private Node root;
    // 存储的字符串数量
    private int size;

    public int getSize(){
        return size;
    }

    public TrieTree() {
        this.root = new Node();
        this.size = 0;
    }

    // 可以使用递归 / 迭代的方式添加字符串

    // 迭代方式
    public void add(String str){
        Node cur = root;
        for (int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            // 如果没有匹配的字符，则添加
            if (cur.next.get(c) == null)
                cur.next.put(c,new Node());

            cur = cur.next.get(c);
        }
        // 判断是否重复添加字符串，重复添加不做任何操作
        if (!cur.isWord){
            cur.isWord = true;
            size++;
        }
    }
    // 判断是否包含字符串的逻辑与添加的逻辑一样
    public boolean contains(String str){
        Node cur = root;
        for (int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            // 如果在没有遍历完成时发现有没有匹配的字符，则说明不包含该字符串
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        // 通过 isWord 来判断是一个完整的字符串还是其他字符串的前缀
        return cur.isWord;
    }

    public static void main(String[] args) {
        String s = "123";
        String t = "12";
        TrieTree trieTree = new TrieTree();
        trieTree.add(s);
        System.out.println(trieTree.contains(t));
    }

    /*
    *
    * question
    *
    * 1. 前缀查找
    *
    * 2. leetcode 211 模式匹配
    *
    * 3. 字符串映射 - leetcode 677
    *
    * 4. Trie 删除操作
    * */
}
