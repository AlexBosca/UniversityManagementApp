package users;

import database.Driver;

public class Student extends User {

    public Student(String firstName, String lastName, String phoneNumber, String password) {
        super(firstName, lastName, phoneNumber, password);
        this.email = User.emailGenerator(firstName, lastName, "STUDENT");
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + this.getFirstName() + '\'' +
                ", lastName='" + this.getLastName() + '\'' +
                ", phoneNumber='" + this.getPhoneNumber() + '\'' +
                ", email='" + this.getEmail() + '\'' +
                '}';
    }
}
