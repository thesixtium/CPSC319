/**
 * Assign2.java
 * Main code to be used for CPSC 319 assignent 2 that should be run from
 *      command line. To quote the assignment, "The goal of this
 *      assignment is to write a Java program that arranges a list of words
 *      into separate lists of anagrams. The input is a text file that
 *      contains a list of words, each word on its own line. The number of
 *      words in the input is arbitrary and could be very large."
 *
 * @author Aleksander Berezowski
 * @version 2.8
 * @since 1.0
 */

import java.io.*;
import java.util.*;

public class Assign2 {
    // Class variables
    private String inputFileName = "";    // Input file name
    private String outputFileName = "";   // Output file name
    private boolean debuggingMode = true; // To set program to debugging mode
    private String[] inputWordList;       // Word list to initially put file into
    private LinkedList[] linkedLists;     // Array to store all linked lists
    private long startTime;               // Tracks program start time

    /**
     * Method to help with debugging by formatting a print statement, automatically
     *      checks if debugging mode is on or not
     * @param methodName Name of the method to print
     */
    private void printHeader(String methodName) {
        if (debuggingMode) {
            System.out.println("\n\t - - - - " + methodName + " - - - -");
        }
    }

    /**
     * Method to read input file
     */
    private void readFile() {
        //https://www.geeksforgeeks.org/read-file-into-an-array-in-java/

        printHeader("readFile");

        // list that holds strings of a file
        List<String> listOfStrings = new ArrayList<String>();
        // load data from file
        BufferedReader bf;

        try {
            bf = new BufferedReader(new FileReader("src/"+inputFileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(inputFileName + " not found, " + e.getMessage());
        }

        try {
            // read entire line as string
            String line = bf.readLine();

            // checking for end of file
            while (line != null) {
                listOfStrings.add(line);
                line = bf.readLine();
            }
        } catch (Exception e){
            throw new RuntimeException("Error reading file, exception caught " + e.getMessage());
        }

        // closing bufferreader object
        try {
            bf.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing file, exception caught " + e.getMessage());
        }

        // storing the data in arraylist to array
        inputWordList = listOfStrings.toArray(new String[0]);

        // printing each line of file
        if(debuggingMode) {
            System.out.println("\nPrint input file's unsorted contents:");
            int lineNumber = 1;
            for (String str : inputWordList) {
                System.out.println("Line " + String.valueOf(lineNumber) + ": " + str);
                lineNumber++;
            }
        }
    }

    /**
     * Method to check if strings are anagrams of each other
     * @param str1 First String to compare
     * @param str2 Second String to compare
     * @return true is str1 and str2 are anagrams of each other, otherwise false
     */
    private boolean checkAnagram(String str1, String str2) {
        //https://stackoverflow.com/questions/2131997/checking-if-two-strings-are-permutations-of-each-other

        printHeader("checkAnagram");

        if (str1.length() != str2.length())
            return false;

        char[] a = str1.toCharArray();
        char[] b = str2.toCharArray();

        return Arrays.equals(sortCharArray(a), sortCharArray(b));
    }

    /**
     * Method to sort a character array
     * @param arr Character array to sort
     * @return Sorted character array
     */
    private char[] sortCharArray(char[] arr){
        //https://www.geeksforgeeks.org/sort-a-string-in-java-2-different-ways/
        char temp;

        int i = 0;
        while (i < arr.length) {
            int j = i + 1;
            while (j < arr.length) {
                if (arr[j] < arr[i]) {

                    // Comparing the characters one by one
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
                j += 1;
            }
            i += 1;
        }

        return arr;

    }

    /**
     * Method to call QuickSort for "linkedLists" LinkedList array
     */
    private void quickSortLinkedListArray(){
        // Driver function for quick sort algorithm

        printHeader("quickSortLinkedListArray");

        //https://www.youtube.com/watch?v=Hoixgm4-P4M&t=49s
        //https://www.geeksforgeeks.org/quick-sort/
        //https://www.reddit.com/r/learnprogramming/comments/1yzs5z/java_quicksort_giving_stackoverflowerror_for/
        //https://www.geeksforgeeks.org/iterative-quick-sort/
        quickSortLinkedListArrayAlg(linkedLists, 0, linkedLists.length - 1);

        // printing each line of file
        if(debuggingMode) {
            System.out.println("\nPrint input file's sorted contents:");
            int lineNumber = 1;
            for (LinkedList list : linkedLists) {
                System.out.println("Line " + String.valueOf(lineNumber) + ": " + list.getHead().getData());
                lineNumber++;
            }
        }
    }

    /**
     * Method to QuickSort "linkedLists" LinkedList array
     * @param arr LinkedList array to quickSort
     * @param l lowest array index
     * @param h highest array index
     */
    private void quickSortLinkedListArrayAlg(LinkedList[] arr, int l, int h) {
        printHeader("quickSortLinkedListArrayAlg");

        // Quick sort algorithm
        int[] stack = new int[h - l + 1];
        int top = -1;
        stack[++top] = l;
        stack[++top] = h;

        while (top >= 0) {
            h = stack[top--];
            l = stack[top--];
            int p = partitionLinkedListArray(arr, l, h);

            if (p - 1 > l) {
                stack[++top] = l;
                stack[++top] = p - 1;
            }

            if (p + 1 < h) {
                stack[++top] = p + 1;
                stack[++top] = h;
            }
        }
    }

    /**
     * Helper Method for "quickSortLinkedListArrayAlg"
     * @param arr array to select partition
     * @param low lowest array index
     * @param high highest array index
     * @return Int of where to partition
     */
    private int partitionLinkedListArray(LinkedList[] arr, int low, int high) {
        // Partition function for the quick sort algorithm

        printHeader("partitionLinkedListArray");

        LinkedList pivot = arr[high];

        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            if (arr[j].getHead().getData().compareTo(pivot.getHead().getData()) < 0) {
                i++;

                Node temp = arr[i].getHead();
                arr[i].setHead(arr[j].getHead());
                arr[j].setHead(temp);
            }
        }

        Node temp = arr[i + 1].getHead();
        arr[i + 1].setHead(arr[high].getHead());
        arr[high].setHead(temp);

        return i + 1;
    }

    /**
     * Method to populate "linkedLists" LinkedList array
     */
    private void createLinkedListArray(){
        printHeader("createLinkedListArray");

        linkedLists = new LinkedList[inputWordList.length];

        linkedLists[0] = new LinkedList(inputWordList[0]);

        if(debuggingMode){
            System.out.println("List after first position populated");
            printLinkedLists();
        }

        for(int j = 1; j < inputWordList.length; j++){
            for(int i = 0; i < linkedLists.length; i++) {
                if (linkedLists[i] != null) {
                    if (checkAnagram(inputWordList[j], linkedLists[i].getHead().getData())) {
                        linkedLists[i].insert(inputWordList[j]);
                        if(debuggingMode){
                            System.out.println(inputWordList[j] + " is an anagram of "
                                    + linkedLists[i].getHead().getData() + ", inserting into list" );
                        }
                        break;
                    } else if (debuggingMode){
                        System.out.println(inputWordList[j] + " is not an anagram of "
                                + linkedLists[i].getHead().getData());
                    }
                } else {
                    linkedLists[i] = new LinkedList(inputWordList[j]);
                    if(debuggingMode){
                        System.out.println("Didn't find list with " + inputWordList[j] +", making new list");
                    }
                    break;
                }
            }
        }

        if(debuggingMode){
            System.out.println("List before insertion sort");
            printLinkedLists();
        }

        for (LinkedList linkedList : linkedLists) {
            if (linkedList != null) {
                linkedList.setHead(linkedList.insertionSort());
            }
        }

        if(debuggingMode){
            System.out.println("List after insertion sort");
            printLinkedLists();
        }


    }

    /**
     * Debugging method to print all Linked Lists in "linkedLists" array
     */
    private void printLinkedLists() {
        for (LinkedList s : linkedLists) {
            if (s != null) {
                if (s.getHead() != null) {
                    s.printList();
                } else {
                    System.out.println("null");
                }
            } else {
                System.out.println("nullptr");
            }
        }
    }

    /**
     * Method to shrink "linkedLists" array of all null objects
     */
    private void shrinkLinkedListArray(){
        printHeader("shrinkLinkedListArray");
        //https://stackoverflow.com/questions/4150233/remove-null-value-from-string-array-in-java

        if(debuggingMode){
            System.out.println("List with nulls");
            printLinkedLists();
        }

        ArrayList<LinkedList> list = new ArrayList<LinkedList>();
        for (LinkedList s : linkedLists)
            if (s != null)
                list.add(s);
        linkedLists = list.toArray(new LinkedList[list.size()]);


        if(debuggingMode){
            System.out.println("List without nulls");
            printLinkedLists();
        }
    }

    // Method to creates the output file
    /**
     * Method creates the output file
     */
    private void printFile(){
        printHeader("printFile");

        //https://www.w3schools.com/java/java_files_create.asp
        try {
            PrintWriter myFile = new PrintWriter(outputFileName + ".txt", "UTF-8");
            for (LinkedList list : linkedLists) {
                if(debuggingMode){
                    System.out.println("Printing to file: " + list.toString());
                }
                myFile.println(list.toString());
            }
            myFile.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Method logs the start of the program
     */
    public void startOfProgram(){
        startTime = System.nanoTime();
    }

    /**
     * Method prints out how long the program took to run in nanoseconds
     */
    public void endOfProgram(){
        System.out.println(System.nanoTime() - startTime);
    }

    /**
     * Main method for Assign2 class
     * @param args Array of string arguments: (required) input file name, (required) output
     *                  file name, (optional) debugging mode.
     */
    public Assign2(String[] args) {
        startOfProgram();

        // Check args
        if(args.length < 2){
            throw new IllegalArgumentException("Wrong number of input arguments: too few arguments");
        } else if(args.length > 3){
            throw new IllegalArgumentException("Wrong number of input arguments: too many arguments");
        }

        if(args.length == 3){
            try{
                Integer.parseInt(args[2]);
            } catch (NumberFormatException e){
                throw new IllegalArgumentException("Debugging mode argument is not a number");
            }

            if (Integer.parseInt(args[2]) == 0){
                debuggingMode = false;
            } else if (Integer.parseInt(args[2]) != 1){
                throw new IllegalArgumentException("Debugging mode argument is not 0 or 1");
            }
        } else {
            debuggingMode = false;
        }

        inputFileName = args[0] + ".txt";
        outputFileName = args[1];

        readFile();

        createLinkedListArray();
        shrinkLinkedListArray();
        quickSortLinkedListArray();

        printFile();

        endOfProgram();
    }

    public static void main(String[] args){
        new Assign2(args);
    }
}
