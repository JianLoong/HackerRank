package weekofcode19;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class CoolGuyAndTwoSubsequences {

    private Node[] heap;
    private int[] array;
    private int size;

    public CoolGuyAndTwoSubsequences(int[] array) {
        this.array = Arrays.copyOf(array, array.length);
        size = (int) (2 * Math.pow(2.0, Math.floor((Math.log((double) array.length) / Math.log(2.0)) + 1)));
        heap = new Node[size];
        build(1, 0, array.length);
    }

    public int size() {
        return array.length;
    }

    private void build(int v, int from, int size) {
        heap[v] = new Node();
        heap[v].from = from;
        heap[v].to = from + size - 1;

        if (size == 1) {
            heap[v].sum = array[from];
            heap[v].min = array[from];
        } else {
            build(2 * v, from, size / 2);
            build(2 * v + 1, from + size / 2, size - size / 2);
            heap[v].sum = heap[2 * v].sum + heap[2 * v + 1].sum;
            heap[v].min = Math.min(heap[2 * v].min, heap[2 * v + 1].min);
        }
    }


    public int RSQ(int from, int to) {
        return RSQ(1, from, to);
    }

    private int RSQ(int v, int from, int to) {
        Node n = heap[v];

        if (n.pendingVal != null && contains(n.from, n.to, from, to)) {
            return (to - from + 1) * n.pendingVal;
        }
        if (contains(from, to, n.from, n.to)) {
            return heap[v].sum;
        }
        if (intersects(from, to, n.from, n.to)) {
            propagate(v);
            int leftSum = RSQ(2 * v, from, to);
            int rightSum = RSQ(2 * v + 1, from, to);

            return leftSum + rightSum;
        }
        return 0;
    }

    public int RMinQ(int from, int to) {
        return RMinQ(1, from, to);
    }

    private int RMinQ(int v, int from, int to) {
        Node n = heap[v];
        if (n.pendingVal != null && contains(n.from, n.to, from, to)) {
            return n.pendingVal;
        }

        if (contains(from, to, n.from, n.to)) {
            return heap[v].min;
        }

        if (intersects(from, to, n.from, n.to)) {
            propagate(v);
            int leftMin = RMinQ(2 * v, from, to);
            int rightMin = RMinQ(2 * v + 1, from, to);

            return Math.min(leftMin, rightMin);
        }

        return Integer.MAX_VALUE;
    }


    public void update(int from, int to, int value) {
        update(1, from, to, value);
    }

    private void update(int v, int from, int to, int value) {

        Node n = heap[v];
        if (contains(from, to, n.from, n.to)) {
            change(n, value);
        }

        if (n.size() == 1) return;

        if (intersects(from, to, n.from, n.to)) {
            propagate(v);
            update(2 * v, from, to, value);
            update(2 * v + 1, from, to, value);
            n.sum = heap[2 * v].sum + heap[2 * v + 1].sum;
            n.min = Math.min(heap[2 * v].min, heap[2 * v + 1].min);
        }
    }

    private void propagate(int v) {
        Node n = heap[v];

        if (n.pendingVal != null) {
            change(heap[2 * v], n.pendingVal);
            change(heap[2 * v + 1], n.pendingVal);
            n.pendingVal = null;
        }
    }

    private void change(Node n, int value) {
        n.pendingVal = value;
        n.sum = n.size() * value;
        n.min = value;
        array[n.from] = value;

    }

    private boolean contains(int from1, int to1, int from2, int to2) {
        return from2 >= from1 && to2 <= to1;
    }

    private boolean intersects(int from1, int to1, int from2, int to2) {
        return from1 <= from2 && to1 >= from2   //  (.[..)..] or (.[...]..)
                || from1 >= from2 && from1 <= to2; // [.(..]..) or [..(..)..
    }

    static class Node {
        int sum;
        int min;
        Integer pendingVal = null;
        int from;
        int to;

        int size() {
            return to - from + 1;
        }

    }


    public static void main(String[] args) throws FileNotFoundException {


        Scanner sc = new Scanner(new FileInputStream("testcase.txt"));

        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        CoolGuyAndTwoSubsequences sg = new CoolGuyAndTwoSubsequences(arr);

        int ans = 0;

        int count = 0;
        for (int a = 0; a < n; a++) {
            for (int b = a; b < n; b++) {
                for (int c = b + 1; c < n; c++) {
                    for (int d = c; d < n; d++) {
                        System.out.println("a " + a + " b " + b + " c " + c + " d " + d);
                        //ans = (ans + Math.min(minInterval(sg, a, b), minInterval(sg, c, d))) % 1000000007;
                    }
                }
            }
        }

        System.out.println(ans);
    }

    public static int minInterval(CoolGuyAndTwoSubsequences sg, int a, int b) {
        return sg.RMinQ(a, b);
    }

}