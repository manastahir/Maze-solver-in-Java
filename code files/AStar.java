import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

public class AStar
{
    // Simple A* sreach algorithm using Min Priority Queue
    private int N;
    private IndexMinPQ<node> IPQ;
    private node[] edgeTo;
    private node source;
    private int visited;
    public AStar(node source,int N,node end)
    {
        this.N=N;
        this.edgeTo=new node[N];
        this.IPQ=new IndexMinPQ<>(N);
        this.source=source;
        this.visited=0;
        node prev;

        IPQ.insert(1,source);
        this.source.f=0.0;

        while (!IPQ.isEmpty())
        {
            prev=IPQ.minKey();
            IPQ.delMin();

            if(prev.equals(end)) break;

            for(node current : prev.ajd())
                relax(prev,current);

            visited++;
        }
    }

    private void relax(node from,node to)
    {
        int w=to.n;

        if(from.x==to.x)
            to.g=Math.abs(from.y-to.y)+from.g;

        else
            to.g=Math.abs(from.x-to.x)+from.g;

        if (to.f < to.g+to.h) return;
        to.f = to.g+to.h;
        edgeTo[w] = from;

        if(IPQ.contains(w)) IPQ.changeKey(w, to);
        else IPQ.insert(w,to);
    }

    //gives path from the source to a given node
    public Iterable<node> pathTo(node v)
    {
        Stack<node> path=new Stack<>();
        while (v!=source){path.push(v); v=edgeTo[v.n];}
        path.push(source);
        return path;
    }

    public int getVisited()
    {
        return visited;
    }
}