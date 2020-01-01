package dev.jason.daostest;

import static org.junit.Assert.*;

import org.junit.Test;

import dev.jason.daos.UserLocalDAO;
import dev.jason.enities.User;
import junit.framework.Assert;

public class DAOTest {

	@Test
	public void createUserTest() {
		UserLocalDAO useraccess = new UserLocalDAO();
		
		User phylicia = new User("babygirl", "12345", 0, null);
		useraccess.createUser(phylicia);
		User result = useraccess.getUserByUsername("babygirl");
		
		Assert.assertEquals("babygirl", result.getUsername());
		System.out.println(result);
	}

}
