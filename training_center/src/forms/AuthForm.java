package forms;

public class AuthForm {
	private String login;
	private String pswd_hash;
	private String role;
	
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
		pswd_hash = Encryption.encryptmd5(pswd_hash);
		this.pswd_hash = pswd_hash;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
