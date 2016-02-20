package weekofcode19;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Jian on 17/02/2016. Does not handle ties. Incumbent not implemented.
 * Make two list one for global canidates one for local. The incumbent happens at level 1000
 * same as the number of queries where the first solution is encountered.
 */
public class BBTwoRobots {


    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("testcase.txt"));
        int t = sc.nextInt();

        for (int test = 0; test < t; test++) {

            int noOfContainers = sc.nextInt();
            int queries = sc.nextInt();

            int[] mA = new int[queries];
            int[] mB = new int[queries];
            for (int i = 0; i < queries; i++) {
                mA[i] = sc.nextInt();
                mB[i] = sc.nextInt();
            }

            int minDistance = Integer.MAX_VALUE;

            HashMap<Integer, ArrayList<Node>> potentialNodes = new HashMap<Integer, ArrayList<Node>>();

            Node root = new Node();
            root.robotNo = 1;
            root.parent = null;
            root.boundingFunction = Math.abs(mA[0] - mB[0]);
            root.level = 0;

            ArrayList<Node> al = new ArrayList<>();
            al.add(root);
            potentialNodes.put(root.boundingFunction, al);

            for (; ; ) {

                if (potentialNodes.size() == 0)
                    break;

                int min = Collections.min(potentialNodes.keySet());
                ArrayList<Node> listNode = potentialNodes.get(min);

                Node node = listNode.get(0);
                listNode.remove(0);

                if (listNode.size() == 0)
                    potentialNodes.remove(min);
                else
                    potentialNodes.put(min, listNode);

                if (node.level == mA.length - 1) {
                    System.out.println(node);
                    System.out.println(print(node));
                    System.out.println(node.boundingFunction);
                    break;
                }


                if (node.level + 1 < queries) {
                    for (int robot = 1; robot <= 2; robot++) {
                        Node n = new Node();
                        n.robotNo = robot;
                        n.parent = node;
                        n.level = node.level + 1;

                        int value = 0;
                        Node previousNode = find(robot, node);

                        if (previousNode == null) {
                            value = 0;
                        } else {
                            value = Math.abs(mB[previousNode.level] - mA[n.level]);
                        }

                        int queryDistance = Math.abs(mA[n.level] - mB[n.level]);

                        n.boundingFunction = value + queryDistance + node.boundingFunction;

                        if (potentialNodes.containsKey(n.boundingFunction)) {
                            ArrayList<Node> list = potentialNodes.get(n.boundingFunction);
                            list.add(n);
                            potentialNodes.put(n.boundingFunction, list);
                        } else {
                            ArrayList<Node> list = new ArrayList<>();
                            list.add(n);
                            potentialNodes.put(n.boundingFunction, list);
                        }
                    }
                }
            }
        }

    }


    public static int distanceSoFar(int[] mA, int[] mB, int[] moves) {
        int distance = 0;
        int r1Pos = 0;
        int r2Pos = 0;

        for (int i = 0; i < moves.length; i++) {
            if (i < moves.length) {
                if (moves[i] == 1) {
                    if (r1Pos != 0)
                        distance += Math.abs(r1Pos - mA[i]);
                    distance += Math.abs(mA[i] - mB[i]);
                    r1Pos = mB[i];
                }
                if (moves[i] == 2) {
                    if (r2Pos != 0) {
                        distance += Math.abs(r2Pos - mA[i]);
                    }
                    distance += Math.abs(mA[i] - mB[i]);
                    r2Pos = mB[i];
                }

            }
        }
        return distance;
    }

    public static String print(Node node) {
        if (node.parent == null)
            return "";
        System.out.println(node.parent);
        print(node.parent);
        return "";
    }

    /**
     * This method finds the nearest R1 or R2 parent.
     *
     * @return
     */
    public static Node find(int robot, Node node) {
        if (node.robotNo == robot) {
            return node;
        } else {
            if (node.parent == null)
                return null;
            find(robot, node.parent);
        }
        return null;
    }

    public static class Node {
        public int robotNo;
        public int boundingFunction;
        public int level;
        public Node parent;

        public String toString() {
            return "Level : " + level + " R : " + robotNo + " BF: " + boundingFunction + "||";
        }

    }
}
