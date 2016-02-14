package algorithm.search;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class ConnectedCellInAGrid {
    private Map<Integer, List<Integer>> edges = new HashMap<Integer, List<Integer>>();

    public void addEdge(Integer src, Integer dest) {
        List<Integer> srcNeighbors = this.edges.get(src);
        if (srcNeighbors == null) {
            this.edges.put(src,
                    srcNeighbors = new ArrayList<Integer>()
            );
        }
        srcNeighbors.add(dest);
    }
    public void BFS(Integer startingVertex) {

        int count = 0;
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.add(startingVertex);
        visited.add(startingVertex);

        HashMap<String, String> hm = new HashMap<>();


        Iterator it = edges.entrySet().iterator();
        while (it.hasNext()) {
            if (queue.size() == 0)
                break;
            Integer next = queue.remove();

            // Get the number of children based on this current node.
            System.out.println(next);
            //System.out.print("At vertice: " + next + " : " + numChild + "\n");


            for (Integer neighbour : getNeighbors(next)) {
                if (!visited.contains(neighbour)) {
                    queue.add(neighbour);
                    visited.add(neighbour);
                }
            }
        }

        System.out.println(count);

    }

    public Iterable<Integer> getNeighbors(Integer vertex) {
        List<Integer> neighbors = this.edges.get(vertex);
        if (neighbors == null) {
            return Collections.emptyList();
        } else {
            return Collections.unmodifiableList(neighbors);
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("testcase.txt"));

        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] grid = new int[m][n];


        ConnectedCellInAGrid g = new ConnectedCellInAGrid();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //grid[i][j] = sc.nextInt();
                //g.addEdge(j,i);
            }
        }

        g.addEdge(1,2);
        g.addEdge(2,3);
        g.addEdge(4,5);
        g.addEdge(5,6);
        g.addEdge(7,8);
        g.addEdge(8,9);

        g.addEdge(1,4);
        g.addEdge(4,7);
        g.addEdge(2,5);
        g.addEdge(5,8);
        g.addEdge(3,6);
        g.addEdge(6,9);

        g.addEdge(1,5);
        g.addEdge(5,9);

        System.out.println(g.edges);


    }
}