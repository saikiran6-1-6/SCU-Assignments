import java.util.Scanner;
import java.util.Arrays;

/**
 * A Program to compute the average price of ten most recent sales
 * and find cheapest and most expensive one's among them.
 *
 * @author (Saikiran Reddy Anugula)
 * @version (11/06/2025)
 */


public class statsOfStudents
 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Take the input from user to get the number of marks.
        System.out.print("Enter the number of marks you want statistics for: ");
        int numberofmarks = scanner.nextInt();

        //Storing the list of marks in a marks array
        double[] marks = new double[numberofmarks];
        int validmarks = 0;

        //Get the marks for individual subject.
        for (int i = 0; i < numberofmarks; i++ ) {
            System.out.print("Enter the marks of subject " + (i + 1) + ": ");

            double mark = scanner.nextDouble();

        //Check if the mark is less than 0 or greater than 30, if that is the case prompt the user to input valid marks.
        //Which is greater than 0 or less than 30.
            if (mark >=0 && mark <=30) {
                marks[validmarks] = mark;
                validmarks++;
            }
            else {
                System.out.println("Please enter marks between 0 and 30: ");
                i--;
            }

        }

    double lowestMarks = marks[0];
    double highestMarks = marks[0];

    for (int i = 0; i < validmarks; i++) {
        if (marks[i] < lowestMarks) {
            lowestMarks = marks[i];
        }
        if (marks[i] > highestMarks) {
            highestMarks = marks[i];
        }
    }

    System.out.println("The marks of the student are " + Arrays.toString(marks) + ": ");
    System.out.println("The lowest marks of the student are " + lowestMarks + ": ");
    System.out.println("The highest marks of student are "+ highestMarks + ": ");
    scanner.close();
    }
 }