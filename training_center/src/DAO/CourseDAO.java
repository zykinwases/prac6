package DAO;

import database.Course;
import database.Professor;

import java.sql.SQLException;
import java.util.Collection;

public interface CourseDAO {
	public void addCourse(Course course) throws SQLException;
	public void updateCourse(Course course) throws SQLException;
	public void deleteCourse(Course course) throws SQLException;
	public Course getCourseById(Long course_id) throws SQLException;
	public Course getCourseByName(String name) throws SQLException;
	public Collection<Course> getAllCourses() throws SQLException;
	public Collection<Course> getCoursesByProf(Professor professor) throws SQLException;
}
