package algorithm.strings;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * Created by Jian on 28/01/2016.
 */
public class Anagram {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("testcase.txt"));
        int no = sc.nextInt();

        for(int  a = 0 ; a < no; a++) {

            final HashMap<String, Integer> m1 = new HashMap<>();
            String str = sc.next();
            String s1 = str.substring(0, str.length() / 2);
            String s2 = str.substring(str.length() / 2, str.length());

            if(s1.length() != s2.length()) {
                System.out.println(-1);
                continue;
            }

            for(int x = 0; x < s1.length(); x++){
                String key = s1.charAt(x) + "";
                if (m1.containsKey(key)) {
                    int value = m1.get(key);
                    m1.put(key, value + 1);
                }
                else
                    m1.put(key,1);
            }

            // -1 because character occurred.
            for(int x = 0; x < s2.length(); x++){
                String key = s2.charAt(x) + "";
                if (m1.containsKey(key)) {
                    int value = m1.get(key);
                    m1.put(key, value - 1);
                }else
                    m1.put(key,-1);
            }


            int sum = 0;
            for(Integer m : m1.values()){
                sum += Math.abs(m);

            }

            System.out.println(m1);
            //You need to do this because you mapped two strings into 1.
            System.out.println(sum/2);

        }

    }
}
