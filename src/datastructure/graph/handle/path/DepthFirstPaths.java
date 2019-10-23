package datastructure.graph.handle.path;/**
 * Created by Administrator on 2019/10/22.
 */

import datastructure.graph.Graph;

import java.util.Iterator;
import java.util.Stack;

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName DepthFirstPaths
 * @Description 深度优先查找图中的路径
 * @Date 2019/10/22 17:13
 **/
public class DepthFirstPaths {
    // 是否已经走过（调用过 dfs()）
    private boolean[] marked;
    // 从起点到一个顶点的已知路径上的最后一个顶点
    private int[] edgeTo;
    // 起点
    private final int s;

    public DepthFirstPaths(Graph g, int s) {
        this.marked = new boolean[g.V()];
        this.edgeTo = new int[g.V()];
        // 设置起点
        this.s = s;

        // 在搜索所有与 s 相连通的路径的同时，也记录了路径间顶点的位置关系
        // note：这不是最优路径，遍历的路径不同有不同的路径结果
        dfs(g,s);
    }

    // 仅实现寻找路径功能，而不是最短路径
    private void dfs(Graph g, int v){
        marked[v] = true;
        Iterator<Integer> it = g.adj(v);
        while(it.hasNext()){
            int w = it.next();
            if (!marked[w]) {
                // edgeTo 数组的下标代表顶点，edgeTo[顶点] = 路径上一个经过的顶点，通过该方式来记录每个顶点与起点的路径
                // 通过栈来回溯 edgeTo 数组，实现路径显示
                // 连通的路径可视为一棵树，从根节点到各个顶点是一条路径（顶点可以是叶子节点也可以是内部的节点）
                edgeTo[w] = v;
                dfs(g,w);
            }
        }
    }
    public boolean hasPathTo(int v){
        return marked[v];
    }


    public Stack<Integer> pathTo(int v){

        if (!hasPathTo(v)) {
            return null;
        }

        Stack<Integer> path = new Stack<>();
        // 如果起点 s 与 v 存在路径，那么必定在 edgeTo 数组中维护着顶点关系
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);

        return path;
    }
}
