package assignment2JavaProgram;

public abstract class person {
    protected String lastName;
    protected String firstName;
    protected String id;


    public person(String lastName, String firstName, String id) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.id = id;
    }

    public abstract void displayDetails();
}
