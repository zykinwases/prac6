package DAO.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;

import DAO.CourseDAO;
import database.Course;
import database.Professor;
import training_center.HibernateUtil;

public class CourseDAOImpl implements CourseDAO {

	public void addCourse(Course course) throws SQLException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(course);
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	public void updateCourse(Course course) throws SQLException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(course);
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	public void deleteCourse(Course course) throws SQLException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(course);
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public Course getCourseById(Long course_id) throws SQLException {
		Session session = null;
		Course course = null;
		session = HibernateUtil.getSessionFactory().openSession();
		TypedQuery<Course> query = session.createQuery(
				"SELECT e FROM Course e " +
				"WHERE e.course_id = :id")
				.setParameter("id", course_id);
		if (!query.getResultList().isEmpty()) {
			course = query.getSingleResult();
		}		
		if (session != null && session.isOpen()) {
			session.close();
		}
		return course;
	}

	@SuppressWarnings("unchecked")
	public Collection<Course> getAllCourses() throws SQLException {
		Session session = null;
		List<Course> courses = new ArrayList<Course>();
		session = HibernateUtil.getSessionFactory().openSession();
		TypedQuery<Course> query = session.createQuery(
				"SELECT e FROM Course e ");
		courses = query.getResultList();
		if (session != null && session.isOpen()) {
			session.close();
		}
		return courses;
	}

	@SuppressWarnings("unchecked")
	public Collection<Course> getCoursesByProf(Professor professor) throws SQLException {
		Session session = null;
		List<Course> courses = new ArrayList<Course>();
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		TypedQuery<Course> query = session.createQuery(
				"SELECT e FROM Course e " +
				"WHERE e.professor_id = :id")
				.setParameter("id", professor);
		courses = query.getResultList();
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
		return courses;
	}
}
