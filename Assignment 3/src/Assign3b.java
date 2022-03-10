/**
 * Assign3b.java
 * Main code to be used for CPSC 319 assignent 3b. Everything is the same
 *          except that the data structure is now an AVL tree.
 *
 *
 * @author Aleksander Berezowski
 * @version 1.2
 * @since 1.0
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Assign3b {
    private String inputFileName, outputFileName1, outputFileName2, output1, output2;
    private boolean debuggingMode = true;
    String [] inputStudentList;
    private AVLTree studentDatabase = new AVLTree();

    /**
     * Method to check and validate incoming arguments
     */
    private void checkArguments(String[] args){
        if(args.length < 3 || args.length > 4){
            throw new IllegalArgumentException("Wrong number of input arguments: Received "
                    + String.valueOf(args.length) + "but expected 3 or 4");
        }

        if(args.length == 4){
            try{
                Integer.parseInt(args[3]);
            } catch (NumberFormatException e){
                throw new IllegalArgumentException("Debugging mode argument is not a number");
            }

            if (Integer.parseInt(args[3]) == 0){
                debuggingMode = false;
            } else if (Integer.parseInt(args[3]) != 1){
                throw new IllegalArgumentException("Debugging mode argument is not 0 or 1");
            }
        } else { debuggingMode = false; }

        inputFileName = "src/" + args[0] + ".txt";
        outputFileName1 = args[1] + ".txt";
        outputFileName2 = args[2] + ".txt";
    }

    /**
     * Method to read input file
     */
    private void readFile() {
        //https://www.geeksforgeeks.org/read-file-into-an-array-in-java/

        // list that holds strings of a file
        List<String> listOfStrings = new ArrayList<String>();
        // load data from file
        BufferedReader bf;

        try {
            bf = new BufferedReader(new FileReader(inputFileName));
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
        inputStudentList = listOfStrings.toArray(new String[0]);

        // printing each line of file
        if(debuggingMode) {
            System.out.println("\nPrint input file's unsorted contents:");
            int lineNumber = 1;
            for (String str : inputStudentList) {
                System.out.println("Line " + String.valueOf(lineNumber) + ": " + str);
                lineNumber++;
            }
        }
    }

    /**
     * Method to build the initial binary tree
     */
    private void buildBinaryTree(){
        int lineNumber = 1;
        if(debuggingMode) {
            System.out.println("\nPrint each binary tree command:");
        }

        for (String student: inputStudentList) {
            String opCode = student.substring(0, 1);
            String studentNumber = student.substring(1, 8);
            String studentLastName = student.substring(8, 33);
            String homeDepartment = student.substring(33, 37);
            String program = student.substring(37, 41);
            String year = student.substring(41, 42);

            // printing each command
            if(debuggingMode) {
                StringBuilder printString = new StringBuilder();
                printString.append(lineNumber).append(": ")
                        .append("\tO: ").append(opCode)
                        .append("\tN: ").append(studentNumber)
                        .append("\tL: ").append(studentLastName)
                        .append("\tH: ").append(homeDepartment)
                        .append("\tP: ").append(program)
                        .append("\tY: ").append(year);
                System.out.println(printString);
                lineNumber++;
            }

            if(opCode.equals("I")){
                studentDatabase.insert(new Data(studentNumber,
                        studentLastName, homeDepartment, program, year ));
            } else {
                studentDatabase.delete(new Data(studentNumber,
                        studentLastName, homeDepartment, program, year ));
            }
        }
    }

    /**
     * Debugging method to print function header
     * @param functionName String function's name
     */
    private void printHeader(String functionName){
        if(debuggingMode){
            System.out.println();
            System.out.println("\t-\t-\t" + functionName + "\t-\t-");
        }
    }

    /**
     * Print string to a file
     * @param output String of what to put into the output file
     * @param outputFileName String desired name of the output file
     */
    private void printFile(String output, String outputFileName){
        printHeader("printFile");
        String[] outputArray = output.split("\n");

        //https://www.w3schools.com/java/java_files_create.asp
        try {
            PrintWriter myFile = new PrintWriter(outputFileName, "UTF-8");
            for (String out : outputArray) {
                if(debuggingMode){
                    System.out.println("Printing to file: " + out);
                }
                myFile.println(out);
            }
            myFile.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Class constructor
     * @param args Array of string arguments: (required) input file name, (required) output
     *                  file name 1, (required) output file name 2, (optional) debugging mode.
     */
    public Assign3b(String[] args){
        checkArguments(args);
        readFile();
        buildBinaryTree();

        output1 = studentDatabase.printDepthFirstInorder();
        output2 = studentDatabase.breadthFirstTraversal();

        if(debuggingMode) {
            printHeader("printDepthFirstInorder");
            System.out.println(output1);
            printHeader("breadthFirstTraversal");
            System.out.println(output2);
            printHeader("printOutTree");
            studentDatabase.printOutTree();
        }

        printFile(output1, outputFileName1);
        printFile(output2, outputFileName2);
    }

    /**
     * Main method
     * @param args Array of string arguments: (required) input file name, (required) output
     *                  file name 1, (required) output file name 2, (optional) debugging mode.
     */
    public static void main(String[] args) {
        //String[] theArgs = {"a3input2", "printDepthFirstInorder", "breadthFirstTraversal", "1"};
        Assign3b runClass = new Assign3b(args);

    }
}
