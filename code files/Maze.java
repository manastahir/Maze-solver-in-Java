import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.Stopwatch;
import java.awt.*;

public class Maze
{

    /*
    H  = height of the maze
    W = Width of the maze
    n = number of nodes formed from the maze

    start = Entry point of the maze
    end = Exit point of the maze

    isWhite = store if the block(pixel) is white

    heuristic = euclidean distance from the end
     */

    private int H,W,n;
    private node start,end;
    private Picture image;
    private boolean[][] isWhite;

    public Maze(String fileName)
    {
        image=new Picture(fileName);
        H=image.height();
        W=image.width();

        isWhite=new boolean[H][W];

        for(int y=0;y<H;y++)
            for(int x=0;x<W;x++)
                isWhite[y][x]=!isBlack(image.getRGB(x,y));
    }

    // method to make grid of nodes from the Maze
    public void oneLook()
    {
        //maintaining the list of all nodes in the upper row which are not connected to nodes below them
        node[] toConnectUp=new node[W];

        //node whose connection is to be made if another node is found to its right
        node toConnectLeft=null;

        //finding exit point
        int y=H-1;
        for(int x=0;x<W;x++)
            if(isWhite[y][x])
            {
                end=new node(x,y,n);
                n++;
                break;
            }

        //finding starting point
        for(int x=0;x<W;x++)
            if(isWhite[0][x])
            {
                start=new node(x,0,n);
                toConnectUp[x]=start;

                start.h=dist(start,end);
                n++;
                break;
            }

        // scanning tha maze from left to right and making connections
        for(y=1;y<H-1;y++)
            for(int x=1;x<W;x++)
            {
                if(!isWhite[y][x])
                {
                    // discard left node and the node in the upper row if thr wall is found
                    toConnectLeft=null;
                    toConnectUp[x]=null;
                    continue;
                }

                //finding the intersection and creating a node there
                if(isWhite[y][x+1]||isWhite[y][x-1])
                    if(isWhite[y-1][x]||isWhite[y+1][x])
                    {
                        node temp=new node(x,y,n);

                        //setting heuristic
                        temp.h=dist(temp,end);

                        //making left and upper connection
                        temp.left=toConnectLeft;

                        //making a two way connection
                        if(toConnectLeft!=null)
                            toConnectLeft.right=temp;

                        //replace the old left node
                        toConnectLeft = temp;

                        //making upper connection
                        temp.up=toConnectUp[x];

                        //making two way connection
                        if(toConnectUp[x]!=null)
                            toConnectUp[x].down=temp;

                        //replace the connected node
                        toConnectUp[x]=temp;
                        n++;
                    }
            }

        end.up=toConnectUp[end.x];

        if(toConnectUp[end.x]!=null)
            toConnectUp[end.x].down=end;
    }

    //provided a path, it paints it in green
    //given that the nodes are only at the intersections,but we have to paint all the blocks between the two nodes
    public void paintPath(Iterable<node> Path)
    {
        int rgb = new Color(14, 255, 32).getRGB(),dx;

        Stack<node> path=(Stack<node>)Path;

        node prev=path.pop(),current;

        while (!path.isEmpty())
        {
            current=path.pop();

            dx = Math.abs(current.x-prev.x);
            if(dx == 0) // if the two nodes represent a horizontal path
            {
                int y1=Math.min(current.y,prev.y),y2=Math.max(current.y,prev.y);
                for (int y = y1; y <= y2; y++)
                    image.setRGB(current.x, y, rgb);
            }
            else // if the two nodes represent a vertical path
            {
                int x1=Math.min(current.x,prev.x),x2=Math.max(current.x,prev.x);
                for (int x = x1; x <= x2; x++)
                    image.setRGB(x, current.y, rgb);
            }
            prev=current;
        }
    }

    public Picture picture()
    {
        return image;
    }

    public int Nodes()
    {
        return n;
    }

    public node source()
    {
        return start;
    }

    public node end()
    {
        return end;
    }

    //heuristic, euclidean distance between the current node and the exit node
    private double dist(node v,node w)
    {
        return Math.sqrt(Math.pow(v.x-w.x,2)+Math.pow(v.y-w.y,2));
    }

    private boolean isBlack(int rgb)
    {
        int r=(rgb >> 16) & 0xFF;
        int g=(rgb >> 8) & 0xFF;
        int b=(rgb >> 0) & 0xFF;

        return (r==0 && g==0 && b==0);
    }
}
