package DAO;

import java.sql.SQLException;
import java.util.Collection;

import database.Course;
import database.Std_less;
import database.Student;

public interface Std_lessDAO {
	public void addStd_less(Std_less stdless) throws SQLException;
	public void updateStd_less(Std_less stdless) throws SQLException;
	public void deleteStd_less(Std_less stdless) throws SQLException;
	//public Collection<Std_less> getStudentsByCourse(Course course, Boolean active) throws SQLException;
	//public Collection<Std_less> getCoursesByStudent(Student student, Boolean active) throws SQLException;
}
