import edu.princeton.cs.algs4.Picture;

public class PathFinder
{
    private Picture picture;

    public PathFinder(String file,String algo)
    {
        Maze maze=new Maze(file);
        maze.oneLook();

        if (algo.equals("A*"))
        {
            AStar sp = new AStar(maze.source(), maze.Nodes(), maze.end());
            maze.paintPath(sp.pathTo(maze.end()));
        }
        else if (algo.equals("BreadthFirst"))
        {
            BreadthFirst sp=new BreadthFirst(maze.source(),maze.end(),maze.Nodes());
            maze.paintPath(sp.pathTo(maze.end()));
        }
        else
        {
            System.err.println("Please Select correct algorithm");
            return;
        }

        picture = maze.picture();
    }

    public Picture solved()
    {
        return picture;
    }

}
