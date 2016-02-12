import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class BFSShortReach {

    private Map<Integer, List<Integer>> adjacencyList = new HashMap<Integer, List<Integer>>();

    public void addEdge(Integer src, Integer dest) {
        List<Integer> srcNeighbors = this.adjacencyList.get(src);
        if (srcNeighbors == null) {
            this.adjacencyList.put(src,
                    srcNeighbors = new ArrayList<Integer>()
            );
        }
        srcNeighbors.add(dest);
    }

    public void solve(int startingVertice, int totalNodes) {

        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> visited = new HashMap<Integer, Integer>();
        Map<Integer, Integer> cost = new HashMap<>();

        queue.add(startingVertice);
        cost.put(startingVertice, 0);

        while (!queue.isEmpty()) {
            int node = queue.remove();
            if (visited.get(node) != null) {
                continue;
            }

            visited.put(node, 1);
            int nodeCost = cost.get(node);
            List<Integer> children = adjacencyList.get(node);
            if (children != null) {
                for (Integer child : children) {
                    int total = nodeCost + 6;
                    if (visited.get(child) == null) {
                        queue.add(child);
                        if (!cost.containsKey(child)) {
                            cost.put(child, total);
                        } else {
                            if (cost.get(child) > total) {
                                cost.put(child, total);
                            }
                        }
                    }
                }
            }
        }

        for (int i = 1; i <= totalNodes; i++) {
            if (i != startingVertice) {
                System.out.print(cost.get(i) == 0 ? -1 + " " : cost.get(i) + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("testcase.txt"));
        int noOfTestCases = sc.nextInt();

        for (int i = 0; i < noOfTestCases; i++) {
            int totalNodes = sc.nextInt();
            int totalEdges = sc.nextInt();

            BFSShortReach bfs = new BFSShortReach();

            for (int j = 0; j < totalEdges; j++) {
                int src = sc.nextInt();
                int dest = sc.nextInt();
                bfs.addEdge(src, dest);
                bfs.addEdge(dest, src);
            }

            int startingVertice = sc.nextInt();
            bfs.solve(startingVertice, totalNodes);
        }
    }
}