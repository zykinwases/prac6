package DAO.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;

import DAO.ProfessorDAO;
import database.Professor;
import database.Company;
import database.HibernateUtil;

public class ProfessorDAOImpl implements ProfessorDAO {

	public void addProfessor(Professor professor) throws SQLException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(professor);
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	public void updateProfessor(Professor professor) throws SQLException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(professor);
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	public void deleteProfessor(Professor professor) throws SQLException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(professor);
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public Professor getProfessorById(Long professor_id) throws SQLException {
		Session session = null;
		Professor professor = null;
		session = HibernateUtil.getSessionFactory().openSession();
		TypedQuery<Professor> query = session.createQuery(
				"SELECT e FROM Professor e " +
				"WHERE e.professor_id = :id")
				.setParameter("id", professor_id);
		if (!query.getResultList().isEmpty()) {
			professor = query.getSingleResult();
		}		
		if (session != null && session.isOpen()) {
			session.close();
		}
		return professor;
	}

	@SuppressWarnings("unchecked")
	public Professor getProfessorByLogin(String login) throws SQLException {
		Session session = null;
		Professor professor = null;
		session = HibernateUtil.getSessionFactory().openSession();
		TypedQuery<Professor> query = session.createQuery(
				"SELECT e FROM Professor e " +
				"WHERE e.login = :login")
				.setParameter("login", login);
		if (!query.getResultList().isEmpty()) {
			professor = query.getSingleResult();
		}		
		if (session != null && session.isOpen()) {
			session.close();
		}
		return professor;
	}
	
	@SuppressWarnings("unchecked")
	public Professor getProfessorByFullName(String fullName) throws SQLException {
		Session session = null;
		Professor professor = null;
		session = HibernateUtil.getSessionFactory().openSession();
		TypedQuery<Professor> query = session.createQuery(
				"SELECT e FROM Professor e " +
				"WHERE e.first_name || \" \" || last_name = :fullName")
				.setParameter("fullName", fullName);
		if (!query.getResultList().isEmpty()) {
			professor = query.getSingleResult();
		}		
		if (session != null && session.isOpen()) {
			session.close();
		}
		return professor;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Professor> getAllProfessors() throws SQLException {
		Session session = null;
		List<Professor> professors = new ArrayList<Professor>();
		session = HibernateUtil.getSessionFactory().openSession();	
		TypedQuery<Professor> query = session.createQuery(
				"SELECT e FROM Professor e ");
		professors = query.getResultList();
		if (session != null && session.isOpen()) {
			session.close();
		}
		return professors;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Professor> getProfessorsByCompany(Company company) throws SQLException {
		Session session = null;
		List<Professor> profs = new ArrayList<Professor>();
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		TypedQuery<Professor> query = session.createQuery(
				"SELECT e FROM Professor e " +
				"WHERE e.company_id = :id")
				.setParameter("id", company);
		profs = query.getResultList();
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
		return profs;
	}
}