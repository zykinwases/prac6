package forms;

public class CourseForm {
	private long course_id;
	private String name;
	private String duration;
	private String intense;
	private long prof_id;
	
	public long getCourse_id() {
		return course_id;
	}
	public void setCourse_id(long id) {
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
	public long getProf_id() {
		return prof_id;
	}
	public void setProf_id(long id) {
		this.prof_id = id;
	}
}