import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * http://stackoverflow.com/questions/18318780/minimum-steps-to-win-snake-ladder
 * Created by Jian on 29/01/2016.
 */
public class SnakeAndLadders {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("testcase.txt"));

        int noOfTestCases = sc.nextInt();

        for (int y = 0; y < noOfTestCases; y++) {

            HashMap<Integer, Integer> jumps = new HashMap<>();
            int noOfLadders = sc.nextInt();

            for (int i = 0; i < noOfLadders; i++) {
                Integer intialPos = sc.nextInt();
                Integer endPos = sc.nextInt();
                jumps.put(intialPos, endPos);
            }

            int noOfSnakes = sc.nextInt();

            for (int i = 0; i < noOfSnakes; i++) {
                Integer intialPos = sc.nextInt();
                Integer endPos = sc.nextInt();
                jumps.put(intialPos, endPos);
            }

            Integer finalPos = 100;
            Integer intialPos = 1;
            HashSet<Integer> positions = new HashSet<>();
            positions.add(intialPos);

            int steps = 0;
            int iterations = 10000000;
            boolean flag = false;

            outer_while:
            while (!positions.contains(finalPos)) {
                steps += 1;
                HashSet<Integer> oldPositions = new HashSet<Integer>(positions);
                positions.clear();
                for (Integer i : oldPositions) {
                    for (int dice = 1; dice < 7; dice++) {
                        Integer newPosition = i + dice;
                        if (jumps.containsKey(newPosition)) {
                            positions.add(jumps.get(newPosition));
                        }
                        else
                            positions.add(newPosition);
                        iterations--;
                        if(iterations == 0) {
                            flag = true;
                            break outer_while;
                        }
                    }
                }

            }

            //No solution found within that much iterations -_-
            if(flag == true)
                System.out.println(-1);
            else
                System.out.println(steps);
        }

    }
}
