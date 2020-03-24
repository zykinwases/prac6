package DAO.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;

import DAO.ProfessorDAO;
import database.Professor;
import training_center.HibernateUtil;

public class ProfessorDAOImpl implements ProfessorDAO {

	public void addProfessor(Professor professor) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(professor);
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Professor insertion fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public void updateProfessor(Professor professor) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(professor);
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Professor update fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public void deleteProfessor(Professor professor) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(professor);
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Professor removing fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public Professor getProfessorById(Long professor_id) throws SQLException {
		Session session = null;
		Professor professor = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			professor = (Professor) session.get(Professor.class, professor_id);			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Professor getting fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return professor;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public Collection<Professor> getAllProfessors() throws SQLException {
		Session session = null;
		List<Professor> professors = new ArrayList<Professor>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			professors = session.createCriteria(Professor.class).list();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Professor getall fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return professors;
	}

}
