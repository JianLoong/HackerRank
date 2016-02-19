package weekofcode19;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Jian on 17/02/2016.
 *
 * Current solution is ((2 ^ N)/2) * N/2
 */
public class TwoRobotsBF {

    static LinkedHashMap<String, Map<String, Integer>> hm = new LinkedHashMap<>();
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("testcase.txt"));

        int t = sc.nextInt();
        for (int k = 0; k < t; k++) {

            hm.clear();
            LinkedHashMap<String, String> map = new LinkedHashMap<>();
            int noOfContainers = sc.nextInt();
            int queries = sc.nextInt();
            int[] mA = new int[queries];
            int[] mB = new int[queries];

            for (int i = 0; i < queries; i++) {
                mA[i] = sc.nextInt();
                mB[i] = sc.nextInt();
            }
            int value = queries;
            int min = Integer.MAX_VALUE;

            for (int i = 0; i < (Math.pow(2, value)/2) ; i++) {
                StringBuilder binary = new StringBuilder(Integer.toBinaryString(i));
                for (int j = binary.length(); j < value; j++) {
                    binary.insert(0, '0');
                }

                int distance = distanceSoFar(mA, mB, binary.toString().toCharArray(), min);

                map.put(distance + "", binary.toString());
                min = Math.min(min, distance);
            }

            System.out.println("Min is " + min + " : " + map.get(min + ""));
            System.out.println(min);
        }
    }


    public static int distanceSoFar(int[] mA, int[] mB, char[] moves, int min) {
        int distance = 0;
        int r1Pos = 0;
        int r2Pos = 0;
        String s = String.valueOf(moves);

        for (int i = 0; i < moves.length; i++) {
            String str = s.substring(0, i + 1);

            while (hm.containsKey(str)) {
                Map<String, Integer> m = hm.get(str);
                i = m.get("Moves") + 1;
                distance = m.get("Distance");
                r1Pos = m.get("R1");
                r2Pos = m.get("R2");
                str = s.substring(0,i + 1);
            }

            if (i < moves.length) {
                if (moves[i] == '0') {
                    if (r1Pos != 0)
                        distance += Math.abs(r1Pos - mA[i]);
                    distance += Math.abs(mA[i] - mB[i]);
                    r1Pos = mB[i];
                }
                if (moves[i] == '1') {
                    if (r2Pos != 0) {
                        distance += Math.abs(r2Pos - mA[i]);
                    }
                    distance += Math.abs(mA[i] - mB[i]);
                    r2Pos = mB[i];
                }
                Map map = new HashMap<String, Integer>();
                map.put("R1", r1Pos);
                map.put("R2", r2Pos);
                map.put("Distance", distance);
                map.put("Moves", i);
                hm.put(s.substring(0, i + 1), map);

                if(distance > min)
                    return min;
            }
        }
        return distance;
    }
}
