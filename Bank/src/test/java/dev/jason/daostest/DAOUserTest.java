package dev.jason.daostest;

import org.junit.Test;

import dev.jason.daos.UserDAO;
import dev.jason.daos.UserJDBCDAO;
import dev.jason.daos.UserLocalDAO;
import dev.jason.enities.User;
import junit.framework.Assert;

public class DAOUserTest {

	@Test
	public void createUserTest() {
		UserDAO useraccess = new UserJDBCDAO();

		User user = new User("DudeThe", "rightuspass", false);
		useraccess.createUser(user);
		User result = useraccess.getUserByUsername("DudeThe");
		// System.out.println("third: " + phylicia);
		Assert.assertEquals("DudeThe", result.getUsername());
		System.out.println("From Create User DAO Test: " + result);
	}

	@Test
	public void getUserBy() {
		UserDAO udao = new UserJDBCDAO();
		User user = new User("jcclair", "12345", false);
		udao.createUser(user);
		user = udao.getUserByUsername("jcclair");
		System.out.println(user);
		Assert.assertEquals("jcclair", user.getUsername());
		User newuser = udao.getUserByID(user.getId()); // he's the first so we know it will work
		System.out.println(newuser);
		Assert.assertEquals(newuser.getId(), user.getId()); // Should be the same
	}

	@Test
	public void updateUser() {
		UserDAO udao = new UserJDBCDAO();
		User wizard = new User("Yolhan", "vudomagic", true);
		User createdwizard = udao.createUser(wizard);
		createdwizard.setUsername("Yolhan the Wizard");
		udao.updateUser(createdwizard);
		Assert.assertEquals("Yolhan the Wizard", createdwizard.getUsername());
	}

	@Test
	public void deleteUserTest() {
		UserDAO udao = new UserJDBCDAO();
		User jason = new User("The Destroyed One", "54321", true);
		udao.createUser(jason);
		int result = udao.getUserByID(jason.getId()).getId();
		udao.deleteUser(jason);
		Assert.assertNull(udao.getUserByID(result));
		System.out.println("Should have gotten an error there is no user with that username. PASSED");

	}

}
