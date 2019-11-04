package datastructure.graph.directed;/**
 * Created by Administrator on 2019/10/24.
 */

import java.util.Iterator;
import java.util.Stack;

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName DirectedCycle
 * @Description 有向环
 * @Date 2019/10/24 15:15
 **/
public class DirectedCycle {

    private boolean[] marked;
    private int[] edgeTo;
    // 有向环中的所有顶点（如果存在的话）
    private Stack<Integer> cycle;
    // 递归调用栈上的所有顶点
    private boolean[] onStack;

    // 寻找有向环的构造函数
    DirectedCycle(Digraph g) {
        onStack = new boolean[g.V()];
        edgeTo = new int[g.V()];
        marked = new boolean[g.V()];
        for (int i = 0; i < g.V(); i++) {
            if (!marked[i]) {
                dfs(g,i);
            }
        }
    }

    private void dfs(Digraph g, int v){

        onStack[v] = true;
        marked[v] = true;

        Iterator<Integer> it = g.adj(v);
        while (it.hasNext()) {
            int w = it.next();
            // 如果找到有向环，就不再继续执行
            if (this.hasCycle()) {
                return;
            }
            // 如果没有找到环，就判断当前元素是否已经被标记，如果没有被标记则继续执行
            else if (!marked[w]) {
                // 维护路径中当前顶点的上一个顶点，可从 edgeTo 数组获得有向环的路径（顶点）
                // 之前已经遍历的路径没有产生环，因此可以被覆盖
                edgeTo[w] = v;
                dfs(g, w);
            }
            // 判断是否在遍历的栈上,如果在则证明存在有向环
            else if (onStack[w]) {
                // 遍历有向环
                cycle = new Stack<>();

                for (int x = v; x != w; x=edgeTo[x]) {
                    cycle.push(x);
                }

                cycle.push(w);
                cycle.push(v);
            }

            onStack[v] = false;
        }
    }

    // 是否含有有向环
    boolean hasCycle() {
        return cycle != null;
    }

    // 有向环中的所有顶点（如果存在的话）
    Stack<Integer> cycle(){
        return cycle;
    }

    public static void main(String[] args) {
        Digraph dg = new Digraph(6);
//        dg.addEdge(0,5);
//        dg.addEdge(5,4);
//        dg.addEdge(4,3);
//        dg.addEdge(3,5);

        DirectedCycle directedCycle = new DirectedCycle(dg);

        Stack<Integer> result = directedCycle.cycle();
        StringBuilder sb = new StringBuilder();
        int len = result.size();
        for (int i=0;i<len;i++) {
            sb.append(result.pop()).append(">");
        }
        String out = sb.toString();
        System.out.println(out.substring(0,out.length()-1));
    }
}
