package DAO;

import database.Admin;

import java.sql.SQLException;
import java.util.Collection;

public interface AdminDAO {
	public void addAdmin(Admin admin) throws SQLException;
	public void updateAdmin(Admin admin) throws SQLException;
	public void deleteAdmin(Admin admin) throws SQLException;
	public Admin getAdminById(Long admin_id) throws SQLException;
	public Admin getAdminByLogin(String login) throws SQLException;
	public Collection<Admin> getAllAdmins() throws SQLException;
}
