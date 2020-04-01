package DAO.impl;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import org.hibernate.Session;

import training_center.HibernateUtil;
import DAO.AdminDAO;
import database.Admin;

public class AdminDAOImpl implements AdminDAO {

	public void addAdmin(Admin admin) throws SQLException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(admin);
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	public void updateAdmin(Admin admin) throws SQLException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(admin);
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	public void deleteAdmin(Admin admin) throws SQLException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(admin);
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public Admin getAdminById(Long admin_id) throws SQLException {
		Session session = null;
		Admin admin = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		TypedQuery<Admin> query = session.createQuery(
				"SELECT e FROM Admin e " +
				"WHERE e.admin_id = :id")
				.setParameter("id", admin_id);
		if (!query.getResultList().isEmpty()) {
			admin = query.getSingleResult();
		}		
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
		return admin;
	}

	@SuppressWarnings("unchecked")
	public Admin getAdminByLogin(String login) throws SQLException {
		Session session = null;
		Admin admin = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		TypedQuery<Admin> query = session.createQuery(
				"SELECT e FROM Admin e " +
				"WHERE e.login = :login")
				.setParameter("login", login);
		if (!query.getResultList().isEmpty()) {
			admin = query.getSingleResult();
		}		
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
		return admin;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Admin> getAllAdmins() throws SQLException {
		Session session = null;
		List<Admin> admins = new ArrayList<Admin>();
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		TypedQuery<Admin> query = session.createQuery(
				"SELECT e FROM Admin e");
		admins = query.getResultList();
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
		return admins;
	}
}
