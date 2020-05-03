package forms;

public class ProfessorForm {
	private long professor_id;
	private String login;
	private String pswd_hash;
	private String first_name;
	private String last_name;
	private long company_id;
	
	public long getProfessor_id() {
		return professor_id;
	}
	public void setProfessor_id(long professor_id) {
		this.professor_id = professor_id;
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
	public long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(long company_id) {
		this.company_id = company_id;
	}
}
