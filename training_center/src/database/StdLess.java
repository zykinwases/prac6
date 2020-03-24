package database;

import java.io.Serializable;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="stdless")
public class StdLess implements Serializable{
	@Id
	@Column(name="student_id")
	private Long student_id;
	@Column(name="course_id")
	private Long course_id;
	
	public StdLess() {}
	public StdLess(Long student_id, Long course_id) {
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
