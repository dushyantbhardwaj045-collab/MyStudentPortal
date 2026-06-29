package main;

import helper.InputHelper;
import java.util.Scanner;
import model.Student;
import service.StudentService;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService();
        while (true) {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Student By Id");
            System.out.println("6. Search Student By Number");
            System.out.println("7. Search Student By Email");
            System.out.println("8. Sort By ID");
            System.out.println("9. Sort By Name");
            System.out.println("10. Sort By Age");
            System.out.println("11. Exit");
            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:{
                    int id = InputHelper.readId(sc);
                    String name = InputHelper.readName(sc);
                    String dob = InputHelper.readDob(sc);
                    int age = InputHelper.readAge(sc);
                    String mobile = InputHelper.readMobile(sc);
                    String email = InputHelper.readEmail(sc);
                    String course = InputHelper.readCourse(sc);
                    Student student =new Student(id,name,dob,age,mobile,email,course);
                    execute(() -> service.addStudent(student));
                    break;
                }
                case 2:{
                    service.displayStudents();
                    break;
                }
                case 3:{
                    int id = InputHelper.readId(sc);
                    String name = InputHelper.readName(sc);
                    String dob = InputHelper.readDob(sc);
                    int age = InputHelper.readAge(sc);
                    String mobile = InputHelper.readMobile(sc);
                    String email = InputHelper.readEmail(sc);
                    String course = InputHelper.readCourse(sc);
                    Student student =new Student(id,name,dob,age,mobile,email,course);
                    execute(() -> service.updateStudent(student));
                    break;
                }
                case 4:{
                    int id = InputHelper.readId(sc);
                    System.out.print(
                    "Are you sure you want to delete this student? (Yes/No): ");
                    String confirm=sc.nextLine();
                    if(confirm.equalsIgnoreCase("Yes")){
                        execute(() -> service.deleteStudent(id));
                    }
                    else System.out.println("Delete Cancelled.");
                    break;
                }
                case 5:{
                    int id = InputHelper.readId(sc);
                    execute(() -> service.searchStudentById(id));
                    break;
                }
                case 6:{
                    String mobile = InputHelper.readMobile(sc);
                    execute(() -> service.searchStudentByMobile(mobile));
                    break;
                }
                case 7:{
                    String email = InputHelper.readEmail(sc);
                    execute(() -> service.searchStudentByEmail(email));
                    break;
                }
                case 8:{
                    service.sortStudents("id");
                    break;
                }
                case 9:{
                    service.sortStudents("name");
                    break;
                }
                case 10:{
                    service.sortStudents("age");
                    break;
                }
                case 11:{
                    System.out.println("Thank You!");
                    sc.close();
                    System.exit(0);
                }
                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
    @FunctionalInterface
    public interface ServiceAction {
        void execute() throws Exception;
    }
    // -------------------- HELPER METHODS --------------------
    private static void execute(ServiceAction action) {
        try {action.execute();}
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}