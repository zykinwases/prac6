package training_center;

import database.*;

import java.util.Iterator;
import java.sql.Timestamp;
import java.util.Collection;
import training_center.Training_center;

import org.hibernate.Session;
import org.postgresql.util.*;

public class Main {
	
	public static void insertall() {
		try {
			Training_center.getInstance().getCompanyDAO().addCompany(new Company("Tinkoff", "Moscow, Golovinskoye road, 5"));
			Training_center.getInstance().getCompanyDAO().addCompany(new Company("Russian school of management", "Moscow, Selskohozyastvennaya street, 17"));
			Training_center.getInstance().getCompanyDAO().addCompany(new Company("Academy of leadership competencies", "Moscow, Dubininskaya street, 40"));
			Training_center.getInstance().getProfessorDAO().addProfessor(new Professor("arkaD", "d3c6eb0075ad5d507caf01dd6e552cc9", "Arkadiev", "Dmitriy", (long)1));
			Training_center.getInstance().getProfessorDAO().addProfessor(new Professor("kornvictor", "6ecd969e26d35442388abf4af5d3d04a", "Korneev", "Victor", (long)2));
			Training_center.getInstance().getProfessorDAO().addProfessor(new Professor("simonya", "398b05a88e480ec9f618b4f7497ff3f0", "Simonova", "Ekaterina",(long)1));
			Training_center.getInstance().getProfessorDAO().addProfessor(new Professor("bolsh", "a8c89bcb316e486ac3f62e9dd5b03b39", "Bolshunov", "Arkadiy", (long)3));
			Training_center.getInstance().getCourseDAO().addCourse(new Course("Basics of banking", new PGInterval("1 month"), new PGInterval("1 hour"), 
					(long)1, true));
			Training_center.getInstance().getCourseDAO().addCourse(new Course("Legal culture", new PGInterval("1 month"), new PGInterval("2 hours"), 
					(long)2, true));
			Training_center.getInstance().getCourseDAO().addCourse(new Course("Mathematical statistics in banking", new PGInterval("1 month"), new PGInterval("1 hour"), 
					(long)1, true));
			Training_center.getInstance().getCourseDAO().addCourse(new Course("You are a leader! What is next?", new PGInterval("3 weeks"), new PGInterval("2 hours"), 
					(long)4, true));
			Training_center.getInstance().getLessonDAO().addLesson(new Lesson((long)1,	new Timestamp(2020,3,1,15,0,0,0)));
			Training_center.getInstance().getLessonDAO().addLesson(new Lesson((long)1, new Timestamp(2020,3,5,16,30,0,0)));
			Training_center.getInstance().getLessonDAO().addLesson(new Lesson((long)2, new Timestamp(2020,3,2,14,0,0,0)));
			Training_center.getInstance().getStudentDAO().addStudent(new Student("ivkoz", "cdf5e358a40de8e681ac9014c37badef", "Kozlov", "Ivan", "89125431212", true));
			Training_center.getInstance().getStudentDAO().addStudent(new Student("bolya", "1b06c1c926fac6898f6d884a2725d907", "Bolshunova", "Irina", "89124676425", true));
			Training_center.getInstance().getStudentDAO().addStudent(new Student("melk", "212ba19b0522e39a46cd56bced3cf37e", "Menshov", "Dmitriy", "89235124122", true));
			Training_center.getInstance().getStudentDAO().addStudent(new Student("ivanka", "0628836d3056aaf2aa3c6451a4283a5d", "Ivanova", "Alisa", "89235521111", true));
			Training_center.getInstance().getStudentDAO().addStudent(new Student("alfa", "8a73ed74dfb98ee945e131ca8b6499e9", "Alferov", "Andrey", "89456723412", true));
			Training_center.getInstance().getAdminDAO().addAdmin(new Admin("admin", "f189656226a53e50eae44f80d4befb6e", "Araratova", "Diana"));
			Training_center.getInstance().getStd_lessDAO().addStd_less(new Std_less((long)1, (long)4));
			Training_center.getInstance().getStd_lessDAO().addStd_less(new Std_less((long)1, (long)1));
			Training_center.getInstance().getStd_lessDAO().addStd_less(new Std_less((long)1, (long)3));
			Training_center.getInstance().getStd_lessDAO().addStd_less(new Std_less((long)2, (long)1));
			Training_center.getInstance().getStd_lessDAO().addStd_less(new Std_less((long)2, (long)4));
			Training_center.getInstance().getStd_lessDAO().addStd_less(new Std_less((long)3, (long)4));
			Training_center.getInstance().getStd_lessDAO().addStd_less(new Std_less((long)2, (long)2));
			Training_center.getInstance().getStd_lessDAO().addStd_less(new Std_less((long)5, (long)4));
			Training_center.getInstance().getStd_lessDAO().addStd_less(new Std_less((long)5, (long)3));
			Training_center.getInstance().getStd_lessDAO().addStd_less(new Std_less((long)4, (long)2));
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
	
	public static void main(String[] args) {// ��� ���������� ���������,
		                                          // ������ ���������
		try {
			insertall();
			Student std = Training_center.getInstance().getStudentDAO().getStudentById((long)1);
			Collection<Course> courses = Training_center.getInstance().getCourseDAO().getCoursesByStudent(std, true);
			Iterator iter = courses.iterator();
			while (iter.hasNext()) {
				Course s = (Course) iter.next();
				System.out.println(s.getName());
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
}