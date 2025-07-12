package GenAISolutions;

import java.util.Scanner;

public class MarksStatistics1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;

        // Input number of marks
        do {
            System.out.print("Enter number of marks: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter a positive integer: ");
                scanner.next();
            }
            n = scanner.nextInt();
        } while (n <= 0);

        double[] marks = new double[n];

        // Input marks with validation
        for (int i = 0; i < n; i++) {
            double mark;
            do {
                System.out.print("Enter mark " + (i + 1) + " (0-30): ");
                while (!scanner.hasNextDouble()) {
                    System.out.print("Invalid input. Enter a number between 0 and 30: ");
                    scanner.next();
                }
                mark = scanner.nextDouble();
            } while (mark < 0 || mark > 30);
            marks[i] = mark;
        }

        double highest = marks[0];
        double lowest = marks[0];
        double sum = 0;

        for (double mark : marks) {
            if (mark > highest) highest = mark;
            if (mark < lowest) lowest = mark;
            sum += mark;
        }

        double mean = sum / n;

        double varianceSum = 0;
        for (double mark : marks) {
            varianceSum += (mark - mean) * (mark - mean);
        }

        double variance = varianceSum / n;

        System.out.printf("Highest: %.2f\nLowest: %.2f\nMean: %.2f\nVariance: %.2f\n", highest, lowest, mean, variance);
        scanner.close();
    }
}
