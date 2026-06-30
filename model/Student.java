package model;

public class Student {
    private int id;
    private String name;
    private String dob;
    private int age;
    private String mobile;
    private String email;
    private String course;
    public Student(int id, String name, String dob,int age, String mobile,String email, String course) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.age = age;
        this.mobile = mobile;
        this.email = email;
        this.course = course;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDob() {
        return dob;
    }
    public int getAge() {
        return age;
    }
    public String getMobile() {
        return mobile;
    }
    public String getEmail() {
        return email;
    }
    public String getCourse() {
        return course;
    }
    public void display() {
        System.out.printf("%-5d %-20s %-12s %-5d %-12s %-30s %-15s%n",id,name,dob,age,mobile,email,course);
    }
}