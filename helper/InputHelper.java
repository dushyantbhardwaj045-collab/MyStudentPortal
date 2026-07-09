package helper;

import java.util.Scanner;
import validation.StudentValidator;

public class InputHelper {
    // ================= ID =================
    public static int readId(Scanner sc) {
        while (true) {
            System.out.print("Enter Id: ");
            try {
                int id = sc.nextInt();
                sc.nextLine();
                if (!StudentValidator.isValidId(id)) 
                System.out.println("Id must be greater than 0.");
                else return id;
            } catch (Exception e) {
                System.out.println("Invalid Id.");
                sc.nextLine();
            }
        }
    }
    // ================= NAME =================
    public static String readName(Scanner sc) {
        while (true) {
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            if (!StudentValidator.isValidName(name)) System.out.println("Invalid Name."); 
            else return name;
        }
    }
    // ================= DOB =================
    public static String readDob(Scanner sc) {
        while (true) {
            System.out.print("Enter DOB (dd-mm-yyyy): ");
            String dob = sc.nextLine();
            if (!StudentValidator.isValidDob(dob)) System.out.println("Invalid DOB."); 
            else return dob;
        }
    }
    // ================= AGE =================
    public static int readAge(Scanner sc) {
        while (true) {
            System.out.print("Enter Age: ");
            try {
                int age = sc.nextInt();
                sc.nextLine();
                if (!StudentValidator.isValidAge(age)) System.out.println("Age must be between 16 and 100."); 
                else return age;
            } catch (Exception e) {
                System.out.println("Invalid Age.");
                sc.nextLine();
            }
        }
    }
    // ================= MOBILE =================
    public static String readMobile(Scanner sc) {
        while (true) {
            System.out.print("Enter Mobile Number: ");
            String mobile = sc.nextLine();
            if (!StudentValidator.isValidMobile(mobile)) System.out.println("Invalid Mobile Number."); 
            else return mobile;
        }
    }
    // ================= EMAIL =================
    public static String readEmail(Scanner sc) {
        while (true) {
            System.out.print("Enter Email: ");
            String email = sc.nextLine();
            if (!StudentValidator.isValidEmail(email)) System.out.println("Invalid Email."); 
            else return email;
        }
    }
    // ================= COURSE =================
    public static String readCourse(Scanner sc) {
        while (true) {
            System.out.print("Enter Course: ");
            String course = sc.nextLine();
            if (!StudentValidator.isValidCourse(course)) System.out.println("Invalid Course."); 
            else return course;
        }
    }
}