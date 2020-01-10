package dev.jason.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dev.jason.enities.Account;
import dev.jason.enities.User;

public class AccountLocalDAO implements AccountDAO {

	public final static Map<Integer, Account> accountstable = new HashMap<Integer, Account>();
	private static int accountid = 1000;

	public Account createAccount(Account account) {
		account.setId(++accountid);
		accountstable.put(account.getId(), account);
		return accountstable.get(accountid);
	}

	public Account getAccountByID(int id) {
		try {
			Account account = accountstable.get(id);
			return account;
		} catch (NullPointerException e) {
			//System.out.println("There is no account with that ID.");
		}
		//System.out.println("getAccountByID: That account ID does not exist.");
		return null;
	}

	public Account updateAccount(Account account) {
		return accountstable.put(account.getId(), account);
	}

	public boolean closeAccount(Account account) {
		if (accountstable.get(account.getId()).getBalance() == 0.0f) {
			if (accountstable.remove(account.getId()) != null) 
				return true;
			
			else {
				return false;
			}
		}
		return false;
	}

	public List<Account> getAccountsByUserID(User user) {
		List<Account> worker = new ArrayList<Account>(accountstable.values());
		int size = worker.size();
		for (int i = 0; i < size; i++) {
			worker.get(i);
			if (worker.get(i).getUserid() != user.getId()) {
				worker.remove(i);
				i--;
				size = worker.size();
			}
		}
		return worker;
	}

}
