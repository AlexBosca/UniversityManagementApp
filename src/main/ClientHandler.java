package main;

import database.Driver;
import users.Student;
import users.Teacher;
import users.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {

    private static final String BANNER = "\nEnter your option:\n" +
            "1. Register\n" +
            "2. Authentication\n" +
            "3. List Students\n" +
            "4. List Teachers\n" +
            "5. Create Course\n" +
            "6. List Courses\n" +
            "7. Exit";

    private static final String HEADER = "\nHello ";
    private static final String STUDENT_MENU = "\n   Student Menu\n------------------\nEnter your option:\n" +
            "1. First option\n" +
            "2. Second Option\n" +
            "3. Exit";
    private static final String TEACHER_MENU = "\n   Teacher Menu\n------------------\nEnter your option:\n" +
            "1. First option\n" +
            "2. Second Option\n" +
            "3. Exit";

    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;
    private final Socket socket;
    private final Driver database;

    private boolean exit = false;

    public ClientHandler(Socket socket, DataInputStream inputStream, DataOutputStream outputStream) {
        this.socket = socket;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.database = Driver.getInstance();
    }

    @Override
    public void run() {

        String received;
        String toReturn;

        while(!exit) {
            try {
                outputStream.writeUTF(BANNER);

                received = inputStream.readUTF();
                int option = Integer.parseInt(received);

                switch (option) {
                    case 1:
                        doRegister();
                        break;
                    case 2:
                        doAuthentication();
                        break;
                    case 3:
//                        outputStream.writeUTF(listStudents());
                        break;
                    case 4:
//                        outputStream.writeUTF(listTeachers());
                        break;
                    case 5:
                        outputStream.writeUTF("Create a Course\n");
                        break;
                    case 6:
                        outputStream.writeUTF("List of courses\n");
                        break;
                    case 7:
                        outputStream.writeUTF("Exiting....");
                        exit = true;
                        break;
                    default:
                        outputStream.writeUTF("Invalid option");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void doRegister() throws IOException {

        User user;
        outputStream.writeUTF("Registration\nEnter your first name:");
        String firstName = inputStream.readUTF();

        outputStream.writeUTF("Enter your last name:");
        String lastName = inputStream.readUTF();

        outputStream.writeUTF("Enter your phone number:");
        String phoneNumber = inputStream.readUTF();

        outputStream.writeUTF("Enter your password:");
        String password = inputStream.readUTF();

        outputStream.writeUTF("You are a student or a teacher?");
        String choice = inputStream.readUTF();

        if(choice.equalsIgnoreCase("STUDENT")) {
            user = new Student(firstName, lastName, phoneNumber, password);
//            database.enrollStudent((Student) user);
        } else if(choice.equalsIgnoreCase("TEACHER")) {
            user = new Teacher(firstName, lastName, phoneNumber, password);
//            database.hireTeacher((Teacher) user);
        }

        outputStream.writeUTF("Your registration has successfully done");
    }

    public void doAuthentication() throws IOException {

        outputStream.writeUTF("Authentication\nEnter your e-mail:");
        String email = inputStream.readUTF();

        outputStream.writeUTF("Enter your password:");
        String password = inputStream.readUTF();

//        User user = database.returnUser(email);
//
//        if((user == null) || (!password.equals(user.getPassword()))) {
//            outputStream.writeUTF("You introduced your password or your email wrong");
//        }
//
//        outputStream.writeUTF("You successfully logged in");
//        launchMenu(user);
    }

//    public String listStudents() throws IOException {
//
//        String reply;
//
//        ArrayList<Student> students = database.getStudents();
//
//        if(!students.isEmpty()) {
//            reply = "List of Students:\n";
//
//            for(Student student : students) {
//                reply += student;
//                reply += "\n";
//            }
//        } else {
//            reply = "";
//        }
//
//        return reply;
//    }
//
//    public String listTeachers() {
//
//        String reply;
//
//        ArrayList<Teacher> teachers = database.getTeachers();
//
//        if(!teachers.isEmpty()) {
//            reply = "List of Teachers:\n";
//
//            for(Teacher teacher : teachers) {
//                reply += teacher;
//                reply += "\n";
//            }
//        } else {
//            reply = "";
//        }
//
//        return reply;
//    }

    public void launchMenu(User user) throws IOException {

        outputStream.writeUTF(HEADER + user.getName() + "\n\n");        //Header

        if (user instanceof Student) {
            printStudentMenu();
        } else if (user instanceof Teacher) {
            printTeacherMenu();
        }
    }

    public void printStudentMenu() throws IOException {

        boolean exit = false;

        while(!exit) {
            outputStream.writeUTF(STUDENT_MENU);
            int option = Integer.parseInt(inputStream.readUTF());

            switch (option) {
                case 1:
                    outputStream.writeUTF("First option");
                    break;
                case 2:
                    outputStream.writeUTF("Second option");
                    break;
                case 3:
                    outputStream.writeUTF("You just logged out, Bye!");
                    exit = true;
                    break;
                default:
                    outputStream.writeUTF("Nonexistent option");
            }
        }
    }

    public void printTeacherMenu() throws IOException {

        boolean exit = false;

        while(!exit) {
            outputStream.writeUTF(STUDENT_MENU);
            int option = Integer.parseInt(inputStream.readUTF());

            switch (option) {
                case 1:
                    outputStream.writeUTF("First option");
                    break;
                case 2:
                    outputStream.writeUTF("Second option");
                    break;
                case 3:
                    outputStream.writeUTF("You just logged out, Bye!");
                    exit = true;
                    break;
                default:
                    outputStream.writeUTF("Nonexistent option");
            }
        }
    }
}
