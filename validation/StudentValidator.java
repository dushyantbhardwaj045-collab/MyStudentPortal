package validation;

public class StudentValidator {
    public static boolean isValidId(int id) {
        return id > 0;
    }
    public static boolean isValidName(String name) {
        return !name.trim().isEmpty() && name.matches("[A-Za-z ]{3,50}");
    }
    public static boolean isValidDob(String dob) {
        return dob.matches("\\d{2}-\\d{2}-\\d{4}");
    }
    public static boolean isValidAge(int age) {
        return age >= 16 && age <= 100;
    }
    public static boolean isValidMobile(String mobile) {
        return mobile.matches("[0-9]{10}");
    }
    public static boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
    public static boolean isValidCourse(String course) {
        return !course.trim().isEmpty() && course.matches("[A-Za-z0-9 .()&-]{2,50}");
    }
}