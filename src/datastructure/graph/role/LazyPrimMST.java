package datastructure.graph.role;/**
 * Created by Administrator on 2019/10/25.
 */

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName LazyPrimMST
 * @Description 最小生成树的延时实现 - 在优先级队列保留失效的边
 * @Date 2019/10/25 16:18
 **/
public class LazyPrimMST {
    // 访问标识
    private boolean[] marked;
    // 最小生成树
    private Queue<Edge> mst;
    // 优先级队列（从小到大出队）
    private PriorityQueue<Edge> pq;

    public LazyPrimMST(EdgeWeightGraph G) {
        // 初始化
        pq = new PriorityQueue<>();
        marked = new boolean[G.V()];
        mst = new LinkedList<>();

        visit(G, 0);
        /*
        *  逻辑：
        *  1. 先从第一个顶点开始，将该顶点的所有有权边添加到优先级队列 pq 中
        *  2. 遍历优先级队列，从权值最小的有权边开始判断
        *
        *       1. 该有权边的两个顶点是否都已经被访问过（marked 数组标记为 true），如果是则说明已经将这两个顶点之间的最小有权边添加到了队列 mst 中，continue
        *       2. 如果 if (marked[v] && marked[w]) 不成立，则通过 visit 将没有访问过的顶点的有权边添加到优先级队列 pq
        * */
        // 每次循环都是在挑选满足条件的最小有权边组成最小生成树
        while (!pq.isEmpty()) {
            // pq.poll() 输出队列最小的有权边
            Edge e = pq.poll();
            // 有权边的两个顶点 v、w
            int v = e.either();
            int w = e.other(v);
            // 过滤不符合条件的有权边 - v w 两个顶点的最小有权边已经添加到最小生成树中了
            if (marked[v] && marked[w]) continue;
            // 添加到最小生成树
            mst.add(e);
            // 下行可以注释掉，好像不影响，因为每次运行到此处 marked[v] 都是 true
            if (!marked[v]) visit(G, v);
            if (!marked[w]) visit(G, w);
        }

    }

    private void visit(EdgeWeightGraph G, int v) {
        marked[v] = true;
        // 遍历 v 的所有有权边
        for (Edge e : G.adj(v)) {
            // 如果 v - w 的另一边没有被访问标识过，则添加到优先级队列中，从小到大的顺序
            // 最小生成树需要最小的权重边
            if (!marked[e.other(v)]) pq.add(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

//    public double weight() {
//
//    }

    public static void main(String[] args) {
        LazyPrimMST mst = new LazyPrimMST(EdgeWeightGraph.init());
        for(Edge e : mst.edges()){
            System.out.println(e);
        }
    }
}
