package DAO.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import DAO.CompanyDAO;
import database.Company;
import database.HibernateUtil;

public class CompanyDAOImpl implements CompanyDAO {

	public void addCompany(Company company) throws SQLException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(company);
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	public void updateCompany(Company company) throws SQLException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(company);
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	public void deleteCompany(Company company) throws SQLException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(company);
		session.getTransaction().commit();
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public Company getCompanyById(Long company_id) throws SQLException {
		Session session = null;
		Company company = null;
		session = HibernateUtil.getSessionFactory().openSession();
		TypedQuery<Company> query = session.createQuery(
				"SELECT e FROM Company e " +
				"WHERE e.company_id = :id")
				.setParameter("id", company_id);
		if (!query.getResultList().isEmpty()) {
			company = query.getSingleResult();
		}		
		if (session != null && session.isOpen()) {
			session.close();
		}
		return company;
	}

	@SuppressWarnings("unchecked")
	public Company getCompanyByName(String name) throws SQLException {
		Session session = null;
		Company company = null;
		session = HibernateUtil.getSessionFactory().openSession();
		TypedQuery<Company> query = session.createQuery(
				"SELECT e FROM Company e " +
				"WHERE e.name = :name")
				.setParameter("name", name);
		if (!query.getResultList().isEmpty()) {
			company = query.getSingleResult();
		}		
		if (session != null && session.isOpen()) {
			session.close();
		}
		return company;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Company> getAllCompanies() throws SQLException {
		Session session = null;
		List<Company> companies = new ArrayList<Company>();
		session = HibernateUtil.getSessionFactory().openSession();
		TypedQuery<Company> query = session.createQuery(
				"SELECT e FROM Company e ");
		companies = query.getResultList();
		if (session != null && session.isOpen()) {
			session.close();
		}
		return companies;
	}
}
