/**
 * @author Aleksander Berezowski
 * @version 11.3
 * @since 1.0
 * @class CPSC 319
 * @assignment Assignment 1
 * @tutorial Section: T03
 * @TAName Shopon
 * @email aleksander.berezowsk@ucalgary.ca
 *
 * This class is the driver code for assign1.java
 * @return  void
 *
 */

import java.util.*;
import java.io.*;
import static java.lang.String.valueOf;

public class main {
    private static String[] arrayOrder = {"ascending", "descending", "random"};
    private static String[] algs = {"quick", "selection", "insertion", "merge"};
    private static int[] size = {10, 100, 1000, 10000};

    public static void main(String[] args){
        for (String i : arrayOrder) {
            for (String j : algs) {
                for (int k : size) {
                    String[] arguments = {i, String.valueOf(k), j, "CPSCAssignment1Test9", String.valueOf(100), "false", "true"};
                    System.out.println(arguments);
                    var run = new assign1(arguments);
                    run = null;
                }
            }
        }

        for (String i : arrayOrder) {
            for (String j : algs) {
                String[] arguments = {i, String.valueOf(100000), j, "CPSCAssignment1Test9", String.valueOf(20), "false", "true"};
                System.out.println(arguments);
                var run = new assign1(arguments);
                run = null;
            }
        }

        for (String i : arrayOrder) {
            for (String j : algs) {
                String[] arguments = {i, String.valueOf(1000000), j, "CPSCAssignment1Test9", String.valueOf(5), "false", "true"};
                System.out.println(arguments);
                var run = new assign1(arguments);
                    run = null;
            }
        }

    }

}
