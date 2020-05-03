package forms;

public class CompanyForm {
	private long company_id;
	private String name;
	private String address;
	
	public long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(long id) {
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

