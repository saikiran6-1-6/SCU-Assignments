
import java.io.*;
import java.util.*;

abstract class StudentProcessor {
    protected Map<String, Double> studentAverages = new HashMap<>();

    public abstract void readData(String filename) throws IOException;
    public abstract void displayFiltered(double threshold);
    public abstract void displayTopBottom(int count);
}

class CSVStudentProcessor extends StudentProcessor {

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
                    studentAverages.put(name, average);
                }
            } catch (NumberFormatException ignored) {}
        }
    }

    private boolean isValid(double mark) {
        return mark >= 0 && mark <= 30;
    }

    @Override
    public void displayFiltered(double threshold) {
        System.out.println("Students below threshold:");
        for (Map.Entry<String, Double> entry : studentAverages.entrySet()) {
            if (entry.getValue() < threshold) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    @Override
    public void displayTopBottom(int count) {
        List<Map.Entry<String, Double>> sorted = new ArrayList<>(studentAverages.entrySet());
        sorted.sort(Map.Entry.comparingByValue());

        System.out.println("\nBottom " + count + " Students:");
        for (int i = 0; i < count && i < sorted.size(); i++) {
            System.out.println(sorted.get(i).getKey() + ": " + sorted.get(i).getValue());
        }

        System.out.println("\nTop " + count + " Students:");
        for (int i = sorted.size() - 1; i >= sorted.size() - count && i >= 0; i--) {
            System.out.println(sorted.get(i).getKey() + ": " + sorted.get(i).getValue());
        }
    }
}

public class StudentMarksMonolithicOOP {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the CSV filename: ");
        String filename = scanner.nextLine();

        StudentProcessor processor = new CSVStudentProcessor();
        processor.readData(filename);

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
                processor.displayFiltered(threshold);
            } else if (choice == 2) {
                processor.displayTopBottom(5);
            } else if (choice == 3) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
