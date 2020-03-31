package DAO;

import database.Lesson;
import database.Course;

import java.sql.SQLException;
import java.util.Collection;

public interface LessonDAO {
	public void addLesson(Lesson lesson) throws SQLException;
	public void updateLesson(Lesson lesson) throws SQLException;
	public void deleteLesson(Lesson lesson) throws SQLException;
	public Lesson getLessonById(Long lesson_id) throws SQLException;
	public Collection<Lesson> getAllLessons() throws SQLException;
	public Collection<Lesson> getLessonsByCourse(Course course) throws SQLException;
}
