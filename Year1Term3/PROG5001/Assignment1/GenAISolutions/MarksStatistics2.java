package GenAISolutions;

import java.util.Scanner;

public class MarksStatistics2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = getValidInteger(scanner, "Enter number of marks: ");
        double[] marks = new double[n];

        for (int i = 0; i < n; i++) {
            marks[i] = getValidMark(scanner, "Enter mark " + (i + 1) + " (0-30): ");
        }

        computeStatistics(marks);
    }

    public static int getValidInteger(Scanner scanner, String prompt) {
        int value;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. " + prompt);
                scanner.next();
            }
            value = scanner.nextInt();
        } while (value <= 0);
        return value;
    }

    public static double getValidMark(Scanner scanner, String prompt) {
        double mark;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextDouble()) {
                System.out.print("Invalid input. " + prompt);
                scanner.next();
            }
            mark = scanner.nextDouble();
        } while (mark < 0 || mark > 30);
        return mark;
    }

    public static void computeStatistics(double[] marks) {
        double highest = marks[0];
        double lowest = marks[0];
        double sum = 0;

        for (double mark : marks) {
            if (mark > highest) highest = mark;
            if (mark < lowest) lowest = mark;
            sum += mark;
        }

        double mean = sum / marks.length;

        double varianceSum = 0;
        for (double mark : marks) {
            varianceSum += (mark - mean) * (mark - mean);
        }

        double variance = varianceSum / marks.length;

        System.out.printf("Highest: %.2f\nLowest: %.2f\nMean: %.2f\nVariance: %.2f\n", highest, lowest, mean, variance);
      
    }
}
