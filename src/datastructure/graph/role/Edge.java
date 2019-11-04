package datastructure.graph.role;/**
 * Created by Administrator on 2019/10/25.
 */

/**
 * @author yejianbin
 * @version 1.0
 * @ClassName Edge
 * @Description 无向加权边的 API
 * @Date 2019/10/25 14:45
 **/
public class Edge implements Comparable<Edge> {

    private final int v;
    private final int w;
    private final double weight;

    Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    // 边的权重
    double weight(){
        return weight;
    }

    // 边两端的顶点之一
    int either(){
        return v;
    }

    // 另一个顶点
    int other(int vertex){
        if (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new RuntimeException("Inconsistent edge");
    }

    @Override
    public String toString() {
        return String.format("v="+v+" w="+w+" weight="+weight);
    }

    // 将一条边 e 与 that 比较
    @Override
    public int compareTo(Edge that) {
        if (this.weight < that.weight()) return -1;
        else if (this.weight > that.weight()) return 1;
        else return 0;
    }
}
