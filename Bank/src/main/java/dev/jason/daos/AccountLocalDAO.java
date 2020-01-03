package dev.jason.daos;

import java.util.HashMap;
import java.util.Map;

import dev.jason.enities.Account;

public class AccountLocalDAO implements AccountDAO {

	public final static Map<Integer, Account> accountstable= new HashMap<Integer, Account>();
	private int accountid = 1000;
	
	public Account createAccount(Account account) {
		account.setAccountid(++accountid);
		account.setAccountid(accountid);
		return accountstable.put(account.getAccountid(), account);
	}

	public Account getAccountByID(int id) {
		try {
		Account account = accountstable.get(id);
		return account;
		} catch (NullPointerException e) {
			System.out.println("There is no account with that ID.");
		}
		return null;
	}

	public Account updateAccount(Account account) {
		return accountstable.put(account.getAccountid(), account);
	}

	public boolean deleteAccount(Account account) {
		if (accountstable.remove(account.getAccountid()) != null)
			return true;
		else
			return false;
	}


}
