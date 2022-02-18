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
 * This class creates and sorts arrays via different algorithms and returns the run time in nanoseconds
 *
 * This method may take some time to complete due to array size, and will throw errors if something
 * goes wrong
 *
 * @param  order            (String)   Specifies the order of the Array
 * @param  size             (int)      Specifies how large the sorted array will be
 * @param  algorithm        (String)   Specifies the sorting algorithm used
 * @param  outputfile       (String)   Specifies the output file name
 * @param  runs             (int)      Specifies how many times the sorting algorithm is run
 * @param  printofile       (boolean)  Specifies if the output array(s) are printed to a file
 * @param  printrunresults  (boolean)  Specifies if the output array(s) are printed to the terminal
 * @return                             Can create one or more ".txt" files dependant on input parameters
 *
 * More information on the arguments for this class are as follows:
 *      Argument 1: Array Order - Required
 *          Specifies the order of the Array
 *          Acceptable arguments:
 *              ascending
 *              descending
 *              random
 *          If not an acceptable argument, will throw an IllegalArgument exception
 *          If not provided, will throw an IllegalArgument exception
 *      Argument 2: Array Size - Required
 *          Specifies how large the sorted array will be
 *          Can be any int
 *          If not provided, will throw an IllegalArgument exception
 *      Argument 3: Sorting Algorithm - Required
 *          Specifies the sorting algorithm used
 *          Acceptable arguments:
 *              merge
 *              selection
 *              insertion
 *              quick
 *          If not an acceptable argument, will throw an IllegalArgument exception
 *          If not provided, will throw an IllegalArgument exception
 *      Argument 4: Output File Name - Required
 *          Specifies the output file name
 *          Needs to be a string
 *          If not provided, will throw an IllegalArgument exception
 *      Argument 5: Program Runs - Optional
 *          Specifies how many times the sorting algorithm is run
 *          If not provided, default to 1
 *          Can be any int
 *      Argument 6: Print Array To File - Optional
 *          Specifies if the output array(s) are printed to a file
 *          If not provided, default to true
 *          Can be true or false
 *      Argument 7: Print Results Of Runs - Optional
 *          Specifies if the output array(s) are printed to the terminal
 *          If not provided, default to false
 *          Can be true or false
 *
 *
 */

// Imports
import java.util.*;
import java.io.*;

public class assign1 {
    // All global variables needed
    private String order = "";
    private int size = 0;
    private String algorithm = "";
    private String outputFile = "";
    private int[] myArray = new int [1];
    private long startTime = 0;
    private long endTime = 0;
    private int runs = 0;
    private long[] resultArray = new long [1];
    private String outputName = "";
    private boolean printArray = false;
    private boolean printResults = false;

    public void createTheArray(String order, int size) {
        // Creates the array that will be sorted

        // Scope variables
        myArray = new int[size];
        int Min = 0;
        int Max = 10000000;

        // Decide array order
        switch (order) {
            case "random":
                for (int i = 0; i < size; i++) {
                    myArray[i] = Min + (int) (Math.random() * ((Max - Min) + 1));
                    // https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
                }
                break;
            case "ascending":
                for (int i = 0; i < size; i++) {
                    myArray[i] = i;
                }
                break;
            case "descending":
                for (int i = 0; i < size; i++) {
                    myArray[i] = size - i;
                }
                break;
            default:
                throw new IllegalArgumentException("Array order argument is incorrect");
        }

    }

    public void arrayToFile(String name){
        // Creates the output array file
        //https://www.w3schools.com/java/java_files_create.asp
        try {
            PrintWriter myFile = new PrintWriter(name + ".txt", "UTF-8");
            for (int j : myArray) {
                myFile.println(j);
            }
            myFile.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void selectionSort(){
        // Selection sort algorithm

        //https://www.youtube.com/watch?v=g-PGLbMth_g
        //https://www.geeksforgeeks.org/selection-sort/
        int n = myArray.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (myArray[j] < myArray[min_idx])
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            int temp = myArray[min_idx];
            myArray[min_idx] = myArray[i];
            myArray[i] = temp;
        }
    }

    public void insertionSort(){
        // Insertion sort algorithm

        //https://www.youtube.com/watch?v=JU767SDMDvA
        //https://www.geeksforgeeks.org/insertion-sort/
        int n = myArray.length;
        for (int i = 1; i < n; ++i) {
            int key = myArray[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && myArray[j] > key) {
                myArray[j + 1] = myArray[j];
                j = j - 1;
            }
            myArray[j + 1] = key;
        }
    }

    public int[] merge(int[] arrayOne, int[] arrayTwo) {
        // Merge arrays, used for merge sort algorithm
        int[] returnArray = new int[arrayOne.length + arrayTwo.length];
        int arrayOneIndex = 0;
        int arrayTwoIndex = 0;

        for (int i = 0; i < returnArray.length; i++) {
            if (arrayOneIndex < arrayOne.length && arrayTwoIndex < arrayTwo.length) {
                if (arrayOne[arrayOneIndex] > arrayTwo[arrayTwoIndex]) {
                    returnArray[i] = arrayTwo[arrayTwoIndex];
                    arrayTwoIndex++;
                } else {
                    returnArray[i] = arrayOne[arrayOneIndex];
                    arrayOneIndex++;
                }
            } else if (arrayOneIndex < arrayOne.length) {
                returnArray[i] = arrayOne[arrayOneIndex];
                arrayOneIndex++;
            } else {
                returnArray[i] = arrayTwo[arrayTwoIndex];
                arrayTwoIndex++;
            }
        }
        return returnArray;
    }

    public int[] mergeSortAlg(int[] sortArray){
        // Merge sort algorithm

        //https://www.youtube.com/watch?v=4VqmGXwpLqc
        if(sortArray.length == 1){
            return sortArray;
        }

        int [] arrayOne = Arrays.copyOfRange(sortArray, 0, sortArray.length/2);
        int [] arrayTwo = Arrays.copyOfRange(sortArray, sortArray.length/2, sortArray.length);

        arrayOne = mergeSortAlg(arrayOne);
        arrayTwo = mergeSortAlg(arrayTwo);

        return merge(arrayOne, arrayTwo);

    }

    public void mergeSort(){
        // Driver function for merge sort function

        myArray = mergeSortAlg(myArray);
    }

    public void quickSort(){
        // Driver function for quick sort algorithm

        //https://www.youtube.com/watch?v=Hoixgm4-P4M&t=49s
        //https://www.geeksforgeeks.org/quick-sort/
        //https://www.reddit.com/r/learnprogramming/comments/1yzs5z/java_quicksort_giving_stackoverflowerror_for/
        //https://www.geeksforgeeks.org/iterative-quick-sort/
        quickSortAlg(myArray, 0, myArray.length - 1);
    }

    public void quickSortAlg(int[] arr, int l, int h)
    {
        // Quick sort algorithm

        int[] stack = new int[h - l + 1];
        int top = -1;
        stack[++top] = l;
        stack[++top] = h;

        while (top >= 0) {
            h = stack[top--];
            l = stack[top--];
            int p = partition(arr, l, h);

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

    static int partition(int[] arr, int low, int high)
    {
        // Partition function for the quick sort algorithm

        int pivot = arr[high];

        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            if (arr[j] <= pivot) {
                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public void startOfProgram(){
        // Log start of program
        startTime = System.nanoTime();
    }

    public long endOfProgram(){
        // Log end of program
        endTime = System.nanoTime();
        return endTime - startTime;
    }

    public void returnRunData(String order, int size, String algorithm){
        // Return the run data in a new file for analysis later
        try {
            PrintWriter myFile = new PrintWriter(outputName + "_resultData.txt", "UTF-8");
            for (long j : resultArray) {
                myFile.println(j);
            }
            myFile.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public assign1(String[] args){
        // Pseudo-main function

        // Accept and deal with input arguments
        try {
            order = args[0];
            size = Integer.parseInt(args[1]);
            algorithm = args[2];
            outputFile = args[3];

            if (args.length > 5) {
                runs = Integer.parseInt(args[4]);
            } else {
                runs = 1;
            }

            if (args.length > 6) {
                printArray = Boolean.parseBoolean(args[5]);
            } else {
                printArray = true;
            }

            if (args.length > 7) {
                printResults = Boolean.parseBoolean(args[5]);
            } else {
                printResults = false;
            }

            if (args.length > 8) {
                throw new IllegalArgumentException("Too many input arguments");
            }
        } catch(Exception e) {
            throw new IllegalArgumentException("Input arguments are of incorrect type");
        }
        resultArray = new long [runs];
        outputName = algorithm + "_" + order + "_" + size;

        // Run the algorithms the specified amount
        for(int i = 0; i < runs; i++){
            startOfProgram();
            createTheArray(order, size);
            System.out.println("On: " + order + " " + algorithm + " - " + String.valueOf(i) + "/" + String.valueOf(size));
            if(printArray){
                arrayToFile(outputFile + outputName + "Unsorted" + String.valueOf(i) );
            }
            switch (algorithm) {
                case "selection":
                    selectionSort();
                    break;
                case "insertion":
                    insertionSort();
                    break;
                case "quick":
                    quickSort();
                    break;
                case "merge":
                    mergeSort();
                    break;
                default:
                    throw new IllegalArgumentException("Algorithm order argument is incorrect");
            }

            if(printArray){
                arrayToFile(outputFile + outputName + "Sorted" + String.valueOf(i));
            }
            resultArray[i] = endOfProgram();
        }
        if(printResults) {
            returnRunData(order, size, algorithm);
        } else {
            for (long j : resultArray) {
                System.out.println(j);
            }
        }
    }
}
