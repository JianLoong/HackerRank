import java.util.Iterator;
import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * Created by Jian on 12/02/2016.
 */
public class DijkstraShortestReach2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int cases = in.nextInt();
        for (int b = 0; b < cases; b++) {
            int n = in.nextInt();
            int m = in.nextInt();
            Graph g = new Graph(n);
            for (int i = 0; i < m; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                int w = in.nextInt();
                g.addEdge(x, y, w);
            }
            int s = in.nextInt();
            minHeap mp = new minHeap(n);
            Node firstNode = new Node(s, 0);
            mp.decreaseKey(firstNode);
            int[] distance = new int[n + 1];
            while (mp.size != 0) {
                Node u = mp.extractMin();
                distance[u.value] = u.dist;
                Iterator<Edge> j = g.myList[u.value].iterator();
                while (j.hasNext()) {
                    Edge adj = j.next();
                    if (mp.isInHeap(mp.pos[adj.dest]) && u.dist != Integer.MAX_VALUE && adj.weight + u.dist < mp.heap[mp.pos[adj.dest]].dist) {
                        Node adjNode = new Node(adj.dest, adj.weight + u.dist);
                        mp.decreaseKey(adjNode);
                    }
                }
            }
            for (int i = 1; i <= n; i++) {
                if (distance[i] == Integer.MAX_VALUE)
                    System.out.print("-1 ");
                else {
                    if (i != s)
                        System.out.print(distance[i] + " ");
                }
            }
            System.out.println();
        }
    }
}

class Edge {
    public int dest;
    public int weight;

    public Edge(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }
}

class Graph {
    public int V;
    LinkedList<Edge>[] myList;

    public Graph(int V) {
        this.V = V;
        myList = new LinkedList[V + 1];
        for (int i = 1; i <= V; i++)
            myList[i] = new LinkedList();
    }

    public void addEdge(int src, int dest, int weight) {
        Edge e1 = new Edge(dest, weight);
        Edge e2 = new Edge(src, weight);
        myList[src].add(e1);
        myList[dest].add(e2);
    }
}

class Node {
    public int value;
    public int dist;

    public Node(int value, int dist) {
        this.value = value;
        this.dist = dist;
    }
}

class minHeap {
    public int capacity;
    public int size;
    Node[] heap;
    int[] pos;

    public minHeap(int capacity) {
        this.capacity = capacity;
        this.size = capacity;
        heap = new Node[capacity + 1];
        pos = new int[capacity + 1];
        for (int i = 0; i < capacity; i++) {

            heap[i] = new Node(i + 1, Integer.MAX_VALUE);
            pos[i + 1] = i;
        }
    }

    public int left(int index) {
        return 2 * index + 1;
    }

    public int right(int index) {
        return 2 * index + 2;
    }

    public int parent(int index) {
        return ((index - 1) / 2);
    }

    public void decreaseKey(Node nd) {
        int index = pos[nd.value];
        if (nd.dist < heap[index].dist) {
            heap[index] = nd;
            while (index > 0 && heap[parent(index)].dist > heap[index].dist) {
                pos[heap[parent(index)].value] = index;
                pos[heap[index].value] = parent(index);
                Node temp = heap[parent(index)];
                heap[parent(index)] = heap[index];
                heap[index] = temp;
                index = parent(index);
            }
        }
    }

    public Node extractMin() {
        if (size == 1) {
            size = 0;
            return heap[0];
        } else {
            Node root = heap[0];
            Node leaf = heap[size - 1];
            heap[0] = leaf;
            pos[root.value] = size - 1;
            pos[leaf.value] = 0;
            min_hippfy(0);
            size = size - 1;
            return root;
        }

    }

    public void min_hippfy(int i) {
        int left = left(i);
        int right = right(i);
        int smallest = i;
        if (left <= (size - 1) && heap[left].dist < heap[smallest].dist) {
            smallest = left;
        }
        if (right <= (size - 1) && heap[right].dist < heap[smallest].dist) {
            smallest = right;
        }
        if (smallest != i) {
            Node temp1 = heap[smallest];
            Node temp2 = heap[i];
            heap[smallest] = temp2;
            heap[i] = temp1;
            pos[temp1.value] = i;
            pos[temp2.value] = smallest;
            min_hippfy(smallest);
        }
    }

    public boolean isInHeap(int x) {
        if (x < size) return true;
        else return false;
    }
}
