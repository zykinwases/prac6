package DAO;

import database.Course;
import database.Professor;
import database.Student;

import java.sql.SQLException;
import java.util.Collection;

public interface CourseDAO {
	public void addCourse(Course course) throws SQLException;
	public void updateCourse(Course course) throws SQLException;
	public void deleteCourse(Course course, Boolean forever) throws SQLException;
	public Course getCourseById(Long course_id) throws SQLException;
	public Collection<Course> getAllCourses(Boolean active) throws SQLException; //?
	public Collection<Course> getCoursesByProf(Professor professor, Boolean active) throws SQLException; //--
	public Collection<Course> getCoursesByStudent(Student student, Boolean active) throws SQLException;
}
