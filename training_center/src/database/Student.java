package database;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="student")
public class Student implements Serializable{
	private static final long serialVersionUID = 6000656888692953836L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="student_id")
	private Long student_id;
	@Column(name="login")
	private String login;
	@Column(name="pswd_hash")
	private String pswd_hash;
	@Column(name="first_name")
	private String first_name;
	@Column(name="last_name")
	private String last_name;
	@Column(name="mobile")
	private String mobile;
	
	public Student() {}
	public Student(String login, String pswd_hash, String first_name, String last_name, String mobile) {
		this.login = login;
		this.pswd_hash = pswd_hash;
		this.first_name = first_name;
		this.last_name = last_name;
		this.mobile = mobile;
	}
	
	public Long getId() {
		return student_id;
	}
	public void setId(Long id) {
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
