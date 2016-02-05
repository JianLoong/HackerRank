import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * Created by Jian on 3/02/2016.
 */
public class IceCreamParlor {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("testcase.txt"));
        int noOfTestCases = sc.nextInt();

        for (int i = 0; i < noOfTestCases; i++) {
            int money = sc.nextInt();
            int noOfFlavors = sc.nextInt();
            int[] prices = new int[noOfFlavors];
            for (int a = 0; a < noOfFlavors; a++) {
                prices[a] = sc.nextInt();
            }
            solve(prices, money);
        }
    }

    public static void solve(int[] prices, int money) {
        Set set = new HashSet<>(prices.length);
        HashMap<Integer, Integer> hm = new HashMap<>();
        int count = 0;
        for (int i : prices) {
            int target = money - i;
            count++;
            if (!set.contains(target)) {
                hm.put(i, count);
                set.add(i);
            } else {
                System.out.println(hm.get(target) + " " + count);
                return;
            }
        }
    }

    /**
     * Binary search doesnt seem to work due to duplicate values
     * @param prices
     * @param money
     * @param mp
     */
//    public static void solve(int[] prices , int money, Map mp){
//        int length = prices.length;
//        if(length < 2)
//            return;
//
//        int left = 0;
//        int right = length - 1;
//
//        while( left < right){
//            int sum = prices[left] + prices[right];
//            if (sum == money){
//                System.out.println(mp.get(prices[left]) + " " + mp.get(prices[right]));
//                left = left + 1;
//                right = right - 1;
//            }else if ( sum <  money){
//                left = left + 1;
//            }else if ( sum > money) {
//                right = right - 1;
//            }
//        }
//    }
}
