package DAO;

import java.sql.SQLException;
import java.util.Collection;

import database.StdLessHyst;

public interface StdLessHystDAO {
	public void addStdLessHyst(StdLessHyst StdLessHyst) throws SQLException;
	public void updateStdLessHyst(StdLessHyst StdLessHyst) throws SQLException;
	public void deleteStdLessHyst(StdLessHyst StdLessHyst) throws SQLException;
	public Collection<StdLessHyst> getCoursesByStudent(Long student_id) throws SQLException; 
	public Collection<StdLessHyst> getStudentsByCourse(Long course_id) throws SQLException;
}
