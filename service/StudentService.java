package service;

import dao.StudentDAO;
import exception.DuplicateStudentException;
import exception.StudentNotFoundException;
import java.util.ArrayList;
import model.Student;

public class StudentService {
    private StudentDAO dao = new StudentDAO();
    public boolean studentExists(int id) {
        return dao.studentExists(id);
    }
    // ================= ADD STUDENT =================
    public void addStudent(Student student) throws DuplicateStudentException {
        if (dao.studentExists(student.getId()))
        throw new DuplicateStudentException("Student ID " + student.getId() + " already exists.");
        dao.addStudent(student);
        System.out.println("Student Added Successfully");
    }
    // ================= DISPLAY ALL =================
    public void displayStudents() {
        ArrayList<Student> students = dao.displayStudents();
        displayStudentsList(students);
    }
    // ================= SEARCH BY ID =================
    public void searchStudentById(int id) throws StudentNotFoundException {
        Student student = getStudentOrThrow(dao.searchStudentById(id),
        "Student ID " + id + " not found.");
        displayStudent(student);
    }
    // ================= SEARCH BY MOBILE =================
    public void searchStudentByMobile(String mobile)throws StudentNotFoundException {
        Student student = getStudentOrThrow(dao.searchStudentByMobile(mobile),
        "No student found with Mobile Number " + mobile + ".");
        displayStudent(student);
    }
    // ================= SEARCH BY EMAIL =================
    public void searchStudentByEmail(String email) throws StudentNotFoundException {
        Student student = getStudentOrThrow(dao.searchStudentByEmail(email),
        "No student found with Email " + email + ".");
        displayStudent(student);
    }
    // ================= UPDATE =================
    public void updateStudent(Student student) throws StudentNotFoundException {
        if (!dao.studentExists(student.getId()))
        throw new StudentNotFoundException("Student ID " + student.getId() + " not found.");
        dao.updateStudent(student);
        System.out.println("Student Updated Successfully");
    }
    // ================= DELETE =================
    public void deleteStudent(int id) throws StudentNotFoundException {
        if (!dao.studentExists(id))
        throw new StudentNotFoundException("Student ID " + id + " not found.");
        dao.deleteStudent(id);
        System.out.println("Student Deleted Successfully");
    }
    // ================= SORT =================
    public void sortStudents(String columnName) {
        ArrayList<Student> students = dao.getStudentsSorted(columnName);
        displayStudentsList(students);
    }
    // ===================================================
    //               PRIVATE HELPER METHODS
    // ===================================================
    private void displayStudent(Student student) {
        printHeader();
        student.display();
    }
    private void displayStudentsList(ArrayList<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No Students Found.");
            return;
        }
        printHeader();
        for (Student student : students) student.display();
    }
    private void printHeader() {
        System.out.println("\n===== STUDENT RECORD =====");
        System.out.printf("%-5s %-20s %-12s %-5s %-12s %-30s %-15s%n","ID","Name","DOB","Age","Mobile","Email","Course");
        System.out.println("--------------------------------------------------------------------------------------------------------");
    }
    private Student getStudentOrThrow(Student student,String message) throws StudentNotFoundException{
        if(student==null){
            throw new StudentNotFoundException(message);
        }
        return student;
    }
}