package database;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name="lesson")
public class Lesson implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="lesson_id")
	private Long lesson_id;
	@Column(name="course_id")
	private Long course_id;
	@Column(name="time")
	private Timestamp time;
	
	public Lesson() {}
	public Lesson(Long course_id, Timestamp time) {
		this.course_id = course_id;
		this.time = time;
	}
	
	public Long getId() {
		return lesson_id;
	}
	public void setId(Long id) {
		this.lesson_id = id;
	}
	public Long getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
}
