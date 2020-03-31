package DAO.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;

import DAO.StudentDAO;
import database.Student;
import training_center.HibernateUtil;

public class StudentDAOImpl implements StudentDAO {

	public void addStudent(Student student) throws SQLException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(student);
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	public void updateStudent(Student student) throws SQLException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(student);
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	public void deleteStudent(Student student) throws SQLException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(student);
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Student getStudentById(Long student_id) throws SQLException {
		Session session = null;
		Student student = null;
		session = HibernateUtil.getSessionFactory().openSession();	
		TypedQuery<Student> query = session.createQuery(
				"SELECT e FROM Student e " +
				"WHERE e.student_id = :id")
				.setParameter("id", student_id);
		if (!query.getResultList().isEmpty()) {
			student = query.getSingleResult();
		}
		if (session != null && session.isOpen()) {
			session.close();
		}
		return student;
	}

	@SuppressWarnings("unchecked")
	public Collection<Student> getAllStudents() throws SQLException {
		Session session = null;
		List<Student> students = new ArrayList<Student>();
		session = HibernateUtil.getSessionFactory().openSession();
		TypedQuery<Student> query = session.createQuery(
				"SELECT e FROM Student e ");
		students = query.getResultList();
		if (session != null && session.isOpen()) {
			session.close();
		}
		return students;
	}
}
