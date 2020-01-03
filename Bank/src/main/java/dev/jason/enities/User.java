package dev.jason.enities;

import java.util.HashSet;
import java.util.Set;

public class User {
	// Data Members
	private String username;
	private String password;
	private int userid;
	private boolean issuperuser = false;
	Set<Account> accounts = new HashSet<Account>();
	
	// CTORs
	public User(String username, String password, Set<Account> accounts, boolean issuperuser) {
		super();
		this.setUsername(username);
		this.setPassword(password);
		this.setAccounts(accounts);
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
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public Set<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
	public boolean getIsSuperUser() {
		return this.issuperuser;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", userid=" + userid + ", accounts=" + accounts
				+ ", issuperuser=" + issuperuser + "]";
	}
	
	
	
}
