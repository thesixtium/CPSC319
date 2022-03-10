/**
 * Data.java
 * Implementation of data for a Node
 *
 * @author Aleksander Berezowski
 * @version 1.3
 * @since 1.0
 */

public class Data {
    private final String studentNumber, studentName, studentDepartment, studentProgram, studentYear;

    /**
     * Constructor based off of new info
     */
    public Data(String number, String name, String department, String program, String year){
        this.studentNumber = number;
        this.studentName = name;
        this.studentDepartment = department;
        this.studentProgram = program;
        this.studentYear = year;
    }

    /**
     * Constructor to deep copy another object
     */
    public Data(Data otherStudent){
        this.studentNumber = otherStudent.studentNumber;
        this.studentName = otherStudent.studentName;
        this.studentDepartment = otherStudent.studentDepartment;
        this.studentProgram = otherStudent.studentProgram;
        this.studentYear = otherStudent.studentYear;
    }

    /**
     * Method to make and return a formatted string of student info
     */
    public String printOut(){
        StringBuilder returnString = new StringBuilder(50);
        returnString.append(this.studentNumber)
                .append("\t|\t")
                .append(this.studentName)
                .append("\t|\t")
                .append(this.studentDepartment)
                .append("\t|\t")
                .append(this.studentProgram)
                .append("\t|\t")
                .append(this.studentYear);
        return returnString.toString();
    }

    /**
     * Getter for student last name, needed for comparisons
     */
    public String getStudentName(){
        return this.studentName;
    }
}
