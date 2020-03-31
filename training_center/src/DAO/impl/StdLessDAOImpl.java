package DAO.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;

import DAO.StdLessDAO;
import database.StdLess;
import training_center.HibernateUtil;

public class StdLessDAOImpl implements StdLessDAO {

	public void addStdLess(StdLess stdless) throws SQLException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(stdless);
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	public void updateStdLess(StdLess stdless) throws SQLException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(stdless);
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
	}
	
	public void deleteStdLess(StdLess stdless) throws SQLException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(stdless);
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<StdLess> getCoursesByStudent(Long student_id) throws SQLException {
		Session session = null;
		List<StdLess> courses = new ArrayList<StdLess>();
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		TypedQuery<StdLess> query = session.createQuery(
				"SELECT e FROM StdLess e " +
				"WHERE e.id.student_id = :id")
				.setParameter("id", student_id);
		courses = query.getResultList();
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
		return courses;
	}

	@SuppressWarnings("unchecked")
	public Collection<StdLess> getStudentsByCourse(Long course_id) throws SQLException {
		Session session = null;
		List<StdLess> courses = new ArrayList<StdLess>();
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		TypedQuery<StdLess> query = session.createQuery(
				"SELECT e FROM StdLess e " +
				"WHERE e.id.course_id = :id")
				.setParameter("id", course_id);
		courses = query.getResultList();
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
		return courses;
	}	
}
