import edu.princeton.cs.algs4.Stopwatch;
import java.util.Scanner;
public class Main
{

    public static void main(String args[])
    {
        Scanner scan = new Scanner(System.in);
        String input_file,output_file;
        int algo;

        System.out.print("Enter Inputfile name: ");
        input_file=scan.nextLine();

        System.out.print("Enter Outputfile name: ");
        output_file=scan.nextLine();

        System.out.print("1. A*\n2.Breadth First\n");
        algo=scan.nextInt();

        PathFinder pf=new PathFinder(input_file, (algo == 1 ? "A*" : "BreadthFirst"));
        pf.solved().save(output_file);
    }
}