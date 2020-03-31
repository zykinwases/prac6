package database;

import java.io.Serializable;
import javax.persistence.Column;

public class StdLessHystId implements Serializable {
	private static final long serialVersionUID = 8714683609897273982L;
	@Column(name="student_id")
	private Long student_id;
	@Column(name="course_id")
	private Long course_id;
	
	public StdLessHystId() {}
	public StdLessHystId(Long student_id, Long course_id) {
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
