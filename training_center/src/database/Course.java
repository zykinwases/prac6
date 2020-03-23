package database;

import database.Student;

import java.util.Set;
import java.io.Serializable;

import javax.persistence.*;

import java.util.HashSet;
import org.postgresql.util.*;

@Entity
@Table(name="course")
public class Course implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="course_id")
	private Long course_id;
	@Column(name="name")
	private String name;
	@Column(name="duration")
	private PGInterval duration;
	@Column(name="intense")
	private PGInterval intense;
	@Column(name="professor_id")
	private Long professor_id;
	@Column(name="relevance")
	private Boolean isActual;
	@OneToMany(mappedBy="course_id", fetch=FetchType.EAGER)
	private Set<Std_less> student_ids = new HashSet<Std_less>(); //set of students attending to the course
	@OneToMany(mappedBy="course_id", fetch=FetchType.EAGER)
	private Set<Lesson> lessons = new HashSet<Lesson>();
	@Transient 
	private Set<Student> prev_students = new HashSet<Student>();
	
	public Course() {}
	public Course(String name, PGInterval duration, PGInterval intense, Long professor_id, Boolean isActual) {
		this.name = name;
		this.duration = duration;
		this.intense = intense;
		this.professor_id = professor_id;
		this.isActual = isActual;
	}
	
	public Long getId() {
		return course_id;
	}
	public void setId(Long id) {
		this.course_id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PGInterval getDuration() {
		return duration;
	}
	public void setDuration(PGInterval duration) {
		this.duration = duration;
	}
	public PGInterval getIntense() {
		return intense;
	}
	public void setIntense(PGInterval intense) {
		this.intense = intense;
	}
	public Long getProfessor_id() {
		return professor_id;
	}
	public void setProfessor_id(Long professor_id) {
		this.professor_id = professor_id;
	}
	public Boolean getIsActual() {
		return isActual;
	}
	public void setIsActual(Boolean isActual) {
		this.isActual = isActual;
	}
	public Set<Std_less> getStudents() {
		return student_ids;
	}
	public void setStudents(Set<Std_less> students) {
		this.student_ids = students;
	}
	public Set<Lesson> getLessons() {
		return lessons;
	}
	public void setLessons(Set<Lesson> lessons) {
		this.lessons = lessons;
	}
	public Set<Student> getPrev_students() {
		return prev_students;
	}
	public void setPrev_students(Set<Student> prev_students) {
		this.prev_students = prev_students;
	}
	public void addPrev_student(Student student) {
		prev_students.add(student);
	}
}
