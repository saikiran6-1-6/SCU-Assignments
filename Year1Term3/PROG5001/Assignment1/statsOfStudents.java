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
    
    double[] LowestAndHighestMarks = lhmarks(marks);
    double MeanValue = mean(marks);
    double VarianceValue = variance(marks);

    System.out.println("The marks of the student are " + Arrays.toString(marks) + ": ");
    System.out.println("The lowest marks of the student are " + LowestAndHighestMarks[0] + ": ");
    System.out.println("The highest marks of the student are " + LowestAndHighestMarks[1] + ": ");
    System.out.println("The mean of student marks is " + MeanValue + ": ");
    System.out.println("number of marks: " + marks.length);
    System.out.println("The variance of student marks is " + VarianceValue + ": ");    

    
    scanner.close();
    }

    private static double[] lhmarks(double[] marks) {
        double lmarks = marks[0];
        double hmarks = marks[0];

        for (int i = 0; i<marks.length; i++) {
            if (marks[i] < lmarks) {
                lmarks = marks[i];
            }
            if (marks[i] > hmarks) {
                hmarks = marks[i];
            }
        }

        return new double[]{lmarks,hmarks};
    }
    private static double mean(double[] marks) {
        double meanvalue =0.0;
        int count = 0;

        for (int i=0; i<marks.length; i++)
        {
            meanvalue = meanvalue + marks[i];
            count++;
        }
        
        meanvalue = meanvalue/count;

        return meanvalue;        
    }

    private static double variance(double[] marks) {
        double sumofsquareddifferences = 0.0;
        double variance = 0.0;
        for (int i=0; i<marks.length; i++) {
            sumofsquareddifferences = sumofsquareddifferences + (marks[i] - mean(marks)) * (marks[i] - mean(marks));
        }

        variance = sumofsquareddifferences / (marks.length - 1);

        return variance;

    }


 }