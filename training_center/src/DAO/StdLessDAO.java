package DAO;

import java.sql.SQLException;

import database.StdLess;

public interface StdLessDAO {
	public void addStdLess(StdLess stdless) throws SQLException;
	public void updateStdLess(StdLess stdless) throws SQLException;
	public void deleteStdLess(StdLess stdless) throws SQLException;
}
