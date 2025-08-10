package assignment2JavaProgram;

// Student class extending Person, encapsulates marks and total marks
@SuppressWarnings("unused")
public class Student extends Person {
    private float mark1;
    private float mark2;
    private float mark3;
    private float totalMarks;

    // Constructor to initialize student data and calculate total marks as sum of all assignments (A1, A2, A3)
    public Student(String lastName, String firstName, String studentID, float mark1, float mark2, float mark3) {
        super(lastName, firstName, studentID);
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
        this.totalMarks = mark1 + mark2 + mark3; // Total marks as sum of A1, A2, A3
    }

    // Getter for total marks (encapsulation)
    public float getTotalMarks() {
        return totalMarks;
    }

    // Override displayDetails for polymorphic behavior
    @Override
    public void displayDetails() {
        System.out.println(firstName + " " + lastName + ", Student ID: " + id + ", Total Marks: " + totalMarks);
    }
}