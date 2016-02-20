package weekofcode19;
//two robot was really more dificult than this. what you need to know is that residues are '' s(vi,vj)%m ''
//so you can see how much residue you got
//

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Jian on 18/02/2016.
 */
public class ScalarProducts {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("testcase.txt"));

        int a1 = sc.nextInt();
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[] sequence = new int[2 * n + 2];

        ArrayList<Point> vectors = new ArrayList();
        sequence[0] = 0;
        sequence[1] = a1;

        for (int i = 2; i < sequence.length; i++) {
            sequence[i] = (sequence[i - 1] + sequence[i - 2]) % m;
        }

        for (int i = 2; i < sequence.length; i = i + 2) {
            Point v = new Point(sequence[i], sequence[i + 1]);
            vectors.add(v);
        }

        HashSet<Long> result = new HashSet<>();
        HashMap<Integer, Long> hm = new HashMap<>();

        for (int i = 0; i < vectors.size(); i++) {
            for (int j = i + 1; j < vectors.size(); j++) {
                int hash = vectors.get(i).hashCode() + vectors.get(j).hashCode();
                if (hm.containsKey(hash)) {
                    result.add(hm.get(hash));
                } else {
                    long r = scalarProduct(vectors.get(i), vectors.get(j), m);
                    //if((m & (m - 1)) == 0){
                    //    r = fastModulo(r,m);
                    //}else {
                    //     r = r % m;
                    //}
                    result.add(r);
                    hm.put(hash, r);
                }
            }
        }
        System.out.println(result.size() % m);

    }


    public static long fastModulo(long dividend, int divisor) {
        return dividend & (divisor - 1);
    }

    public static long scalarProduct(Point p1, Point p2, int m) {
        long A = ((p1.x % m) * (p2.x % m)) % m;
        long B = ((p1.y % m) * (p2.y % m)) % m;
        return ((A % m) + (B % m)) % m;
    }

    public static class Point {
        long x;
        long y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return (x + " " + y);
        }

    }

}

