import util.Node;
import util.TraversalStrategy;
import util.Tree;
import util.TreeNode;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by Jian on 29/01/2016.
 */
public class EvenTree {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(new FileReader("testcase.txt"));

        Tree tree = new Tree();

        int vertices = sc.nextInt();
        int edges = sc.nextInt();


        tree.addNode("1");
        sc.nextLine();

        while(sc.hasNextLine()){
            String str = sc.nextLine();
            String [] nodes = str.split(" ");
            tree.addNode(nodes[0], nodes[1]);

        }


    }
}



