/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;

public class UtopianTree {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numCases = in.nextInt();
        for (int i = 0; i < numCases; i++) {
            int n = in.nextInt();
            int answer = ((int) Math.pow(2, (n + 3) / 2)) + (((int) Math.pow(-1, n)) - 3) / 2;
            System.out.println(answer);
        }
    }
}