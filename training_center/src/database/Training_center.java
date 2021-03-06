package database;

import DAO.*;
import DAO.impl.*;

public class Training_center {
	private static AdminDAO adminDAO = null;
	private static CompanyDAO companyDAO = null;
	private static CourseDAO courseDAO = null;
	private static LessonDAO lessonDAO = null;
	private static ProfessorDAO professorDAO = null;
	private static StudentDAO studentDAO = null;
	private static StdLessDAO stdLessDAO = null;
	private static StdLessHystDAO stdLessHystDAO = null;
	private static Training_center instance = null;
	
	public static synchronized Training_center getInstance() {
		if (instance == null) {
			instance = new Training_center();
		}
		return instance;
	}
	public AdminDAO getAdminDAO() {
		if (adminDAO == null) {
			adminDAO = new AdminDAOImpl();
		}
		return adminDAO;
	}
	public CompanyDAO getCompanyDAO() {
		if (companyDAO == null) {
			companyDAO = new CompanyDAOImpl();
		}
		return companyDAO;
	}
	public CourseDAO getCourseDAO() {
		if (courseDAO == null) {
			courseDAO = new CourseDAOImpl();
		}
		return courseDAO;
	}
	public LessonDAO getLessonDAO() {
		if (lessonDAO == null) {
			lessonDAO = new LessonDAOImpl();
		}
		return lessonDAO;
	}
	public ProfessorDAO getProfessorDAO() {
		if (professorDAO == null) {
			professorDAO = new ProfessorDAOImpl();
		}
		return professorDAO;
	}
	public StudentDAO getStudentDAO() {
		if (studentDAO == null) {
			studentDAO = new StudentDAOImpl();
		}
		return studentDAO;
	}
	public StdLessDAO getStdLessDAO() {
		if (stdLessDAO == null) {
			stdLessDAO = new StdLessDAOImpl();
		}
		return stdLessDAO;
	}
	public StdLessHystDAO getStdLessHystDAO() {
		if (stdLessHystDAO == null) {
			stdLessHystDAO = new StdLessHystDAOImpl();
		}
		return stdLessHystDAO;
	}
}
