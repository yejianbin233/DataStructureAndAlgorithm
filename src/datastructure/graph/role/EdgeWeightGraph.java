package datastructure.graph.role;/**
 * Created by Administrator on 2019/10/25.
 */

import datastructure.bag.Bag;

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName EdgeWeightGraph
 * @Description 无向加权图
 * @Date 2019/10/25 14:48
 **/
public class EdgeWeightGraph {

    private final int V;
    private int E;
    private Bag<Edge>[] adj;

    // 创建一幅含有 V 个顶点的空图
    public EdgeWeightGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Edge>();
        }
    }

    // 图的顶点数
    int V() {
        return V;
    }

    // 图的边数
    int E() {
        return E;
    }

    // 添加有权边
    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        // 分别向边的两个顶点添加有权边
        adj[v].add(e);
        adj[w].add(e);

        E++;
    }

    // 和 v 相关的所有有权边
    Iterable<Edge> adj(int v) {
        return adj[v];
    }

    // 有权图的所有边
    Iterable<Edge> edges() {
        Bag<Edge> b = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (Edge e : adj[v]) {
                if (e.other(v) > v) {
                    b.add(e);
                }
            }
        }
        return b;
    }

    public static EdgeWeightGraph init(){
        EdgeWeightGraph G = new EdgeWeightGraph(6);
        // 最小生成树
        G.addEdge(new Edge(0,1,1));
        G.addEdge(new Edge(1,2,1));
        G.addEdge(new Edge(2,3,1));
        G.addEdge(new Edge(3,4,1));
        G.addEdge(new Edge(4,5,1));

        // 混淆，weight 为随机指定
        G.addEdge(new Edge(0,3,2));
        G.addEdge(new Edge(0,5,3));
        G.addEdge(new Edge(3,5,4));
        G.addEdge(new Edge(1,4,5));
        return G;
    }
}
