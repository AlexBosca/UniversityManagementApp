package util;

import users.Student;
import users.Teacher;

import java.util.ArrayList;

public class Course {

    private String name;
    private String description;
//    private
    private ArrayList<Student> students;
    private Teacher teacher;

    public Course(String name, String description) {
        this.students = new ArrayList<>();
    }

    public boolean addStudent(Student student) {
        return students.add(student);
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }
}
