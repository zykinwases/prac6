package forms;

import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class LessonForm {
	private long lesson_id;
	private long course_id;
	private Timestamp time;
	
	public long getLesson_id() {
		return lesson_id;
	}
	public void setLesson_id(long lesson_id) {
		this.lesson_id = lesson_id;
	}
	public long getCourse_id() {
		return course_id;
	}
	public void setCourse_id(long course_id) {
		this.course_id = course_id;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(String time) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			Date date = dateFormat.parse(time);
			this.time = new Timestamp(date.getTime());
		} catch (ParseException e) {
			this.time = new Timestamp(0);
		}
	}
}
