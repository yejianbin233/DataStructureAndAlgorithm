package datastructure.graph.directed;/**
 * Created by Administrator on 2019/10/24.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName Digraph
 * @Description 有向图
 * @Date 2019/10/24 10:20
 **/
public class Digraph {
    private final int v;
    private int e;
    private List<List<Integer>> adj;

    public Digraph(int v) {
        this.v = v;
        this.e = 0;
        adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(new LinkedList<>());
        }
    }

    public int V() {
        return v;
    }

    public int E() {
        return e;
    }

    public void addEdge(int v, int w) {
        adj.get(v).add(w);
        e++;
    }

    public Iterator<Integer> adj(int v) {
        return adj.get(v).iterator();
    }

    // 有向图取反
    Digraph reverse() {
        Digraph r = new Digraph(v);
        for (int i = 0; i < v; i++) {
            Iterator<Integer> it = adj(i);
            while (it.hasNext()) {
                int x = it.next();
                r.addEdge(x, i);
            }
        }
        return r;
    }

    @Override
    public String toString() {
        String s = v + " vertices, " + e + "edges\n";

        for (int v=0; v < this.v; v++) {
            s += v + ": ";
            Iterator<Integer> it = this.adj(v);
            while(it.hasNext()) {
                s += it.next() + " ";
            }
            s += "\n";
        }
        return s.toString();
    }

    public static void main(String[] args) {
        Digraph digraph = init();
        System.out.println(digraph.toString());
        Digraph recever = digraph.reverse();
        System.out.println(recever.toString());
    }



    public static Digraph init(){
        Digraph g = new Digraph(7);
        g.addEdge(0,1);
        g.addEdge(1,2);
        g.addEdge(2,3);
        g.addEdge(3,4);
        g.addEdge(4,5);
        g.addEdge(5,6);
        return g;
    }
}
