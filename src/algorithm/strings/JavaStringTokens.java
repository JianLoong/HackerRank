import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine().trim();
        if (s.length() != 0) {
            String reg = "\\s*[^a-zA-Z]+\\s*";
            String[] sTokens = s.split(reg);

            System.out.println(sTokens.length);
            for (String token :
                    sTokens) {
                System.out.println(token);
            }
        }else {
            System.out.println(0);
        }
        scan.close();
    }
}
