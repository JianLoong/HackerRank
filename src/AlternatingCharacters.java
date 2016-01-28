/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jian
 */
public class AlternatingCharacters {
    public static void main(String[] args) {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        final Map<String, Integer> m = new HashMap<String, Integer>();
        int i = 0;
        for (char key : alphabet) {
            m.put(key + "", 0);
        }

    }
}
