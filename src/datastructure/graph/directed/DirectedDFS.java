package datastructure.graph.directed;/**
 * Created by Administrator on 2019/10/24.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName DirectedDFS
 * @Description 有向图 - 可达性分析
 * @Date 2019/10/24 14:19
 **/
public class DirectedDFS {
    private boolean[] marked;

    // 在 Digraph 中找到从 s 可达的所有顶点
    public DirectedDFS(Digraph g, int s) {
        marked = new boolean[g.V()];
        dfs(g, s);
    }

    // 在 Digraph 中找到从 sources 中的所有顶点可达的所有顶点（理解错误，不是 sources 中所有顶点共同可达到的顶点数）
    // 多点可达性 - 应用  - 标记 - 清除的垃圾收集
    public DirectedDFS(Digraph g, Iterator<Integer> sources) {
        marked = new boolean[g.V()];
        while (sources.hasNext()) {
            int x = sources.next();
            if (!marked[x]) {
                dfs(g, x);
            }
        }
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        Iterator<Integer> it = g.adj(v);
        while (it.hasNext()) {
            int x = it.next();
            if (!marked[x]) {
                dfs(g, x);
            }
        }
    }

    boolean marked(int v) {
        return marked[v];
    }

    public static void main(String[] args) {
        Digraph g = Digraph.init();
        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);

        DirectedDFS search = new DirectedDFS(g, 1);
        System.out.println(search.marked(0) + " = false");
        System.out.println(search.marked(5) + " = true");
        DirectedDFS search2 = new DirectedDFS(g, arr.iterator());
        for (int i = 0; i < g.V(); i++) {
            if (search2.marked(i)) {
                System.out.println(i + " ");
            }
        }
        System.out.println();
    }
}
