package assignment2JavaProgram;

// Abstract class representing a generic person with common attributes
public abstract class Person {
    protected String lastName;
    protected String firstName;
    protected String id;

    // Constructor for initializing person details
    public Person(String lastName, String firstName, String id) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.id = id;
    }

    // Abstract method for displaying details, to be overridden by subclasses
    public abstract void displayDetails();
}