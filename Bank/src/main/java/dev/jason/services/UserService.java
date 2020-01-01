package dev.jason.services;

import dev.jason.enities.Account;
import dev.jason.enities.User;

public interface UserService {

	public User createUser(User user);
	public User login(User user);
	public boolean logout(User user);
	public float getBalance(Account account);
	public Account createAccount(Account account);
	public Account depositToAccount(Account account, float amount);
	public boolean deleteAccount(Account account);
	public float withdrawFromAccount(Account account, float amount);
}
