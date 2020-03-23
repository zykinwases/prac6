package database;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name="admin_acc")
public class Admin implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="admin_id")
	private Long admin_id; 
	@Column(name="login")
	private String login;
	@Column(name="pswd_hash")
	private String pswd_hash;
	@Column(name="last_name")
	private String last_name;
	@Column(name="first_name")
	private String first_name;
	
	public Admin() {}
	public Admin(String login, String pswd_hash, String last_name, String first_name) {
		this.login = login;
		this.pswd_hash = pswd_hash;
		this.last_name = last_name;
		this.first_name = first_name;
	}
	
	public Long getId() {
		return admin_id;
	}
	public void setId(Long id) {
		this.admin_id = id;
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
	
	 @Override
	 public boolean equals(Object o) {
		 if (this == o) return true;
		 if (o == null || getClass() != o.getClass()) return false;
		 Admin that = (Admin) o;
		 return Objects.equals(admin_id, that.admin_id) && Objects.equals(login, that.login) &&
				 Objects.equals(pswd_hash, that.pswd_hash) && Objects.equals(last_name, that.last_name) &&
				 Objects.equals(first_name, that.first_name);

	    }
	 
	 @Override
	 public int hashCode() {
		 return Objects.hash(admin_id, login, pswd_hash, last_name, first_name);
	 }
}