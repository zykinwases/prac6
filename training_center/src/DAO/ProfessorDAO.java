package DAO;

import database.Professor;

import java.sql.SQLException;
import java.util.Collection;

public interface ProfessorDAO {
	public void addProfessor(Professor professor) throws SQLException;
	public void updateProfessor(Professor professor) throws SQLException;
	public void deleteProfessor(Professor professor) throws SQLException;
	public Professor getProfessorById(Long professor_id) throws SQLException;
	public Professor getProfessorByLogin(String login) throws SQLException;
	public Collection<Professor> getAllProfessors() throws SQLException;
}
