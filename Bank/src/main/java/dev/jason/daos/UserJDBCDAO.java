package dev.jason.daos;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import dev.jason.enities.User;
import dev.jason.utilities.ConnectionUtil;

public class UserJDBCDAO implements UserDAO{

	public User createUser(User user) {
		Connection conn = ConnectionUtil.getConnection();
		String sql = "INSERT INTO bank_user VALUES(0, ?, ?, ?, ?)";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			if(user.getIsSuperUser()) ps.setInt(3, 1);
			else ps.setInt(3, 0);
			if (user.isloggedin()) ps.setInt(4, 1);
			else ps.setInt(4, 0);
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int result = rs.getInt("bu_id");
			user.setId(result);
			//System.out.println(user);
			return user;
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("That username already exists. Please try another.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println("second: " + user);
		return null;
	}

	public User getUserByUsername(String username) {
		Connection conn = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM bank_user WHERE username=?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			rs.next();
			User user = new User();
			user.setId(rs.getInt("bu_id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			int result = rs.getInt("is_superuser");
			if(result == 1) user.setIsSuperUser(true);
			else user.setIsSuperUser(false);
			result = rs.getInt("is_loggedin");
			if(result == 1) user.setIsloggedin(true);
			else user.setIsloggedin(false);
			return user;
		} catch (SQLException e) {
			System.out.println("That username does not exist.");
		}
		return null;
	}

	public User getUserByID(int id) {
		Connection conn = ConnectionUtil.getConnection();
		String sql = "SELECT* FROM bank_user WHERE bu_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			User user = new User();
			user.setId(rs.getInt("bu_id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			int result = rs.getInt("is_superuser");
			if (result == 1) user.setIsSuperUser(true);
			else user.setIsSuperUser(false);
			result = rs.getInt("is_loggedin");
			if (result == 1) user.setIsloggedin(true);
			else user.setIsloggedin(false);
			return user;
			
		} catch (SQLException e) {
			System.out.println("There is no user with that ID.");
		}
		return null;
	}

	public User updateUser(User user) {
		Connection conn = ConnectionUtil.getConnection();
		String sql = "UPDATE bank_user SET username=?, password=?, is_superuser=?, is_loggedin=? WHERE bu_id=?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(5, user.getId()); // Getting the user that we need
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			if (user.getIsSuperUser()) ps.setInt(3, 1);
			else ps.setInt(3, 0);
			if(user.isloggedin()) ps.setInt(4, 1);
			else ps.setInt(4, 0);
			ps.execute();
			
			return user;
		} catch (SQLException e) {
			System.out.println("Was not able to find that user.");
		}
		return null;
	}

	public boolean deleteUser(User user) {
		Connection conn = ConnectionUtil.getConnection();
		String sql = "DELETE FROM bank_user WHERE bu_id=?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getId());
			ps.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("There was no user with that ID.");
		}
		return false;
	}

}
