package database;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="company")
public class Company implements Serializable{
	private static final long serialVersionUID = -3128915115233645441L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="company_id")
	private Long company_id;
	@Column(name="name")
	private String name;
	@Column(name="address")
	private String address;
	
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
}
