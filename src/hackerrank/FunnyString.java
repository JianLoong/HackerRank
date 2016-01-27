/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hackerrank;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Jian
 */
public class FunnyString {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("testcase.txt"));
        int noOfTestCases = sc.nextInt();

        for (int i = 0; i < noOfTestCases; i++) {
            if (funnyOrNot(sc.next())) {
                System.out.println("Funny");
            } else {
                System.out.println("Not Funny");
            }
        }

    }

    public static boolean funnyOrNot(String str) {
        //Reverse string
        String reverse = new StringBuilder(str).reverse().toString();
        //System.out.println(str.charAt(1) - str.charAt(0));
        ArrayList<Integer> strValues = new ArrayList<>();
        ArrayList<Integer> revValues = new ArrayList<>();
        for (int i = 1; i < str.length(); i++) {
            strValues.add((Math.abs(str.charAt(i) - str.charAt(i - 1))));
        }

        for (int i = 1; i < reverse.length(); i++) {
            revValues.add(Math.abs(reverse.charAt(i) - reverse.charAt(i - 1)));
        }

        for (int i = 0; i < str.length() - 1; i++) {
//            System.out.print(strValues.get(i) + " ");
//            System.out.println(revValues.get(i));
            if (!strValues.get(i).equals(revValues.get(i))) {
                return false;               
            }
        }

        return true;

    }

}
