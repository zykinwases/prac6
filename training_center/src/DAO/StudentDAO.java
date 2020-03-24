package DAO;

import database.Course;
import database.Student;

import java.sql.SQLException;
import java.util.Collection;

public interface StudentDAO {
	public void addStudent(Student student) throws SQLException;
	public void updateStudent(Student student) throws SQLException;
	public void deleteStudent(Student student, Boolean active) throws SQLException;
	public Student getStudentById(Long student_id) throws SQLException;
	public Collection<Student> getAllStudents(Boolean active) throws SQLException;
	public Collection<Student> getStudentsByCourse(Course course, Boolean forever) throws SQLException;
}
