package forms;

public class StudentForm {
	private long student_id;
	private String login;
	private String pswd_hash;
	private String first_name;
	private String last_name;
	private String mobile;
	
	public long getStudent_id() {
		return student_id;
	}
	public void setStudent_id(long id) {
		this.student_id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPswd_hash() {
		return pswd_hash;
	}
	public void setPswd_hash(String pswd_hash) {
		if (pswd_hash.length() <= 30) pswd_hash = Encryption.encryptmd5(pswd_hash);
		this.pswd_hash = pswd_hash;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}	
}
