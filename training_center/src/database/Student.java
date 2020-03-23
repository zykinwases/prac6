package database;

import database.Course;
import java.util.Set;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;

@Entity
@Table(name="student")
public class Student implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="student_id")
	private Long student_id;
	@Column(name="login")
	private String login;
	@Column(name="pswd_hash")
	private String pswd_hash;
	@Column(name="first_name")
	private String first_name;
	@Column(name="last_name")
	private String last_name;
	@Column(name="mobile")
	private String mobile;
	@Column(name="relevance")
	private Boolean isActual;	
	@OneToMany(mappedBy="student_id", fetch=FetchType.EAGER)
	private Set<Std_less> course_ids = new HashSet<Std_less>(); //set of courses of the student
	@Transient
	private Set<Course> prev_courses = new HashSet<Course>();
	
	public Student() {}
	public Student(String login, String pswd_hash, String first_name, String last_name, String mobile, Boolean isActual) {
		this.login = login;
		this.pswd_hash = pswd_hash;
		this.first_name = first_name;
		this.last_name = last_name;
		this.mobile = mobile;
		this.isActual = isActual;
	}
	
	public Long getId() {
		return student_id;
	}
	public void setId(Long id) {
		this.student_id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPswd_hash() {
		return pswd_hash;
	}
	public void setPswd_hash(String pswd_hash) {
		this.pswd_hash = pswd_hash;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Boolean getIsActual() {
		return isActual;
	}
	public void setIsActual(Boolean isActual) {
		this.isActual = isActual;
	}
	public Set<Std_less> getCourses() {
		return course_ids;
	}
	public void setCourses(Set<Std_less> courses) {
		this.course_ids = courses;
	}
	public Set<Course> getPrev_courses() {
		return prev_courses;
	}
	public void setPrev_courses(Set<Course> prev_courses) {
		this.prev_courses = prev_courses;
	}
	public void addPrev_courses(Course course) {
		prev_courses.add(course);
	}
}
