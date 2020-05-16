import edu.princeton.cs.algs4.Stack;

public class node implements Comparable<node>
{
    /*
    x = x.coordinate of the node
    y = y.corrdinate of the node
    n = index number of the node
    left,right,up,down = neighbouring nodes

    f = total cost
    g = edge cost
    h = heuristic cost
    f = g + h
     */

    public int x,y,n;
    public double f,g,h;
    public node left,right,up,down;

    public node(int x,int y,int n)
    {
        this.x=x;
        this.y=y;
        this.n=n;
        //cost before node discovery is infinite
        f=Double.POSITIVE_INFINITY;
    }

    public Iterable<node> ajd()
    {
        // return the neighbours of the node(if they exist)

        Stack<node> adj=new Stack<>();

        if(left!=null)adj.push(left);
        if(right!=null)adj.push(right);
        if(up!=null)adj.push(up);
        if(down!=null)adj.push(down);

        return adj;
    }

    @Override
    public int compareTo(node that)
    {
        // comparison based on the total cost of reaching the node

        if(this.f>that.f) return +1;
        else if(this.f<that.f) return -1;
        return 0;
    }

    public boolean equals(Object O)
    {
       // check the node's position to return if two nodes are same
       node that= (node) O;
       return (this.x==that.x && this.y==that.y);
    }
}
