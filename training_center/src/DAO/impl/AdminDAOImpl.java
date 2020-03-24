package DAO.impl;

import java.sql.SQLException;
import java.util.Collection;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;

import training_center.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Query;

import DAO.AdminDAO;
import database.Admin;

@SuppressWarnings("deprecation")
public class AdminDAOImpl implements AdminDAO {

	public void addAdmin(Admin admin) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(admin);
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Admin insertion fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public void updateAdmin(Admin admin) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(admin);
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Admin update fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public void deleteAdmin(Admin admin) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(admin);
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Admin removing fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public Admin getAdminById(Long admin_id) throws SQLException {
		Session session = null;
		Admin admin = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			admin = (Admin) session.get(Admin.class, admin_id);			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Admin getting fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return admin;
	}

	@SuppressWarnings("unchecked")
	public Collection<Admin> getAllAdmins() throws SQLException {
		Session session = null;
		List<Admin> admins = new ArrayList<Admin>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			admins = session.createCriteria(Admin.class).list();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Admin getall fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return admins;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Boolean authentication(String login, String pswd_hash) throws SQLException {
		Session session = null;
		List<Admin> admins = new ArrayList<Admin>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Admin where login =: log and pswd_hash =: pswd");
			query.setParameter("log", login);
			query.setParameter("pswd", pswd_hash);
			admins = (List<Admin>) query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Admin authentication fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return !admins.isEmpty();
	}

}
