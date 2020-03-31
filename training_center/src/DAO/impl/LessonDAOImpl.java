package DAO.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;

import DAO.LessonDAO;
import database.Course;
import database.Lesson;
import training_center.HibernateUtil;

public class LessonDAOImpl implements LessonDAO {

	public void addLesson(Lesson lesson) throws SQLException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(lesson);
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	public void updateLesson(Lesson lesson) throws SQLException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(lesson);
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	public void deleteLesson(Lesson lesson) throws SQLException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(lesson);
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public Lesson getLessonById(Long lesson_id) throws SQLException {
		Session session = null;
		Lesson lesson = null;
		session = HibernateUtil.getSessionFactory().openSession();
		TypedQuery<Lesson> query = session.createQuery(
				"SELECT e FROM Lesson e " +
				"WHERE e.lesson_id = :id")
				.setParameter("id", lesson_id);
		if (!query.getResultList().isEmpty()) {
			lesson = query.getSingleResult();
		}
		if (session != null && session.isOpen()) {
			session.close();
		}
		return lesson;
	}

	@SuppressWarnings("unchecked")
	public Collection<Lesson> getAllLessons() throws SQLException {
		Session session = null;
		List<Lesson> lessons = new ArrayList<Lesson>();
		session = HibernateUtil.getSessionFactory().openSession();
		TypedQuery<Lesson> query = session.createQuery(
				"SELECT e FROM Lesson e ");
		lessons = query.getResultList();
		if (session != null && session.isOpen()) {
			session.close();
		}
		return lessons;
	}

	@SuppressWarnings("unchecked")
	public Collection<Lesson> getLessonsByCourse(Course course) throws SQLException {
		Session session = null;
		List<Lesson> lessons = new ArrayList<Lesson>();
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		TypedQuery<Lesson> query = session.createQuery(
				"SELECT e FROM Lesson e " +
				"WHERE e.course_id = :id")
				.setParameter("id", course);
		lessons = query.getResultList();
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
		return lessons;
	}

}
