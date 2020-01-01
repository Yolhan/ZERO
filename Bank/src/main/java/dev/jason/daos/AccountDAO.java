package dev.jason.daos;

import dev.jason.enities.Account;

public interface AccountDAO {

	// Create
	public Account createAccount(Account account);
	
	// Read
	public Account getAccountByID(int id);
	
	// Update
	public Account updateAccount(Account account);
	
	// Delete
	public boolean deleteAccount(Account account);
}
