import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by Jian on 28/01/2016.
 */
public class ACMICPCTeam {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("testcase.txt"));

        int people = sc.nextInt();
        int topics = sc.nextInt();

        String a[] = new String[people];
        for (int i = 0; i < people; i++)
            a[i] = sc.next();



    }
}
