package dev.jason.enities;

import java.util.HashSet;
import java.util.Set;

public class User {
	// Data Members
	private String username;
	private String password;
	private int userid;
	Set<Account> accounts = new HashSet<Account>();
	
	// CTORs
	public User(String username, String password, int userid, Set<Account> accounts) {
		super();
		this.setUsername(username);
		this.setPassword(password);
		this.setUserid(userid);
		this.setAccounts(accounts);
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

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", userid=" + userid + ", accounts=" + accounts
				+ "]";
	}
	
	
	
}
