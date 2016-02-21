package weekofcode19;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Jian on 17/02/2016. Incumbent not implemented.
 * Make two list one for global candidates one for local. The incumbent happens at level 1000?
 * same as the number of queries where the first solution is encountered.
 */
public class BBTwoRobots {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("testcase.txt"));
        int t = sc.nextInt();
        for (int test = 0; test < t; test++) {

            int minDistance = Integer.MAX_VALUE;

            int noOfContainers = sc.nextInt();
            int queries = sc.nextInt();

            int[] mA = new int[queries];
            int[] mB = new int[queries];
            for (int i = 0; i < queries; i++) {
                mA[i] = sc.nextInt();
                mB[i] = sc.nextInt();
            }

            //HashMap<Integer, ArrayList<Node>> potentialNodes = new HashMap<Integer, ArrayList<Node>>();
            LinkedList<Node> list = new LinkedList<>();
            int dist = Math.abs(mA[0] - mB[0]);
            Node root = new Node(1, dist, 0, null);
            //ArrayList<Node> rootArray = new ArrayList<>();
            //rootArray.add(root);
            //potentialNodes.put(root.boundingFunction, rootArray);
            list.add(root);
            for (; ; ) {

                //if (potentialNodes.size() == 0)
                //    break;
                //int min = Collections.min(potentialNodes.keySet());
                //ArrayList<Node> listNode = potentialNodes.get(min);
                //if (listNode.size() == 0)
                //break;
                Node node = list.pollLast();
                if(node == null)
                    break;
                //Node node = listNode.get(0);
                //listNode.remove(0);
                //if (listNode.size() == 0)
                //   potentialNodes.remove(min);
                //else
                //    potentialNodes.put(min, listNode);
                if (node.level == queries - 1) {
                    //System.out.println(node.boundingFunction);
                    minDistance = Math.min(node.boundingFunction, minDistance);
                    LinkedList<Integer> result = getRobot(node, new LinkedList<>());
                    Collections.reverse(result);
                    System.out.println(result + " " + node.boundingFunction);
                }
                for (int robot = 1; robot <= 2; robot++) {
                    int pos = getParent(node, robot);
                    if (node.level == queries - 1)
                        break;
                    int value = 0;
                    //System.out.println(pos);
                    if (pos != -1)
                        value = Math.abs(mB[pos] - mA[node.level + 1]);
                    else
                        value = 0;
                    int boundingFunction = value + node.boundingFunction + Math.abs(mA[node.level + 1] - mB[node.level + 1]);
                    Node child = new Node(robot, boundingFunction, node.level + 1, node);
                    //ArrayList<Node> arrayList = potentialNodes.getOrDefault(boundingFunction, new ArrayList<>());
                    //arrayList.add(child);
                    //potentialNodes.put(boundingFunction, arrayList);
                    list.add(child);
                }
            }
            System.out.println(minDistance);
        }
    }
    public static LinkedList getRobot(Node node, LinkedList<Integer> list) {
        list.add(node.robotNo);
        if (node.parent == null)
            return list;
        getRobot(node.parent, list);
        return list;
    }

    public static int getParent(Node node, int robotNo) {
        LinkedList<Integer> al = getRobot(node, new LinkedList<>());
        //System.out.println(al + " search for" + " " + robotNo);
        for (int i = al.size() - 1; i >= 0; i--) {
            if (al.get(i) == robotNo)
                return i;
        }
        return -1;
    }


    public static class Node {
        public int robotNo;
        public int boundingFunction;
        public int level;
        public Node parent;


        public Node(int robotNo, int boundingFunction, int level, Node parent) {
            this.robotNo = robotNo;
            this.boundingFunction = boundingFunction;
            this.level = level;
            this.parent = parent;
        }

        public String toString() {
            return "BF : " + boundingFunction + " LE : " + level + " || ";
        }
    }
}
