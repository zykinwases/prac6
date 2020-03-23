package DAO.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;

import DAO.LessonDAO;
import database.Course;
import database.Lesson;
import database.Std_less;
import database.Student;
import training_center.HibernateUtil;

public class LessonDAOImpl implements LessonDAO {

	public void addLesson(Lesson lesson) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(lesson);
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Lesson insertion fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public void updateLesson(Lesson lesson) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(lesson);
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Lesson update fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public void deleteLesson(Lesson lesson) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(lesson);
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Lesson removing fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public Lesson getLessonById(Long lesson_id) throws SQLException {
		Session session = null;
		Lesson lesson = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			lesson = (Lesson) session.get(Lesson.class, lesson_id);			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Lesson getting fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lesson;
	}

	public Collection<Lesson> getAllLessons() throws SQLException {
		Session session = null;
		List<Lesson> lessons = new ArrayList<Lesson>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			lessons = session.createCriteria(Lesson.class).list();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Lesson getall fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lessons;
	}

	public Collection<Lesson> getNextLessonsFor(Long time, Student student) throws SQLException {
		Session session = null;
		List<Lesson> lessons = new ArrayList<Lesson>();
		String period;
		try {
			if (time == 0) {
				period = "1 day";
			} else if (time == 1) {
				period = "2 days";
			} else if (time == 2) {
				period = "1 week";
			} else {
				period = "1 month";
			}
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = null;
			List<Std_less> t1 = new ArrayList<Std_less>();
			t1.addAll(student.getCourses());
			Iterator iter = t1.iterator();
			while (iter.hasNext()) {
				Std_less s = (Std_less) iter.next();
				query = session.createQuery("from lesson where course_id = :id " +
						" and time >= CURRENT_TIME and time <= CURRENT_DATE + :t");
				query.setParameter("id", s.getCourse_id());
				query.setParameter("t", period);
				lessons.addAll((List<Lesson>) query.list());
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Lesson by company fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lessons;
	}

	public Collection<Lesson> getLessonsByCourse(Course course) throws SQLException {
		Session session = null;
		List<Lesson> lessons = new ArrayList<Lesson>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = null;
			query = session.createQuery("select lesson.* from lesson inner join course using (course_id) where course_id =: id");
			query.setParameter("id", course.getId());
			lessons = (List<Lesson>) query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Lesson by course fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lessons;
	}

}
