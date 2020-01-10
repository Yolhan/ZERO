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
		//User jason = new User("jcclair", "12345", true);
		User temp = usi.createUser();
		Assert.assertNotNull(temp);
		System.out.println("Service Test from createUser: " + temp);
	}

	@Test
	public void loginTest() {
		UserService usi = new UserServiceImpl();
		User jason;// = new User();
		jason = usi.createUser();
		User temp = usi.login();
		Assert.assertNotNull(temp);
		Assert.assertEquals(true, temp.isloggedin());
		System.out.println("Service Test from login: " + temp);
	}
	
	@Test
	public void logoutTest() {
		UserService usi = new UserServiceImpl();
		User jason;// = new User("jcclair", "12345", true);
		jason = usi.createUser();
		jason = usi.login();
		User temp = usi.logout(jason);
		Assert.assertNotNull(temp);
		Assert.assertEquals(false, temp.isloggedin());
		System.out.println("Service Test from logout: " + temp);
	}
		
	@Test
	public void createAccountTest() {
		UserService usi = new UserServiceImpl();
		User jason = new User();
		jason = usi.createUser();
		jason = usi.login();
		Account checking = new Account("Checking", 100.0f, 0);
		checking = usi.createAccount(jason);
		Assert.assertNotNull(checking);
		System.out.println("Service Test from createAccount: " + checking);
	}
	
	@Test
	public void getBalanceTest() {
		UserService usi = new UserServiceImpl();
		User jason = new User();
		jason = usi.createUser();
		jason = usi.login();
		Account checking = new Account("Checking", 100.0f, 0);
		checking = usi.createAccount(jason);
		Account temp = usi.getBalance(checking);
		System.out.println("temp: " + temp.getBalance());
		System.out.println("checking: " + checking.getBalance());
		System.out.println("Service Test from createAccount: " + temp);
		//Assert.assertEquals(100.0f, temp.getBalance());
		
	}

//	@Test
//	public void depositToAccountTest() {
//		UserService usi = new UserServiceImpl();
//		User jason = new User("jcclair", "qwer1234", true);
//		jason = usi.login();
//		Account checking; // = new Account("Checking", 100.0f, 0);
//		checking = usi.createAccount(jason);
//		usi.accessAccount(jason);
//		// Assert.assertEquals(120.0, temp.getBalance());
//		System.out.println("Service Test from createAccount: " + temp);
//	}
//	
//	@Test
//	public void withdrawFromAccountTest() {
//		UserService usi = new UserServiceImpl();
//		Account checking = new Account("Checking", 100.0f, 0);
//		usi.createAccount(checking);
//		Account temp = usi.withdrawFromAccount(checking, 30.0f);
//		//Assert.assertEquals(70.0f, temp.getBalance());
//		System.out.println("Service Test from withdrawFromAccount: " + temp);
//	}
	
	@Test
	public void deleteAccountTest() {
		UserService usi = new UserServiceImpl();
		User jason = new User("jcclair", "qwer1234", true);
		//Account checking = new Account("Checking", 100.0f, 0);
		usi.createAccount(jason);
		boolean result = usi.closeAccount(jason);
		Assert.assertEquals(false, result); // There's money in there
		usi.accessAccount(jason);
		result = usi.closeAccount(jason);
		Assert.assertEquals(true, result);
		
	}

}
