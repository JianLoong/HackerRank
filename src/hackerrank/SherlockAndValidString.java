/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hackerrank;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Jian
 */
public class SherlockAndValidString {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("testcase.txt"));

        //HashSet<String,Integer> hs = new HashSet();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        String s = sc.next();
        s = s.replaceAll("\\s+", "");
        s = s.toLowerCase();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Integer val = map.get(new Character(c));
            if (val != null) {
                map.put(c, new Integer(val + 1));
            } else {
                map.put(c, 1);
            }
        }
        System.out.println(map);
        printMap(map);

        //System.out.println(map);
    }

    public static void printMap(Map mp) {

        int frequencies[] = new int[mp.size()];
        Iterator it = mp.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            frequencies[i] = (int) pair.getValue();
            i++;
            //System.out.println(pair.getKey() + " = " + pair.getValue());
            //if((int)pair.getValue() % 2 != 0){
            //    //System.out.println("Unique");
            //    count++;
            //}
            //it.remove(); // avoids a ConcurrentModificationException
        }
        
        System.out.println(checkFrequencies(frequencies));

    }

    public static boolean checkFrequencies(int[] array) {
        boolean flag = true;
        int first = array[0];
        for (int i = 1; i < array.length && flag; i++) {
            if (array[i] != first) {
                flag = false;
            }
        }
    
        if(flag) 
            return true;
        return false;
    }
    
}
