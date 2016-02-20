package weekofcode19;
//two robot was really more dificult than this. what you need to know is that residues are '' s(vi,vj)%m ''
//so you can see how much residue you got
//

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
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

        if (n == 1) {
            System.out.println(0);
            return;
        }

        int length = 2 * n + 2;
        int[] sequence = new int[length];

        sequence[0] = 0;
        sequence[1] = a1;

        for (int i = 2; i < length; i++) {
            sequence[i] = ((sequence[i - 1] % m) + (sequence[i - 2]) % m) % m;
        }

        int[][] ve = new int[(length / 2) - 1][2];
        int count = 0;
        for (int i = 2; i < length; i = i + 2) {
            ve[count][0] = sequence[i];
            ve[count][1] = sequence[i + 1];
            count++;
        }

        //System.out.println(Arrays.deepToString(ve));

        HashSet<Long> result = new HashSet<>();

        for (int i = 0; i < ve.length; i++) {
            for (int j = i + 1; j < ve.length; j++) {
                //int hash = new Point(ve[i][0], ve[i][1]).hashCode() +  new Point(ve[j][0], ve[j][1]).hashCode();
                //if (!result.contains(hash)) {
                    long r = scalarProduct(ve[i][0], ve[i][1], ve[j][0], ve[j][1], m);
                    result.add(r);
                //}
            }
        }

        System.out.println(result.size() % m);

    }

    public static long scalarProduct(Point p1, Point p2, int m) {
        long A = ((p1.x % m) * (p2.x % m)) % m;
        long B = ((p1.y % m) * (p2.y % m)) % m;
        return ((A % m) + (B % m)) % m;
    }

    public static long scalarProduct(long p1x, long p1y, long p2x, long p2y, int m) {
        long A = ((p1x % m) * (p2x % m)) % m;
        long B = ((p1y % m) * (p2y % m)) % m;
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

