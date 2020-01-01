package dev.jason.daos;

import dev.jason.enities.User;

public interface UserDAO {

	// CRUD
	
	// Create
	public User createUser(User user);
	
	// Read
	public User getUserByUsername(String username);
	public User getUserByID(int id);
	
	// Update
	public User updateUser(User user);
	
	// Delete
	public boolean deleteUser(User user);
}
