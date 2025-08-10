package assignment2JavaProgram;

import java.util.List;
import java.util.Scanner;

// Main program with menu system to execute both functionalities based on total marks (A1 + A2 + A3)
public class MenuSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // 1 - Display menu and get user choice
            System.out.println("\nMenu:");
            System.out.println("1. Filter students below a total marks threshold (sum of A1, A2, A3)");
            System.out.println("2. Display top 5 and bottom 5 students by total marks (sum of A1, A2, A3)");
            System.out.println("3. Exit");
            System.out.print("Enter choice (1-3): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input, please enter a number between 1 and 3");
                continue;
            }

            if (choice == 3) {
                System.out.println("Exiting program");
                break;
            }

            if (choice != 1 && choice != 2) {
                System.out.println("Error: Invalid choice, please select 1, 2, or 3");
                continue;
            }

            // 2 - Get and validate file name
            System.out.print("Enter file name (e.g., prog5001_students_grade_2022.csv): ");
            String fileName = scanner.nextLine();

            // 3 - Create FileProcessor
            FileProcessor fileProcessor = new CSVFileProcessor();

            // 4 - Read file using FileProcessor
            if (!fileProcessor.readFile(fileName)) {
                continue; // File reading failed, return to menu
            }

            // 5 - Get data from FileProcessor
            CSVFileProcessor csvProcessor = (CSVFileProcessor) fileProcessor;
            String unitName = csvProcessor.getUnitName();
            List<Student> studentRecords = csvProcessor.getStudentRecords();
            int validRecords = csvProcessor.getValidRecords();

            // 6 - Validate student records
            if (validRecords == 0) {
                System.out.println("No Valid Student Records Found");
                continue;
            }

            if (choice == 1) {
                // 7 - Filter students below total marks threshold
                System.out.print("Enter threshold for total marks (sum of A1, A2, A3): ");
                float threshold;
                try {
                    threshold = Float.parseFloat(scanner.nextLine());
                    if (threshold < 0) {
                        System.out.println("Threshold cannot be a negative number");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Threshold must be a valid number");
                    continue;
                }

                System.out.println("Unit: " + unitName);
                System.out.println("Students with total marks (sum of A1, A2, A3) below threshold " + threshold);
                int studentsPrinted = 0;
                for (Student student : studentRecords) {
                    if (student.getTotalMarks() < threshold) {
                        student.displayDetails();
                        studentsPrinted++;
                    }
                }

                // 8 - Edge case for filtering
                if (studentsPrinted == 0) {
                    System.out.println("No students have total marks below " + threshold);
                } else if (studentsPrinted == validRecords) {
                    System.out.println("All students have total marks below " + threshold);
                }
            } else if (choice == 2) {
                // 7 - Sort studentRecords by total marks (sum of A1, A2, A3)
                for (int i = 0; i < studentRecords.size() - 1; i++) {
                    for (int j = 0; j < studentRecords.size() - i - 1; j++) {
                        if (studentRecords.get(j).getTotalMarks() < studentRecords.get(j + 1).getTotalMarks()) {
                            Student temp = studentRecords.get(j);
                            studentRecords.set(j, studentRecords.get(j + 1));
                            studentRecords.set(j + 1, temp);
                        }
                    }
                }

                // 8 - Print top 5 students
                System.out.println("Unit: " + unitName);
                System.out.println("Top 5 students by total marks (sum of A1, A2, A3)");
                if (validRecords < 5) {
                    System.out.println("Note: Fewer than 5 students available");
                }
                int count = Math.min(5, validRecords);
                for (int i = 0; i < count; i++) {
                    studentRecords.get(i).displayDetails();
                }

                // 9 - Print bottom 5 students
                System.out.println("Bottom 5 students by total marks (sum of A1, A2, A3)");
                if (validRecords < 5) {
                    System.out.println("Note: Fewer than 5 students available");
                }
                int startIndex = Math.max(0, studentRecords.size() - 5);
                for (int i = startIndex; i < studentRecords.size(); i++) {
                    studentRecords.get(i).displayDetails();
                }
            }
        }
        scanner.close();
    }
}