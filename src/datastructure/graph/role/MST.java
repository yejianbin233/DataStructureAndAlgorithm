package datastructure.graph.role;/**
 * Created by Administrator on 2019/10/25.
 */

import datastructure.bag.Bag;

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName MST
 * @Description 最少生成树 API（TODO）
 * @Date 2019/10/25 15:46
 **/
public class MST {
    private double weight;
    private Bag<Edge> bag;
    private boolean[] marked;
    public MST(EdgeWeightGraph G) {
        bag = new Bag<>();
        marked = new boolean[G.V()];
        dfs(G,0);
    }
    private void dfs(EdgeWeightGraph G, int s){
        marked[s] = true;
        // 要遍历所有的边，要按照权重遍历
        // 排序 G.adj(s)
        // 深度优先遍历，从做小权重的便开始添加，直到最小生成树边数为 V-1
        for (Edge v:G.adj(s)) {
            //
            if (!marked[v.either()]) {
                dfs(G,v.either());
            }
        }
    }

    Iterable<Edge> edges() {
        return null;
    }

    double weight() {
        return weight;
    }

    public static void main(String[] args) {
        EdgeWeightGraph G = new EdgeWeightGraph(7);
        MST mst = new MST(G);
        for (Edge e : mst.edges()){
            System.out.println(e);
        }
        System.out.println(mst.weight());
    }
}
