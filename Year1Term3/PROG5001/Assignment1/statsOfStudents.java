import java.util.Scanner;

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

        //Check if at least one mark is entered
        if (numberofmarks < 1) {
            System.out.println("At least one mark is required to calculate statistics for the student marks.");
            scanner.close();
            return;
        }

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

        // Calculate and print mean (always possible if at least 1 mark)
        double MeanValue = mean(marks);
        System.out.println("The mean of student marks is " + MeanValue + ": ");

        // Only calculate lowest/highest marks and variance if at least 2 marks
        if (marks.length >= 2) {
            double[] LowestAndHighestMarks = lhmarks(marks);
            System.out.println("The lowest marks of the student are " + LowestAndHighestMarks[0] + ": ");
            System.out.println("The highest marks of the student are " + LowestAndHighestMarks[1] + ": ");
            double VarianceValue = variance(marks);
            System.out.println("The variance of student marks is " + VarianceValue + ": ");
        } else {
            System.out.println("At least two marks are required to calculate lowest/highest marks and variance.");
        }

        scanner.close();
    }

    private static double[] lhmarks(double[] marks) {
        //Check for at least two marks
        if (marks.length < 2) {
            System.out.println("At least two marks are required to calculate lowest and highest marks.");
            return new double[]{-1, -1};
        }
        double lmarks = marks[0];
        double hmarks = marks[0];
        for (int i = 1; i < marks.length; i++) {
            if (marks[i] < lmarks) lmarks = marks[i];
            if (marks[i] > hmarks) hmarks = marks[i];
        }
        return new double[]{lmarks, hmarks};
    }

    private static double mean(double[] marks) {
        //Check for at least one mark
        if (marks.length == 0) {
            System.out.println("At least one mark is required to calculate mean.");
            return -1;
        }
        double meanvalue = 0.0;
        for (int i = 0; i < marks.length; i++) {
            meanvalue = meanvalue + marks[i];
        }
        meanvalue = meanvalue / marks.length;
        return meanvalue;
    }

    private static double variance(double[] marks) {
        //Check for at least two marks
        if (marks.length < 2) {
            System.out.println("At least two marks are required to calculate variance.");
            return -1;
        }
        double meanValue = mean(marks);
        double sumofsquareddifferences = 0.0;
        for (int i = 0; i < marks.length; i++) {
            sumofsquareddifferences = sumofsquareddifferences + (marks[i] - meanValue) * (marks[i] - meanValue);
        }
        return sumofsquareddifferences / (marks.length - 1);
    }
}
