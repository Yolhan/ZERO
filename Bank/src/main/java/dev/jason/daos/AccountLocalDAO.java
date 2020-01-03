package dev.jason.daos;

import java.util.HashMap;
import java.util.Map;

import dev.jason.enities.Account;

public class AccountLocalDAO implements AccountDAO {

	public final static Map<Integer, Account> accountstable = new HashMap<Integer, Account>();
	private static int accountid = 1000;

	public Account createAccount(Account account) {
		account.setAccountid(++accountid);
		return accountstable.put(account.getAccountid(), account);
	}

	public Account getAccountByID(int id) {
		try {
			Account account = accountstable.get(id);
			return account;
		} catch (NullPointerException e) {
			System.out.println("There is no account with that ID.");
		}
		System.out.println("getAccountByID: That account ID does not exist.");
		return null;
	}

	public Account updateAccount(Account account) {
		return accountstable.put(account.getAccountid(), account);
	}

	public boolean deleteAccount(Account account) {
		if (accountstable.get(account.getAccountid()).getAccountbalance() == 0.0f) {
			if (accountstable.remove(account.getAccountid()) != null)
				return true;
			else {
				System.out.println("Delete account: That account does not exist.");
				return false;
			}

		}
		System.out.println("Delete account: That account does not exist.");
		return false;
	}

}
