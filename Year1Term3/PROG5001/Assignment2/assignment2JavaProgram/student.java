package assignment2JavaProgram;

public class student extends person {
    private float mark1;
    private float mark2;
    private float mark3;
    private float totalMarks;

    public student(String lastName, String firstName, String studentID, float mark1, float mark2, float mark3) {
        super(lastName, firstName, studentID);
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
        this.totalMarks = mark1 + mark2 + mark3;
    }


    public float getTotalMarks() {
        return totalMarks;
    }


    public void displayDetails() {
        System.out.println(firstName + " " + lastName + ", Student ID: " + id + ", Total Marks: " + totalMarks);
    }
}

