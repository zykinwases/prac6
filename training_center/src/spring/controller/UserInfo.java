package spring.controller;

public class UserInfo {
	private static String role;
	private static long id;
	private static UserInfo instance = null;
	
	public UserInfo() {
		UserInfo.role = "";
		UserInfo.id = 0;
	}
	
	public static synchronized UserInfo getInstance() {
		if (instance == null) {
			instance = new UserInfo(); 
		}
		return instance;
	}
	
	public synchronized UserInfo getInstance(String role, long id) {
		if (UserInfo.id == 0) {
			UserInfo.role = role;
			UserInfo.id = id;
		}
		return instance;
	}
	
	public String getRole() {
		return role;
	}

	public long getId() {
		return id;
	}
	
	public void delete() {
		role = "";
		id = 0;
	}
}
