package datastructure.graph.handle;/**
 * Created by Administrator on 2019/10/22.
 */

import datastructure.graph.Graph;

import java.util.Iterator;

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName DepthFirstSearch
 * @Description 深度优先遍历 - 遍历所有的边（可达性分析）
 *
 * 类似走迷宫思路｛
 *
 *   1. 选择一条没有标记过的通道，在通道上铺一条绳子；
 *   2. 标记所有第一次路过的路口和通道；
 *   3. 当来到一个标记过的路口时，回退到上一个路口；
 *   4. 当回退到的路口没有可走的通道时继续回退。
 *
 * ｝
 * @Date 2019/10/22 11:12
 **/
public class DepthFirstSearch {
    // 标记通过的路口（顶点）
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph g, int s) {
        // 每个顶点对应一个 marked 下标
        marked = new boolean[g.V()];
        dfs(g, s);
    }

    private void dfs(Graph g, int v){
        // 标记当前顶点已经被走过
        this.marked[v] = true;
        this.count++;
        // 当前顶点的边的迭代器
        Iterator<Integer> it = g.adj(v);
        // while 循环隐含着回退，当循环结束回退到上一个路口（顶点）
        while(it.hasNext()){
            // 如果 v 连接的顶点 w 没有被走过，那么先以该顶点 w 深入遍历（深度优先遍历）
            int w = it.next();
            if (!marked[w]) {
                // 使用递归，进入下一个路口继续遍历
                dfs(g,w);
            }
        }
    }
    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return this.count;
    }
}
