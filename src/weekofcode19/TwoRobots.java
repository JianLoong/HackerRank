package weekofcode19;

import sun.security.rsa.RSASignature;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Jian on 17/02/2016.
 */
public class TwoRobots {


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


            int[] moves = new int[queries];

            int distance = 0;

            int r1Pos = 0;
            int r2Pos = 0;

            distance = Math.abs(mA[0] - mB[0]);
            r1Pos = mB[0];
            moves[0] = 1;

            //Pick R2 for sure
            if (queries == 2) {
                moves[1] = 2;
            }


            //Robot 2 hasnt start moving
            for (int i = 1; i < queries; i++) {
                if (i < queries - 1) {
                    // We already move R1 now we decide if we should move R2.
                    HashMap<String, Integer> hm = new HashMap<>();
                    int queryDistance = Math.abs(mA[i] - mB[i]);

                    int r1r1 = 0;
                    int r1r2 = 0;
                    int r2r1 = 0;
                    int r2r2 = 0;

                    //Sequence R1 + R1 and R1 + R2
                    //Bring r1 to start pos and add query distance
                    int r1 = Math.abs(r1Pos - mA[i]) + queryDistance;
                    r1r1 = r1 + Math.abs(mA[i] - mA[i + 1]);
                    r1r2 = 0;

                    if (r2Pos != 0)
                        r1r2 = r1 + Math.abs(r2Pos - mA[i + 1]);
                    else
                        r1r2 = r1 + 0;


                    //Sequence R2 + R1 and R2 + R2
                    int r2 = 0;
                    if (r2Pos != 0) {
                        r2 = Math.abs(r2Pos - mA[i]) + queryDistance;
                    }

                    r2r1 = r2 + Math.abs(r1Pos - mA[i + 1]);
                    r2r2 = r2 + Math.abs(mA[i] - mA[i + 1]);


                    System.out.println("R1R1 : " + r1r1);
                    System.out.println("R1R2 : " + r1r2);
                    System.out.println("R2R1 : " + r2r1);
                    System.out.println("R2R2 : " + r2r2);

                    int[] min = {r1r1, r1r2, r2r1, r2r2};
                    Arrays.sort(min);

                    System.out.println("Min Array: " + Arrays.toString(min));



                    if (r1r1 == min[0]) {
                        moves[i] = 1;
                        moves[i + 1] = 1;
                        r1Pos = mB[i];
                    }
                    if (r1r2 == min[0]) {
                        moves[i] = 1;
                        moves[i + 1] = 2;
                        r1Pos = mB[i];
                    }
                    if (r2r1 == min[0]) {
                        moves[i] = 2;
                        moves[i + 1] = 1;
                        r2Pos = mB[i];
                    }
                    if (r2r2 == min[0]) {
                        moves[i] = 2;
                        moves[i + 1] = 2;
                        r2Pos = mB[i];
                    }
                }
            }

            System.out.println("Moves is " + Arrays.toString(moves));
            System.out.println(distanceSoFar(mA, mB, moves));
        }

    }


    public static int distanceSoFar(int[] mA, int[] mB, int[] moves) {
        int distance = 0;
        int r1Pos = 0;
        int r2Pos = 0;

        for (int i = 0; i < moves.length; i++) {
            if (moves[i] == '3') {
                return distance;
            }
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
        return distance;
    }

}
