package assignment2JavaProgram;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class csvFileProcessing extends fileProcessing {
    private List<student> studentRecords;
    private String unitName;
    private int validRecords;

    // Constructor
    public csvFileProcessing() {
        this.studentRecords = new ArrayList<>();
        this.unitName = "";
        this.validRecords = 0;
    }


    public boolean readFile(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            System.out.println("Error: File name cannot be empty");
            return false;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // Read unit name from first line
            unitName = br.readLine();
            if (unitName == null || unitName.trim().isEmpty()) {
                System.out.println("Error: File is empty or missing unit name");
                return false;
            }

            String line;
            // Skip header line
            br.readLine();
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.trim().startsWith("#")) {
                    continue; // Ignore empty lines or comments
                }

                // Parse CSV line
                String[] data = line.split(",");
                if (data.length < 6) {
                    System.out.println("Warning: Skipping record with insufficient fields");
                    continue;
                }

                String lastName = data[0].trim();
                String firstName = data[1].trim();
                String studentID = data[2].trim();

                // Validate name and ID
                if (lastName.isEmpty() || firstName.isEmpty() || studentID.isEmpty()) {
                    System.out.println("Warning: Skipping record with missing name or ID");
                    continue;
                }

                // Handle marks, including empty fields
                List<String> emptyFields = new ArrayList<>();
                float mark1 = parseMark(data[3], "A1", emptyFields, firstName, lastName, studentID);
                float mark2 = parseMark(data[4], "A2", emptyFields, firstName, lastName, studentID);
                float mark3 = parseMark(data[5], "A3", emptyFields, firstName, lastName, studentID);

                // Warn about empty fields
                if (!emptyFields.isEmpty()) {
                    System.out.println("Warning: Missing marks for " + firstName + " " + lastName +
                            " (Student ID: " + studentID + ") in fields " + emptyFields);
                    System.out.println("Consider updating the missing fields to include this record in calculations");
                }

                // Validate marks range (0-30)
                boolean validMarks = true;
                if (mark1 < 0 || mark1 > 30 || mark2 < 0 || mark2 > 30 || mark3 < 0 || mark3 > 30) {
                    System.out.println("Warning: Invalid mark in record for " + firstName + " " + lastName);
                    validMarks = false;
                }

                if (validMarks) {
                    studentRecords.add(new student(lastName, firstName, studentID, mark1, mark2, mark3));
                    validRecords++;
                } else {
                    System.out.println("Warning: Skipping record with invalid marks");
                }
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error: File not found or cannot be read");
            return false;
        }
    }

    // Helper method to parse marks and handle empty fields
    private float parseMark(String markStr, String fieldName, List<String> emptyFields,
                           String firstName, String lastName, String studentID) {
        if (markStr == null || markStr.trim().isEmpty()) {
            emptyFields.add(fieldName);
            return 0.0f;
        }
        try {
            return Float.parseFloat(markStr.trim());
        } catch (NumberFormatException e) {
            System.out.println("Warning: Invalid mark in field " + fieldName + " for " +
                    firstName + " " + lastName + " (Student ID: " + studentID + ")");
            return 0.0f;
        }
    }

    // Getters (encapsulation)
    public List<student> getStudentRecords() {
        return studentRecords;
    }

    public String getUnitName() {
        return unitName;
    }

    public int getValidRecordsCount() {
        return validRecords;
    }
}

