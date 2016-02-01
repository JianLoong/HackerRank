import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by Jian on 29/01/2016.
 */
public class BFSShortReach {


    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("testcase.txt"));


        int noOfTestCases = sc.nextInt();

        for (int x = 0; x < noOfTestCases; x++) {

            int vertices = sc.nextInt();
            int edges = sc.nextInt();
            ArrayList<String> strs = new ArrayList<>();

            sc.nextLine();

            for (int i = 0; i < edges; i++) {
                String s = sc.nextLine();
                strs.add(s);
            }

            int startNode = sc.nextInt();

            for (String s : strs) {
                String[] str = s.split(" ");

            }

            //Shortest distance to all other nodes.
            //System.out.println(tree.getNodes());

            System.out.println();
        }


    }

}

