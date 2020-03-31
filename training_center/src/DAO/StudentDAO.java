package DAO;

import database.Student;

import java.sql.SQLException;
import java.util.Collection;

public interface StudentDAO {
	public void addStudent(Student student) throws SQLException;
	public void updateStudent(Student student) throws SQLException;
	public void deleteStudent(Student student) throws SQLException;
	public Student getStudentById(Long student_id) throws SQLException;
	public Collection<Student> getAllStudents() throws SQLException;
}
