package database;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="std_less")
public class Std_less implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="student_id")
	private Long student_id;
	@Column(name="course_id")
	private Long course_id;
	
	public Std_less() {}
	public Std_less(Long student_id, Long course_id) {
		this.student_id = student_id;
		this.course_id = course_id;
	}
	
	public Long getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Long student_id) {
		this.student_id = student_id;
	}
	public Long getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
	}
}
