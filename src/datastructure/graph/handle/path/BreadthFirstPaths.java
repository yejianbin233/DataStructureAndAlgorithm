package datastructure.graph.handle.path;/**
 * Created by Administrator on 2019/10/23.
 */

import datastructure.graph.Graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName BreadthFirstPaths
 * @Description 广度优先搜索 - 使用队列
 * @Date 2019/10/23 9:49
 **/
public class BreadthFirstPaths {
    private boolean[] marked;
    // 用于维护树的层次关系，也就是起点 s 到 v 的最短路径
    private int[] edgeTo;
    private final int s;

    public BreadthFirstPaths(Graph g, int s) {
        this.marked = new boolean[g.V()];
        this.edgeTo = new int[g.V()];
        this.s = s;

        bfs(g, s);
    }
    // 迭代方式 - 单点最短路径
    private void bfs(Graph g, int s) {
        // 创建队列
        Queue<Integer> queue = new LinkedList<>();
        // 添加起点
        queue.add(s);
        marked[s] = true;

        // 判断队列是否为空
        while (!queue.isEmpty()) {
            // 出队，处理队首元素
            int v = queue.remove();

            // 维护层次关系
            /*
            * 在广度优先搜索中，也可以把搜索路径视为一棵树；
            *
            * 这是一颗层次关系的树（按照一层一层的节点去搜索，上层处理过的节点不再处理）
            * */

            Iterator<Integer> it = g.adj(v);
            while (it.hasNext()) {
                int w = it.next();
                // 忽略已被标记的顶点
                if (!marked[w]) {
                    queue.add(w);
                    // // 维护顶点与顶点之间的层次关系
                    marked[w] = true;
                    edgeTo[w] = v;
                }
            }
        }

    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Stack<Integer> pathTo(int v) {
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
