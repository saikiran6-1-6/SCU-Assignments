
import java.io.*;
import java.util.*;

class Student {
    private String name;
    private double average;

    public Student(String name, double average) {
        this.name = name;
        this.average = average;
    }

    public String getName() {
        return name;
    }

    public double getAverage() {
        return average;
    }
}

interface StudentOperations {
    void readData(String filename) throws IOException;
    void displayBelowThreshold(double threshold);
    void displayTopBottom(int count);
}

class StudentManager implements StudentOperations {
    private List<Student> students = new ArrayList<>();

    @Override
    public void readData(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.startsWith("#") || line.startsWith("//") || line.isEmpty()) continue;

            String[] parts = line.split(",");
            if (parts.length < 6) continue;

            String name = parts[0] + " " + parts[1];
            try {
                double mark1 = Double.parseDouble(parts[3]);
                double mark2 = Double.parseDouble(parts[4]);
                double mark3 = Double.parseDouble(parts[5]);

                if (isValid(mark1) && isValid(mark2) && isValid(mark3)) {
                    double average = (mark1 + mark2 + mark3) / 3.0;
                    students.add(new Student(name, average));
                }
            } catch (NumberFormatException ignored) {}
        }
    }

    private boolean isValid(double mark) {
        return mark >= 0 && mark <= 30;
    }

    @Override
    public void displayBelowThreshold(double threshold) {
        System.out.println("Students below threshold:");
        for (Student s : students) {
            if (s.getAverage() < threshold) {
                System.out.println(s.getName() + ": " + s.getAverage());
            }
        }
    }

    @Override
    public void displayTopBottom(int count) {
        students.sort(Comparator.comparingDouble(Student::getAverage));

        System.out.println("\nBottom " + count + " Students:");
        for (int i = 0; i < count && i < students.size(); i++) {
            Student s = students.get(i);
            System.out.println(s.getName() + ": " + s.getAverage());
        }

        System.out.println("\nTop " + count + " Students:");
        for (int i = students.size() - 1; i >= students.size() - count && i >= 0; i--) {
            Student s = students.get(i);
            System.out.println(s.getName() + ": " + s.getAverage());
        }
    }
}

public class StudentMarksModularOOP {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the CSV filename: ");
        String filename = scanner.nextLine();

        StudentOperations manager = new StudentManager();
        manager.readData(filename);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Filter students below threshold");
            System.out.println("2. Display top and bottom 5 students");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Enter threshold average: ");
                double threshold = scanner.nextDouble();
                manager.displayBelowThreshold(threshold);
            } else if (choice == 2) {
                manager.displayTopBottom(5);
            } else if (choice == 3) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
