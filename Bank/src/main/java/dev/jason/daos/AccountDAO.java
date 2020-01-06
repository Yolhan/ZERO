package dev.jason.daos;

import java.util.List;


import dev.jason.enities.Account;
import dev.jason.enities.User;

public interface AccountDAO {

	// Create
	public Account createAccount(Account account);
	
	// Read
	public Account getAccountByID(int id);
	public List<Account> getAccountByUserID(User user);
	
	// Update
	public Account updateAccount(Account account);
	
	// Delete
	public boolean deleteAccount(Account account);
}
