package DAO;

import java.sql.SQLException;
import java.util.Collection;

import database.StdLess;

public interface StdLessDAO {
	public void addStdLess(StdLess stdless) throws SQLException;
	public void updateStdLess(StdLess stdless) throws SQLException;
	public void deleteStdLess(StdLess stdless) throws SQLException;
	public Collection<StdLess> getCoursesByStudent(Long student_id) throws SQLException; 
	public Collection<StdLess> getStudentsByCourse(Long course_id) throws SQLException;
}
