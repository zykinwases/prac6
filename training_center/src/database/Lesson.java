package database;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name="lesson")
public class Lesson implements Serializable{
	private static final long serialVersionUID = -1575596271312344023L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="lesson_id")
	private Long lesson_id;
	@ManyToOne
	@JoinColumn(name="course_id")
	private Course course_id;
	@Column(name="time")
	private Timestamp time;
	
	public Lesson() {}
	public Lesson(Course course_id, Timestamp time) {
		this.course_id = course_id;
		this.time = time;
	}
	
	public Long getId() {
		return lesson_id;
	}
	public void setId(Long id) {
		this.lesson_id = id;
	}
	public Course getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Course course_id) {
		this.course_id = course_id;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
}
