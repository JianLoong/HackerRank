package weekofcode19;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

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

        HashSet<Long> result = new HashSet<>();
        HashMap<Long, Long> hm = new HashMap<>();


        for (int i = 0; i < ve.length; i++) {
            for (int j = i + 1; j < ve.length; j++) {
                //long r = scalarProduct(ve[i][0], ve[i][1], ve[j][0], ve[j][1], m);
                //result.add(r);
                long product = scalarProduct(ve[i][0], ve[i][1], ve[j][0], ve[j][1]);
                if(!hm.containsKey(product)) {
                    long r = scalarProduct(ve[i][0], ve[i][1], ve[j][0], ve[j][1], m);
                    hm.put(product, r);
                    result.add(r);
                }
            }
        }

        System.out.println(result.size() % m);

    }

    public static long scalarProduct(long p1x, long p1y, long p2x, long p2y) {
        return p1x * p2x + p1y * p2y;
    }

    public static long scalarProduct(long p1x, long p1y, long p2x, long p2y, int m) {
        long A = ((p1x % m) * (p2x % m)) % m;
        long B = ((p1y % m) * (p2y % m)) % m;
        return ((A % m) + (B % m)) % m;
    }

}

