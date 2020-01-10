package dev.jason.enities;

import java.util.HashSet;
import java.util.Set;

public class User {
	// Data Members
	private String username;
	private String password;
	private int id;
	private boolean issuperuser = false;
	private boolean isloggedin = false;
	//Set<Account> accounts = new HashSet<Account>();
	
	// CTORs
	public User(){};
	public User(String username, String password, boolean issuperuser) {
		super();
		this.setUsername(username);
		this.setPassword(password);
		//this.setAccounts(accounts);
		this.issuperuser = issuperuser;
	}
	
	// Getter and Setters
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int userid) {
		this.id = userid;
	}
	public boolean getIsSuperUser() {
		return this.issuperuser;
	}

	public boolean isloggedin() {
		return isloggedin;
	}

	public void setIsloggedin(boolean isloggedin) {
		this.isloggedin = isloggedin;
	}
	public void setIsSuperUser(boolean issuperuser) {
		this.issuperuser = issuperuser;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", id=" + id + ", issuperuser=" + issuperuser
				+ ", isloggedin=" + isloggedin + "]";
	}




	
	
	
}
