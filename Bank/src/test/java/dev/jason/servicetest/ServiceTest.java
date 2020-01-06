package dev.jason.servicetest;

import org.junit.Assert;
import org.junit.Test;

import dev.jason.enities.Account;
import dev.jason.enities.User;
import dev.jason.services.UserService;
import dev.jason.services.UserServiceImpl;

public class ServiceTest {

	@Test
	public void createUserTest() {
		UserService usi = new UserServiceImpl();
		User jason = new User("jcclair", "12345", true);
		User temp = usi.createUser(jason);
		Assert.assertNotNull(temp);
		System.out.println("Service Test from createUser: " + temp);
	}

	@Test
	public void loginTest() {
		UserService usi = new UserServiceImpl();
		User jason = new User("jcclair", "12345", true);
		usi.createUser(jason);
		User temp = usi.login(jason);
		Assert.assertNotNull(temp);
		System.out.println("Service Test from login: " + temp);
	}
	
	@Test
	public void logoutTest() {
		UserService usi = new UserServiceImpl();
		User jason = new User("jcclair", "12345", true);
		usi.createUser(jason);
		usi.login(jason);
		User temp = usi.logout(jason);
		Assert.assertNotNull(temp);
		System.out.println("Service Test from logout: " + temp);
	}
		
	@Test
	public void createAccountTest() {
		UserService usi = new UserServiceImpl();
		Account checking = new Account("Checking", 100.0f, 0);
		Account temp = usi.createAccount(checking);
		Assert.assertNotNull(temp);
		System.out.println("Service Test from createAccount: " + temp);
	}
	
	@Test
	public void getBalanceTest() {
		UserService usi = new UserServiceImpl();
		Account checking = new Account("Checking", 100.0f, 0);
		usi.createAccount(checking);
		Account temp = usi.getBalance(checking);
		System.out.println("temp: " + temp.getBalance());
		System.out.println("checking: " + checking.getBalance());
		System.out.println("Service Test from createAccount: " + temp);
		//Assert.assertEquals(100.0f, temp.getBalance());
		
	}

	@Test
	public void depositToAccountTest() {
		UserService usi = new UserServiceImpl();
		Account checking = new Account("Checking", 100.0f, 0);
		usi.createAccount(checking);
		Account temp = usi.depositToAccount(checking, 20.0f);
		// Assert.assertEquals(120.0, temp.getBalance());
		System.out.println("Service Test from createAccount: " + temp);
	}
	
	@Test
	public void withdrawFromAccountTest() {
		UserService usi = new UserServiceImpl();
		Account checking = new Account("Checking", 100.0f, 0);
		usi.createAccount(checking);
		Account temp = usi.withdrawFromAccount(checking, 30.0f);
		//Assert.assertEquals(70.0f, temp.getBalance());
		System.out.println("Service Test from withdrawFromAccount: " + temp);
	}
	
	@Test
	public void deleteAccountTest() {
		UserService usi = new UserServiceImpl();
		Account checking = new Account("Checking", 100.0f, 0);
		usi.createAccount(checking);
		boolean result = usi.deleteAccount(checking);
		Assert.assertEquals(false, result); // There's money in there
		checking = usi.withdrawFromAccount(checking, 100.0f);
		result = usi.deleteAccount(checking);
		Assert.assertEquals(true, result);
		
	}

}
