package dev.jason.services;

import dev.jason.enities.Account;
import dev.jason.enities.User;

public interface UserService {

	public User createUser();
	public User login();
	public int accountOptions(User user);
	public User logout(User user);
	public Account getBalance(Account account);
	public void printAccountsByID(User user);
	public void printAccounts();
	public Account createAccount(User user);
	//public Account depositToAccount(Account account, float amount);
	public boolean closeAccount(User user);
	//public Account withdrawFromAccount(Account account, float amount);
	public void accessAccount(User user);
	public int initRequests();
	public void close();
}
