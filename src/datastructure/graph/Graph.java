package datastructure.graph;/**
 * Created by Administrator on 2019/10/22.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName Graph
 * @Description 图的基本操作 API
 * @Date 2019/10/22 9:45
 **/
public class Graph {
    private int v;
    private int e;

    // 通过外层 List 来访问顶点，使用 ArrayList 是为了能够通过下标索引。
    // 存储的元素作为单链表使用（LinkedList），并且只能添加元素不能删除元素 - 作为 Bag（暂未实现） 的代替
    private List<LinkedList<Integer>> adj;

    // 创建一个含有 V 个顶点但不含有边的图
    public Graph(int v) {
        this.v = v;
        adj = new ArrayList<>();
        for (int i=0; i < v;i++) {
            adj.add(new LinkedList<>());
        }

    }

    // 顶点数
    public int V() {
        return v;
    }

    // 边数
    public int E(){
        return e;
    }

    // 向图中添加一条边 v-w
    public void addEdge(int v, int w){
        List<Integer> V = adj.get(v);
        // 可以添加多条边（平行边）
        V.add(w);
        e++;
    }

    // 和 v 相邻的所有顶点
    public Iterator<Integer> adj(int v){
        return adj.get(v).iterator();
    }

    @Override
    public String toString() {
        String s = v + "vertices, " + e + "edges\n";

        for (int v=0; v < this.v; v++) {
            s += v + ": ";
            Iterator<Integer> it = this.adj(v);
            while(it.hasNext()) {
                s += it.next() + " ";
            }
            s += "\n";
        }
        return s.toString();
    }

    /*
    *
    *  最常用的图处理代码
    * */
    // 计算当前顶点的度数
    public static int degree(Graph g, int v){

        int degree = 0;
        Iterator<Integer> it = g.adj(v);
        while (it.hasNext()) {
            it.next();
            degree++;
        }

        return degree;
    }

    // 计算图的最大度数
    public static int maxDegree(Graph g){
        int max = 0;
        // 遍历所有顶点
        for (int v = 0; v < g.V(); v++) {
            // 只计算一次顶点的度数
            int currentDegree = degree(g,v);
            if (currentDegree > max) {
                max = currentDegree;
            }
        }
        return max;
    }

    // 计算无向图中的所有顶点的平均度数
    public static double avgDegree(Graph g){
        // 无向图中，一条边连接两个顶点，两个顶点各有独自的一条边
        // 2 * 边数 / 顶点数
        return 2 * g.E() / g.V();
    }

    // 计算自环的个数
    public static int numberOfSelfLoops(Graph g){
        int count = 0;

        for (int v = 0; v < g.V(); v++) {
            Iterator<Integer> it = g.adj(v);
            while(it.hasNext()) {
                if (v == it.next()) {
                    count ++;
                }
            }
        }
        return count;
    }
}
