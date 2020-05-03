package DAO.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;

import DAO.StdLessHystDAO;
import database.StdLessHyst;
import database.HibernateUtil;

public class StdLessHystDAOImpl implements StdLessHystDAO {
	public void addStdLessHyst(StdLessHyst StdLessHyst) throws SQLException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(StdLessHyst);
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	public void updateStdLessHyst(StdLessHyst StdLessHyst) throws SQLException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(StdLessHyst);
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
	}
	
	public void deleteStdLessHyst(StdLessHyst StdLessHyst) throws SQLException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(StdLessHyst);
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<StdLessHyst> getCoursesByStudent(Long student_id) throws SQLException {
		Session session = null;
		List<StdLessHyst> courses = new ArrayList<StdLessHyst>();
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		TypedQuery<StdLessHyst> query = session.createQuery(
				"SELECT e FROM StdLessHyst e " +
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
	public Collection<StdLessHyst> getStudentsByCourse(Long course_id) throws SQLException {
		Session session = null;
		List<StdLessHyst> courses = new ArrayList<StdLessHyst>();
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		TypedQuery<StdLessHyst> query = session.createQuery(
				"SELECT e FROM StdLessHyst e " +
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
