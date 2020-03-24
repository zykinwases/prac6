package DAO.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;

import DAO.CompanyDAO;
import database.Company;
import training_center.HibernateUtil;

public class CompanyDAOImpl implements CompanyDAO {

	public void addCompany(Company company) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(company);
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Company insertion fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public void updateCompany(Company company) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(company);
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Company update fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public void deleteCompany(Company company) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(company);
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Company removing fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public Company getCompanyById(Long company_id) throws SQLException {
		Session session = null;
		Company company = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			company = (Company) session.get(Company.class, company_id);			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Company getting fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return company;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public Collection<Company> getAllCompanies() throws SQLException {
		Session session = null;
		List<Company> companies = new ArrayList<Company>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			companies = session.createCriteria(Company.class).list();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Company getall fault", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return companies;
	}

}
