package database;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="company")
public class Company implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="company_id")
	private Long company_id;
	@Column(name="name")
	private String name;
	@Column(name="address")
	private String address;
	@OneToMany(mappedBy="company_id", fetch=FetchType.EAGER)
	private Set<Professor> staff = new HashSet<Professor>();
	
	public Company() {}
	public Company(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	public Long getId() {
		return company_id;
	}
	public void setId(Long id) {
		this.company_id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Set<Professor> getStaff() {
		return staff;
	}
	public void setStaff(Set<Professor> staff) {
		this.staff = staff;
	}
}
