package DAO.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;

import DAO.CourseDAO;
import database.Course;
import database.Professor;
import database.Std_less;
import database.Student;
import training_center.HibernateUtil;

public class CourseDAOImpl implements CourseDAO {

	public void addCourse(Course course) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(course);
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Course insertion fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public void updateCourse(Course course) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(course);
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Course update fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public void deleteCourse(Course course, Boolean active) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			if (active) {
				session.delete(course);
			} else {
				course.setIsActual(false);
				session.update(course);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Course removing fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public Course getCourseById(Long course_id) throws SQLException {
		Session session = null;
		Course course = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			course = (Course) session.get(Course.class, course_id);			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Course getting fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return course;
	}

	public Collection<Course> getAllCourses(Boolean active) throws SQLException {
		Session session = null;
		List<Course> courses = new ArrayList<Course>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			if (active) {
				session.beginTransaction();
				Query query = session.createQuery("from Course where relevance = t"); //need check
				courses = (List<Course>) query.list();
				session.getTransaction().commit();
			} else {
				courses = session.createCriteria(Course.class).list();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Course getall fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return courses;
	}

	public Collection<Course> getCoursesByProf(Professor professor, Boolean active) throws SQLException {
		Session session = null;
		List<Course> res = new ArrayList<Course>();
		List<Course> courses = new ArrayList<Course>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			res.addAll(professor.getCourses());
			session.getTransaction().commit();
			if (active) {
				Iterator iter = res.iterator();
				while (iter.hasNext()) {
					Course tmp = (Course) iter.next();
					if (tmp.getIsActual()) {
						courses.add(tmp);
					}
				}
			} else {
				courses = res;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Course by prof fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return courses;
	}
	
	public Collection<Course> getCoursesByStudent(Student student, Boolean active) throws SQLException {
		Session session = null;
		List<Std_less> res = new ArrayList<Std_less>();
		List<Course> courses = new ArrayList<Course>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			res.addAll(student.getCourses());
			session.getTransaction().commit();
			Iterator iter = res.iterator();
			while (iter.hasNext()) {
				courses.add(getCourseById((((Std_less)iter.next()).getCourse_id())));
			}
			if (!active) {
				courses.addAll(student.getPrev_courses());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Courses by student fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return courses;
	}
}
