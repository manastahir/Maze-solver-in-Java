import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class BreadthFirst
{
    node edgeTo[];
    boolean marked[];
    node source,end;
    int n;
    int visited;

    public BreadthFirst(node source,node end,int n)
    {
        this.edgeTo=new node[n];
        this.marked=new boolean[n];
        this.source=source;
        this.end=end;
        this.visited = 0;
        bfs();
    }

    private void bfs() {
        Queue<node> toVisit = new Queue<>();
        node current = source;

        toVisit.enqueue(current);

        while (current.y != end.y)
        {
            this.visited++;
            current = toVisit.dequeue();

            for (node e : current.ajd())
            {
                if (!marked[e.n])
                {
                    marked[e.n] = true;
                    toVisit.enqueue(e);
                    edgeTo[e.n] = current;
                }
            }
        }
    }

    //gives path from the source to a given node
    public Iterable<node> pathTo(node v)
    {
        Stack<node> path=new Stack<>();
        while (v!=source){path.push(v); v=edgeTo[v.n];}
        path.push(source);
        return path;
    }

    public int getVisited() {
        return visited;
    }
}
