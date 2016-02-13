import java.util.Scanner;

/**
 * Created by Jian on 11/02/2016.
 */
public class Test {
    public static void main(String[] args) {


        Scanner user_input = new Scanner(System.in);

        System.out.println("Enter characters to check for Palindrome: ");


        if (user_input.hasNextInt()) {
            String intToString = String.valueOf(user_input.nextInt()).toString();
            String reversedInt = new StringBuffer(intToString).reverse().toString();

            for (int i = 0; i < intToString.length(); i++) {
                if (intToString.charAt(i) != reversedInt.charAt(i)) {
                    System.out.println("False");
                } else {
                    System.out.println("True");
                }
            }
        } else if (user_input.hasNextLine()) {
            String user_string = user_input.nextLine().replaceAll(" ", "").toLowerCase();
            StringBuilder user_mutable_string = new StringBuilder(user_string);
            user_mutable_string.reverse();

            if (user_string.equals(user_mutable_string.toString())) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        } else {
            System.out.println("Bonkers!");
        }

    }
}
