import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

/**
 * Created by Jian on 2/02/2016.
 */
public class StringFunctionCalculation {

    public static void main(String[] args) {

        String s = "banana";
        int length = s.length();

        int[] sa = suffixArray(s);
        //int[] lcp = lcp(sa, s);



        System.out.print("SA : ");
        for (int i = 0; i < sa.length; i++) {
            System.out.print(sa[i] + " ");
        }

        System.out.print("\nLCP: ");
        for (int i = 0; i < lcp.length; i++) {
            System.out.print(lcp[i] + " ");
        }
        System.out.println();

    }



    /*
    public static int[] lcp(int[] sa, CharSequence s) {
        int n = sa.length;
        int[] rank = new int[n];
        for (int i = 0; i < n; i++)
            rank[sa[i]] = i;
        int[] lcp = new int[n - 1];
        for (int i = 0, h = 0; i < n; i++) {
            if (rank[i] < n - 1) {
                for (int j = sa[rank[i] + 1]; Math.max(i, j) + h < s.length() && s.charAt(i + h) == s.charAt(j + h); ++h)
                    ;
                lcp[rank[i]] = h;
                if (h > 0)
                    --h;
            }
        }
        return lcp;
    }
    */



    public static int[] suffixArray(CharSequence S) {
        int n = S.length();

        int[] sa = IntStream.range(0, n).mapToObj(i -> n - 1 - i).
                sorted((a, b) -> Character.compare(S.charAt(a), S.charAt(b))).mapToInt(Integer::intValue).toArray();
        int[] classes = S.chars().toArray();

        for (int len = 1; len < n; len *= 2) {
            int[] c = classes.clone();
            for (int i = 0; i < n; i++) {
                classes[sa[i]] = i > 0
                        && c[sa[i - 1]] == c[sa[i]]
                        && sa[i - 1] + len < n
                        && c[sa[i - 1] + len / 2] == c[sa[i] + len / 2] ? classes[sa[i - 1]] : i;
            }

            int[] cnt = IntStream.range(0, n).toArray();
            int[] s = sa.clone();
            for (int i = 0; i < n; i++) {
                int s1 = s[i] - len;
                if (s1 >= 0)
                    sa[cnt[classes[s1]]++] = s1;
            }
        }
        return sa;
    }
}
