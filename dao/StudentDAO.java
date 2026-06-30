package dao;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Student;
public class StudentDAO {
    public boolean studentExists(int id) {
        String query = "SELECT 1 FROM Students WHERE id=?";
        try (Connection con = DBConnection.getConnection();PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // ================== CRUD OPERATIONS ==================
    // -------------------- ADD --------------------
    public void addStudent(Student student){
        String query ="INSERT INTO Students(id,name,dob,age,mobile,email,course) VALUES(?,?,?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, student.getId());
            setStudentData(ps, student, 2);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // -------------------- DISPLAY --------------------
    public ArrayList<Student> displayStudents() {
        ArrayList<Student> students = new ArrayList<>();
        String query = "SELECT * FROM Students";
        try (Connection con = DBConnection.getConnection();PreparedStatement ps = con.prepareStatement(query);ResultSet rs = ps.executeQuery()) {
             while (rs.next()) students.add(mapStudent(rs));
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
    // -------------------- UPDATE --------------------
    public void updateStudent(Student student){
        String query ="UPDATE Students SET name=?, dob=?, age=?, mobile=?, email=?, course=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();PreparedStatement ps = con.prepareStatement(query)) {
            setStudentData(ps, student, 1);
            ps.setInt(7, student.getId());
            ps.executeUpdate();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
     // -------------------- DELETE --------------------
    public void deleteStudent(int id){
        String query = "DELETE FROM Students WHERE id=?";
        try (Connection con = DBConnection.getConnection();PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
             ps.executeUpdate();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // -------------------- SEARCH --------------------
    public Student searchStudentById(int id) {
        String query = "SELECT * FROM Students WHERE id=?";
        return findStudent(query, id);
    }
    public Student searchStudentByMobile(String mobile) {
        String query = "SELECT * FROM Students WHERE mobile=?";
        return findStudent(query, mobile);
    }
    public Student searchStudentByEmail(String email) {
        String query = "SELECT * FROM Students WHERE email=?";
        return findStudent(query, email);
    }
    // -------------------- SORT --------------------
    public ArrayList<Student> getStudentsSorted(String columnName) {
        ArrayList<Student> students = new ArrayList<>();
        // Security Check
        if (!(columnName.equals("id") || columnName.equals("name") || columnName.equals("age"))) {
            System.out.println("Invalid Sorting Column.");
            return students;
        }
        String query = "SELECT * FROM Students ORDER BY " + columnName;
        try (Connection con = DBConnection.getConnection();PreparedStatement ps = con.prepareStatement(query);ResultSet rs = ps.executeQuery()) {
            while (rs.next()) students.add(mapStudent(rs));
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
    // ================== HELPER METHODS ==================
    private Student mapStudent(ResultSet rs) throws SQLException {
        return new Student(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("dob"),
                rs.getInt("age"),
                rs.getString("mobile"),
                rs.getString("email"),
                rs.getString("course")
        );
    }
    private Student findStudent(String query, Object value) {
        try (Connection con = DBConnection.getConnection();PreparedStatement ps = con.prepareStatement(query)) {
            if (value instanceof Integer) ps.setInt(1, (Integer) value);
            else ps.setString(1, value.toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapStudent(rs);
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void setStudentData(PreparedStatement ps,Student student,int startIndex) throws SQLException {
        ps.setString(startIndex, student.getName());
        ps.setString(startIndex + 1, student.getDob());
        ps.setInt(startIndex + 2, student.getAge());
        ps.setString(startIndex + 3, student.getMobile());
        ps.setString(startIndex + 4, student.getEmail());
        ps.setString(startIndex + 5, student.getCourse());
    }
}