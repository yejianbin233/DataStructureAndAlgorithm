package datastructure.graph.handle.path;/**
 * Created by Administrator on 2019/10/22.
 */

import datastructure.graph.Graph;
import datastructure.graph.handle.Search;
import datastructure.graph.handle.path.DepthFirstPaths;

import java.util.Iterator;
import java.util.Stack;

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName Paths
 * @Description 寻找路径
 * @Date 2019/10/22 17:03
 **/
public class Paths {
    private int s;
    private Graph g;

    public Paths(Graph g, int s) {
        this.g = g;
        this.s = s;
    }

    // 判断 s - v 之间是否存在路径
    boolean hasPathTo(int v) {
        return false;
    }

    // s 到 v 的路径，如果不存在则返回 null
    Iterator<Integer> pathTo() {
        return null;
    }

    public static void main(String[] args) {
        Graph g = Search.initGraph(false);

        int s = 0;

        // 深度优先遍历
        // DepthFirstPaths search = new DepthFirstPaths(g, s);

        // 广度优先遍历
        BreadthFirstPaths search = new BreadthFirstPaths(g, s);
        for (int v = 0; v < g.V(); v++) {
            System.out.print(s + " to " + v + "：");

            if (search.hasPathTo(v)) {
//                Iterator<Integer> it = search.pathTo(v);
                Stack<Integer> path = search.pathTo(v);
                while(!path.isEmpty()){
                    int x = path.pop();
                    if (x == s) {
                        System.out.print(x);
                    } else {
                        System.out.print("-" + x);
                    }
                }
                System.out.println();
            }
        }
    }
}
