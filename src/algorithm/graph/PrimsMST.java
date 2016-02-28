package algorithm.graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InterruptedIOException;
import java.util.*;

/**
 * Created by Jian on 24/02/2016.
 */
public class PrimsMST {

    Map<Integer, ArrayList<Edge>> map = new LinkedHashMap<>();
    static int V;

    public void solve() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("testcase.txt"));

        int noOfNodes = sc.nextInt();

        V = noOfNodes;

        int noOfEdges = sc.nextInt();


        for (int i = 0; i < noOfEdges; ++i) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int weight = sc.nextInt();

            add(src, dest, weight);
            add(dest, src, weight);
        }

        //System.out.println(map);

        int[][] adj = createAdjacencyList(map);

        System.out.println(Arrays.deepToString(adj));

        primMST(adj);
    }

    private PriorityQueue<Integer[]> createPriorityQueue(Integer[] dist) {
        PriorityQueue<Integer[]> pq = new PriorityQueue<Integer[]>(11,
                new Comparator<Integer[]>() {
                    public int compare(Integer[] A, Integer[] B) {
                        return A[1] < B[1] ? -1 : 1;
                    }
                });
        for (int v = 0; v < dist.length; v++) {
            pq.add(new Integer[]{v, dist[v]});
        }
        return pq;
    }


    public static void main(String[] args) throws FileNotFoundException {
        PrimsMST g = new PrimsMST();
        g.solve();
    }


    // value, from the set of vertices not yet included in MST
    int minKey(int key[], Boolean mstSet[]) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }

    void printMST(int parent[], int n, int graph[][]) {

        int sum = 0;
        //System.out.println("Edge   Weight");
        try {
            for (int i = 1; i < V; i++) {
                sum += graph[i][parent[i]];
                //System.out.println(parent[i] + " - " + i + "    " +
                //        graph[i][parent[i]]);
            }

        } catch (ArithmeticException e) {
            System.out.println(e);
        }

        System.out.println(sum);
    }

    // Function to construct and print MST for a graph represented
    //  using adjacency matrix representation
    void primMST(int graph[][]) {
        // Array to store constructed MST
        int parent[] = new int[V];

        // Key values used to pick minimum weight edge in cut
        int key[] = new int[V];

        // To represent set of vertices not yet included in MST
        Boolean mstSet[] = new Boolean[V];

        // Initialize all keys as INFINITE
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Always include first 1st vertex in MST.
        key[0] = 0;     // Make key 0 so that this vertex is
        // picked as first vertex
        parent[0] = -1; // First node is always root of MST

        // The MST will have V vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick thd minimum key vertex from the set of vertices
            // not yet included in MST
            int u = minKey(key, mstSet);

            // Add the picked vertex to the MST Set

            mstSet[u] = true;

            // Update key value and parent index of the adjacent
            // vertices of the picked vertex. Consider only those
            // vertices which are not yet included in MST
            for (int v = 0; v < V; v++)

                // graph[u][v] is non zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v]
                if (graph[u][v] != 0 && mstSet[v] == false &&
                        graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
        }

        // print the constructed MST
        printMST(parent, V, graph);
    }


    public int[][] createAdjacencyList(Map m) {
        int[][] adj = new int[m.size()][m.size()];

        for (int[] i : adj)
            Arrays.fill(i, 0);

        //System.out.println();
        //System.out.println(Arrays.deepToString(adj));

        for (Integer i : map.keySet()) {
            adj[i - 1][i - 1] = 0;
            for (Edge e : map.get(i)) {
                adj[i - 1][e.dest - 1] = e.weight;
            }

        }
        //System.out.println(Arrays.deepToString(adj));

        return adj;
    }


    public void add(int src, int dest, int weight) {
        ArrayList<Edge> edges = map.getOrDefault(src, new ArrayList<>());
        Edge e = new Edge(src, dest, weight);
        if (edges.contains(e)) {

            // Pick the least one
            if (weight >= e.weight) {
                edges.add(e);
            }
        } else {
            edges.add(e);
        }
        map.put(src, edges);
    }

    public class Edge {
        int src;
        int dest;
        int weight;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Edge edge = (Edge) o;

            if (src != edge.src) return false;
            return dest == edge.dest;

        }

        @Override
        public int hashCode() {
            int result = src;
            result = 31 * result + dest;
            return result;
        }

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        public String toString() {
            return "S: " + src + "  " + "D: " + dest + "  " + "W: " + weight + " ||";
        }
    }
}
