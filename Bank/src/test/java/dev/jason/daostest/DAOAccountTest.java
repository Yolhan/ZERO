package dev.jason.daostest;

import org.junit.Test;

import dev.jason.daos.AccountDAO;
import dev.jason.daos.AccountJDBCDAO;
import dev.jason.daos.UserDAO;
import dev.jason.daos.UserJDBCDAO;
import dev.jason.enities.Account;
import dev.jason.enities.User;
import junit.framework.Assert;

public class DAOAccountTest {
	
	@Test
	public void createAccountTest() {
		AccountDAO accountdao = new AccountJDBCDAO();
		UserDAO udao = new UserJDBCDAO();
		User user = new User("newtest", "asdfjkqwef", false);
		user = udao.createUser(user);
		Account account = new Account("Checking", 1.0f, 0);
		account.setUserid(user.getId());
		account = accountdao.createAccount(account); // update the database and updates the local id
		//System.out.println(account);
		Assert.assertEquals("Checking", accountdao.getAccountByID(account.getId()).getName());
		System.out.println("From test createAccountTest: " + account);
	}
	
	@Test
	public void getAccountByIDTest() {
		AccountDAO adao = new AccountJDBCDAO();
		Account checking = new Account();
		checking.setId(1);
		checking = adao.getAccountByID(checking.getId());
		Assert.assertEquals("Checking", checking.getName());
		System.out.println("Test from getAccountByID: " + checking);
	}
	
	@Test
	public void updateAccountTest() {
		AccountDAO adao = new AccountJDBCDAO();
		Account account = new Account("Savings", 500.0f, 1);
		account.setId(1);
		account = adao.updateAccount(account);
		Assert.assertEquals("Savings", account.getName());
		System.out.println("Test from updateAccountTest: " + account);
		
	}
	
	@Test
	public void closeAccountTest() {
		AccountDAO adao = new AccountJDBCDAO();
		Account account = new Account("Goodies", 0, 1);
		account.setId(8);
		boolean result = adao.closeAccount(account);
		Assert.assertEquals(true, result);
		System.out.println("Test from closeAccountTest: " + result);
	}


}
