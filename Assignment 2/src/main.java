/**
 * main.java
 * Main function to run Assign2 with all parameters automatically
 *      and record all run times, printed in nanoseconds.
 *
 * @author Aleksander Berezowski
 * @version 1.4
 * @since 1.0
 */

/*
/**
 * [Desc]
 * @param x [desc]
 * @return [desc]
 */


public class main {
    /**
     * Only constructor for main; while it accepts a String array
     *      of arguments it should not be used.
     *
     * @param args None, this parameter should not be used
     */
    public static void main(String[] args){
        String[] files = {"input", "example_1", "example_2", "small"};
        //String[] files = {"input", "example_1", "example_2", "example_3", "large", "medium", "small"};
        for(String fileName : files){
            String[] myArgs = {fileName, "output2_" + fileName};
            System.out.println("Results for: " + fileName);
            var test = new Assign2(myArgs);
            System.out.println();
        }
    }
}
