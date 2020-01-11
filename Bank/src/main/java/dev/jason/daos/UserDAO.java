package dev.jason.daos;

import java.util.List;

import dev.jason.enities.User;

public interface UserDAO {

	// CRUD
	
	// Create
	public User createUser(User user);
	
	// Read
	public User getUserByUsername(String username);
	public User getUserByID(int id);
	public List<User> getUsers();
	
	// Update
	public User updateUser(User user);
	
	// Delete
	public boolean deleteUser(User user);
}
