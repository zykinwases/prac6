package database;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="stdless")
public class StdLess implements Serializable{
	private static final long serialVersionUID = -6578672338881851419L;
	@EmbeddedId
	private StdLessId id;
	@ManyToOne
	@JoinColumn(name="student_id", insertable=false, updatable=false)
	private Student student_id;
	@ManyToOne
	@JoinColumn(name="course_id", insertable=false, updatable=false)
	private Course course_id;

	public StdLess() {}
	public StdLess(Student student_id, Course course_id) {
		this.id = new StdLessId(student_id.getStudent_id(), course_id.getCourse_id());
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
