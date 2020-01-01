package dev.jason.enities;

public class Account {

	// Data Members
	private String accountname;
	private float accountbalance;
	private int accountuserid;
	private int accountid;
	final private boolean issuperuser;
	
	// CTOR
	public Account(String accountname, float accountbalance, int accountuserid, int accountid, boolean issuperuser) {
		this.setAccountname(accountname);
		this.setAccountbalance(accountbalance);
		this.setAccountuserid(accountuserid);
		this.setAccountid(accountid);
		this.issuperuser = issuperuser;
	}
	
	// Getter and Setters
	public String getAccountname() {
		return accountname;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	public float getAccountbalance() {
		return accountbalance;
	}
	public void setAccountbalance(float accountbalance) {
		if (accountbalance < 0) 
			this.accountbalance = 0.0f;
		else
			this.accountbalance = accountbalance;
	}
	public int getAccountuserid() {
		return accountuserid;
	}
	public void setAccountuserid(int accountuserid) {
		this.accountuserid = accountuserid;
	}
	public int getAccountid() {
		return accountid;
	}
	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}
	@Override
	public String toString() {
		return "Account [accountname=" + accountname + ", accountbalance=" + accountbalance + ", accountuserid="
				+ accountuserid + ", accountid=" + accountid + "]";
	}

	public boolean isSuperUser() {
		return issuperuser;
	}
	
}
