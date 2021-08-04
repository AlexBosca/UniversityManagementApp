package database;

import users.Student;
import users.Teacher;
import users.User;
import util.Course;

import java.sql.*;


public class Driver {

//    private final ArrayList<Student> students;
//    private final ArrayList<Teacher> teachers;
//    private final ArrayList<Course> courses;
    private static Driver instance = null;
    private static int currentStudentID = 1;
    private static int currentTEacherID = 1;
    private Connection connection;

    private Driver() {
//        students = new ArrayList<>();
//        teachers = new ArrayList<>();
//        courses = new ArrayList<>();

        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/university_management_system", "root", "123456789a");

            System.out.println("Connection created");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static Driver getInstance() {
        if(instance == null) {
            instance = new Driver();
        }

        return instance;
    }

    public boolean doLogin(String email, String password, String table) {
        try {
            ResultSet result;
            PreparedStatement statement = connection.prepareStatement("SELECT  firstName, password FROM " + table + " WHERE email=? and password=?");

                statement.setString(1, email);
                statement.setString(2, password);

                result = statement.executeQuery();

            return result.next();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return false;
    }

    public boolean enrollUser(User user, boolean isTeacher) {

        try {
            int result;

            String table = isTeacher ? "teachers" : "Students";

            PreparedStatement statement = connection.prepareStatement("INSERT INTO " + table + "(id, firstName, lastName, phoneNumber, password, email) VALUES(?, ?, ?, ?, ?, ?)");

            statement.setString(1, String.valueOf(isTeacher ? currentTEacherID : currentStudentID));
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getPhoneNumber());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getEmail());

            result = statement.executeUpdate();

            System.out.println(getUser(user.getEmail()));

            return true;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return false;
    }

    public void closeConnection() {
        try {
//            System.out.println("Closing database connection...");
            connection.close();
//            System.out.println("Connection with database just closed!");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public User getUser(String email) {

        User user = null;

        try {

            ResultSet result;

            String table = email.contains("teacher") ? "teachers" : "students";

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table + " WHERE email=?");

            statement.setString(1, email);

            result = statement.executeQuery();

            if(result.next()) {
                if (table.equals("teachers")) {
                    user = new Teacher(
                            result.getString("firstName"),
                            result.getString("lastName"),
                            result.getString("phoneNumber"),
                            result.getString("password"));
                    user.setEmail(result.getString("email"));
                } else {
                    user = new Student(
                            result.getString("firstName"),
                            result.getString("lastName"),
                            result.getString("phoneNumber"),
                            result.getString("password"));
                    user.setEmail(result.getString("email"));
                }
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return user;
    }

//    public boolean addCourse(Course course) {
//        return courses.add(course);
//    }
//
//    public User returnUser(String email) {
//        for(User user : students) {
//            if(user.getEmail().equals(email)) {
//                return user;
//            }
//        }
//
//        for(User user : teachers) {
//            if(user.getEmail().equals(email)) {
//                return user;
//            }
//        }
//
//        return null;
//    }

//    private void executeQuery(String query) {
//
//    }
}
