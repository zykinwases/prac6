package DAO.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;

import DAO.LessonDAO;
import database.Course;
import database.Lesson;
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

	@SuppressWarnings({ "deprecation", "unchecked" })
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

	@SuppressWarnings("rawtypes")
	public Collection<Lesson> getNextLessonsFor(Long time, Student student) throws SQLException {
		Session session = null;
		List<Lesson> lessons = new ArrayList<Lesson>();
		Long period;
		try {
			if (time == 0) {
				period = (long)1;
			} else if (time == 1) {
				period = (long)2;
			} else if (time == 2) {
				period = (long)7;
			} else {
				period = (long)30;
			}
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			List<Course> t1 = new ArrayList<Course>();
			t1.addAll(student.getCourses());
			Iterator iter = t1.iterator();
			while (iter.hasNext()) {
				Course s = (Course) iter.next();
				System.out.println(s.getId());
				List<Lesson> l = new ArrayList<Lesson>();
				l.addAll(s.getLessons());
				Iterator iter2 = l.iterator();
				Timestamp today = new Timestamp(System.currentTimeMillis());
				Timestamp per = new Timestamp(System.currentTimeMillis() + period * 86400000);
				while (iter2.hasNext()) {
					Lesson les = (Lesson) iter2.next();
					System.out.println(les.getTime());
					if ((les.getTime().compareTo(today) >= 0) & (les.getTime().compareTo(per) < 0)) {
						lessons.add(les);
					}
				}
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Lesson for student fault", JOptionPane.OK_OPTION);
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
			lessons.addAll(course.getLessons());
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
