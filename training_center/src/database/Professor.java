package database;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="professor")
public class Professor implements Serializable {
	private static final long serialVersionUID = -4292995305758794393L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="professor_id")
	private Long professor_id;
	@Column(name="login")
	private String login;
	@Column(name="pswd_hash")
	private String pswd_hash;
	@Column(name="last_name")
	private String last_name;
	@Column(name="first_name")
	private String first_name;
	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company_id;
	
	public Professor() {}
	public Professor(String login, String pswd_hash, String last_name, String first_name, Company company_id) {
		this.login = login;
		this.pswd_hash = pswd_hash;
		this.last_name = last_name;
		this.first_name = first_name;
		this.company_id = company_id;
	}
 	
	public Long getProfessor_id() {
		return professor_id;
	}
	public void setProfessor_id(Long id) {
		this.professor_id = id;
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
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public Company getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Company company_id) {
		this.company_id = company_id;
	}
}
