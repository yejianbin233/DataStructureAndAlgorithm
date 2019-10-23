package datastructure.graph.handle;/**
 * Created by Administrator on 2019/10/23.
 */

import datastructure.graph.Graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName CC
 * @Description 连通分量
 * @Date 2019/10/23 10:42
 **/
public class CC {

    private boolean[] marked;
    private int[] id;
    private int count;

    // 预处理构造函数
    public CC(Graph g) {
        marked = new boolean[g.V()];
        id = new int[g.V()];

        // 要理解连通变量代表的意义 - 连通图的连通变量表示本身
        // 每次深度搜索后都会标记访问过的顶点，如果在深度搜索之后，仍然存在顶点没有被访问，这说明该图存在多个连通变量
        for (int s = 0; s < g.V(); s++) {
            if (!marked[s]) {
                dfs(g, s);
                this.count++;
            }
        }
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        id[v] = count;

        Iterator<Integer> it = g.adj(v);
        while (it.hasNext()) {
            int x = it.next();
            if (!marked[x]) {
                dfs(g, x);
            }
        }
    }

    // v 与 w 是否连通
    // 根据 id[] 连通变量数组，如果存放的数据不同则说明存在不同的连通变量
    boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    // 连通分量
    int count() {
        return this.count;
    }

    // v 所在的连通分量的标识符（0 - count() - 1），连通分量不会超过顶点数 - 1，相等则说明每个顶点之间都是独立的，没有边连接
    int id(int v) {
        return id[v];
    }

    public static void main(String[] args) {
        Graph g = Search.initGraph(false);

        CC cc = new CC(g);
        int m = cc.count();
        System.out.println(m + " components");

        List<List<Integer>> components = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            components.add(new LinkedList<Integer>());
        }

        for (int v = 0; v < g.V(); v++) {
            components.get(cc.id(v)).add(v);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < components.get(i).size(); j++) {
                System.out.print(components.get(i).get(j) + " ");
            }
            System.out.println();
        }

        System.out.println(cc.connected(0,1));
    }
}
