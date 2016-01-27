import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author Jian
 */
public class FibonacciModified {

    public static int count = 0;
    public static BigInteger term = new BigInteger("0");

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("testcase.txt"));

        int a = sc.nextInt();
        int b = sc.nextInt();
        int n = sc.nextInt();

        BigInteger a1 = BigInteger.valueOf(a);
        BigInteger b1 = BigInteger.valueOf(b);
        fibonacci(a1, b1, (n-2));
        System.out.println(term);
    }

    public static void fibonacci(BigInteger a, BigInteger b, int n) {
        if (count == n) 
            return;
        BigInteger rs = (b.pow(2)).add(a);
        term = rs;
        count++; 
        fibonacci(b, term, n);
    }
}
