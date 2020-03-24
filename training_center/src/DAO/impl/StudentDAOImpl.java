package DAO.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;

import DAO.StudentDAO;
import database.Course;
import database.StdLess;
import database.Student;
import training_center.HibernateUtil;

@SuppressWarnings("deprecation")
public class StudentDAOImpl implements StudentDAO {

	public void addStudent(Student student) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(student);
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Student insertion fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public void updateStudent(Student student) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(student);
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Student update fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public void deleteStudent(Student student, Boolean forever) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			if (forever) {
				session.delete(student);
			} else {
				List<Course> courses = new ArrayList<Course>();
				courses.addAll(student.getCourses());
				Iterator<Course> iter = courses.iterator();
				while (iter.hasNext()) {
					Course c = iter.next();
					session.delete(new StdLess(student.getId(), c.getId()));
					c.addPrev_student(student);
				}
				student.setIsActual(false);
				session.update(student);
			}
			
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Student removing fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	public Student getStudentById(Long student_id) throws SQLException {
		Session session = null;
		Student student = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			student = (Student) session.get(Student.class, student_id);			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Student getting fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return student;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection<Student> getAllStudents(Boolean active) throws SQLException {
		Session session = null;
		List<Student> students = new ArrayList<Student>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			if (active) {
				session.beginTransaction();
				Query query = session.createQuery("from student where relevance = t"); //need check
				students = (List<Student>) query.list();
				session.getTransaction().commit();
			} else {
				students = session.createCriteria(Student.class).list();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Student getall fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return students;
	}
	
	public Collection<Student> getStudentsByCourse(Course course, Boolean active) throws SQLException {
		Session session = null;
		List<Student> students = new ArrayList<Student>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			students.addAll(course.getStudents());
			session.getTransaction().commit();
			if (!active) {
				students.addAll(course.getPrev_students());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Students by course fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return students;
	}
}
