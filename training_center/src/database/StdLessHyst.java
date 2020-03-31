package database;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="stdlesshyst")
public class StdLessHyst implements Serializable{
	private static final long serialVersionUID = 9032281743010438482L;
	@EmbeddedId
	private StdLessHystId id;
	@ManyToOne
	@JoinColumn(name="student_id", insertable=false, updatable=false)
	private Student student_id;
	@ManyToOne
	@JoinColumn(name="course_id", insertable=false, updatable=false)
	private Course course_id;

	public StdLessHyst() {}
	public StdLessHyst(Student student_id, Course course_id) {
		this.id = new StdLessHystId(student_id.getId(), course_id.getId());
		this.student_id = student_id;
		this.course_id = course_id;
	}
	
	public Student getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Student student_id) {
		this.student_id = student_id;
	}
	public Course getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Course course_id) {
		this.course_id = course_id;
	}
}
