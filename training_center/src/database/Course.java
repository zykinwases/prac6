package database;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="course")
public class Course implements Serializable {
	private static final long serialVersionUID = 3253934423554664660L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="course_id")
	private Long course_id;
	@Column(name="name")
	private String name;
	@Column(name="duration")
	private String duration;
	@Column(name="intense")
	private String intense;
	@ManyToOne
	@JoinColumn(name="professor_id")
	private Professor professor_id;	
	
	public Course() {}
	public Course(String name, String duration, String intense, Professor professor_id) {
		this.name = name;
		this.duration = duration;
		this.intense = intense;
		this.professor_id = professor_id;
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
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getIntense() {
		return intense;
	}
	public void setIntense(String intense) {
		this.intense = intense;
	}
	public Professor getProfessor_id() {
		return professor_id;
	}
	public void setProfessor_id(Professor professor_id) {
		this.professor_id = professor_id;
	}
}
