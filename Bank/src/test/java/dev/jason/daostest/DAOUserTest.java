package dev.jason.daostest;

import static org.junit.Assert.*;

import org.junit.Test;

import dev.jason.daos.AccountLocalDAO;
import dev.jason.daos.UserLocalDAO;
import dev.jason.enities.Account;
import dev.jason.enities.User;
import junit.framework.Assert;

public class DAOUserTest {

	@Test
	public void createUserTest() {
		UserLocalDAO useraccess = new UserLocalDAO();

		User phylicia = new User("babygirl", "12345", false);
		useraccess.createUser(phylicia);
		User result = useraccess.getUserByUsername("babygirl");
		Assert.assertEquals("babygirl", result.getUsername());
		System.out.println("From Create User DAO Test: " + result);
	}
	
	@Test
	public void getUserBy() {
		UserLocalDAO udao = new UserLocalDAO();
		User user = new User("jcclair", "12345", false);
		udao.createUser(user);
		User user1 = udao.getUserByID(user.getId()); // he's the first so we know it will work
		User user2 = udao.getUserByUsername("jcclair");
//		Assert.assertEquals("jcclair", user1.getUsername());;
//		Assert.assertEquals(1001, user2.getId());
	}
	
	@Test
	public void updateUser() {
		UserLocalDAO udao = new UserLocalDAO();
		User jason = new User("jcclair", "12345", true);
		udao.createUser(jason);
		jason.setUsername("Wizard");
		udao.updateUser(jason);
		Assert.assertEquals("Wizard", jason.getUsername());
	}
	
	@Test
	public void deleteUserTest() {
		UserLocalDAO udao = new UserLocalDAO();
		User jason = new User("jcclair", "12345", true);
		udao.createUser(jason);
		Assert.assertEquals("jcclair", udao.getUserByUsername("jcclair").getUsername());
		udao.deleteUser(jason);
		Assert.assertNull(udao.getUserByUsername("jcclair"));

	}

}
