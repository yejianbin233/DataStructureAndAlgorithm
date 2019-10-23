package datastructure.graph.handle;/**
 * Created by Administrator on 2019/10/22.
 */

import datastructure.graph.Graph;

import java.util.Iterator;

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName Search
 * @Description 图处理算法 API - Search
 * @Date 2019/10/22 10:32
 **/
public class Search {

    private int s;
    private Graph g;
    private int count;

    // 找到和起点 s 连通的所有顶点
    public Search(Graph g, int s) {
        this.s = s;
        this.g = g;
        count = 0;
    }

    // 判断 v 和 s 是否连通
    boolean marked(int v) {
        Iterator<Integer> it = g.adj(s);
        while (it.hasNext()){
            if (it.next() == v){
                count++;
                return true;
            }
        }
        return false;
    }

    // 与 s 连通的顶点总数
    int count() {
        return this.count;
    }

    // 测试运行
    public static void main(String[] args) {
        // 获取初始化图, true - 连通图 : false - 非连通图
//        Graph g = initGraph(true);
        Graph g = initGraph(false);
        int s = 1;
        Search search = new Search(g, s);

        for (int v = 0; v < g.V(); v++) {
            if (search.marked(v)) {
                System.out.println(v + " ");
            }
            System.out.println();
        }

        // 如果顶点 s 的连接顶点数与图中的顶点数与边的对应关系 g.V() - 1 不一样则说明不是连通图
        if (search.count() != g.V() - 1) {
            System.out.println("Not ");
        } else {
            System.out.println("connected");
        }
    }

    public static Graph initGraph(boolean isConnected){
        return isConnected ? connectedGraph() : notGraph();
    }
    private static Graph notGraph(){
        Graph g = new Graph(7);
        // 0 - {1,2,5,6}
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(0,5);
        g.addEdge(0,6);
        // 1 - {0}
        g.addEdge(1,0);
        // 2 - {0}
        g.addEdge(2,0);
        // 5 - {0,3,4}
        g.addEdge(5,0);
        g.addEdge(5,3);
        g.addEdge(5,4);
        // 3 - {5,4}
        g.addEdge(3,5);
        g.addEdge(3,4);
        // 4 - {3,5,6}
        g.addEdge(4,3);
        g.addEdge(4,4);
        g.addEdge(4,6);
        // 6 - {0,4}
        g.addEdge(6,0);
        g.addEdge(6,4);
        return g;
    }
    private static Graph connectedGraph(){
        Graph g = new Graph(2);

        // 0 - {1}
        g.addEdge(0,1);
        // 1 - {0}
        g.addEdge(1,0);

        return g;
    }
}
