package users;

public class Teacher extends User {

    public Teacher(String firstName, String lastName, String phoneNumber, String password) {
        super(firstName, lastName, phoneNumber, password);
        this.email = User.emailGenerator(firstName, lastName, "TEACHER");
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "firstName='" + this.getFirstName() + '\'' +
                ", lastName='" + this.getLastName() + '\'' +
                ", phoneNumber='" + this.getPhoneNumber() + '\'' +
                '}';
    }
}
