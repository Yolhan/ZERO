package dev.jason.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dev.jason.enities.Account;
import dev.jason.enities.User;
import dev.jason.utilities.ConnectionUtil;

public class AccountJDBCDAO implements AccountDAO {

	public Account createAccount(Account account) {

		Connection conn = ConnectionUtil.getConnection();
		String sql = "INSERT INTO account VALUES(0, ?, ?, ?)";

		try {
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, account.getName());
			ps.setFloat(2, account.getBalance());
			ps.setInt(3, account.getUserid());
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			account.setId(rs.getInt("a_id"));
			return account;
		} catch (SQLException e) {
			System.out.println("Not able to find account.");
		}
		return null;
	}

	public Account getAccountByID(int id) {
		Connection conn = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM account WHERE a_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			rs.next();
			Account account = new Account();
			account.setId(rs.getInt("a_id"));
			account.setName(rs.getString("name"));
			account.setBalance(rs.getFloat("balance"));
			account.setUserid(rs.getInt("bu_id"));
			return account;
		} catch (SQLException e) {
			System.out.println("Was not able to find that account ID.");
		}
		return null;
	}

	public List<Account> getAccountsByUserID(User user) {
		Connection conn = ConnectionUtil.getConnection();
		String sql = "SELECT*FROM account WHERE bu_id=?";
		List<Account> accounts = new ArrayList<Account>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Account account = new Account();
				account.setId(rs.getInt("a_id"));
				account.setName(rs.getString("name"));
				account.setBalance(rs.getFloat("balance"));
				account.setUserid(rs.getInt("bu_id"));
				accounts.add(account);
			}
			return accounts;
		} catch (SQLException e) {
			System.out.println("User ID was not found in the database.");
		}
		return null;
	}

	public Account updateAccount(Account account) {
		Connection conn = ConnectionUtil.getConnection();
		String sql = "UPDATE account SET name=?, balance=?, bu_id=? WHERE a_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, account.getName());
			ps.setFloat(2, account.getBalance());
			ps.setInt(3, account.getUserid());
			ps.setInt(4, account.getId());
			ps.execute();
			return account;
		} catch (SQLException e) {
			System.out.println("Was not able to find account with that ID.");
		}
		return null;
	}

	public boolean closeAccount(Account account) {

		Connection conn = ConnectionUtil.getConnection();
		String sql = "DELETE FROM account WHERE a_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, account.getId());
			ps.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("Was not able to find that account with that ID.");
		}
		return false;
	}

	public List<Account> getAccounts() {
		Connection conn = ConnectionUtil.getConnection();
		String sql = "SELECT*FROM account";
		List<Account> accounts = new ArrayList<Account>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			//ps.setInt(1, user.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Account account = new Account();
				account.setId(rs.getInt("a_id"));
				account.setName(rs.getString("name"));
				account.setBalance(rs.getFloat("balance"));
				account.setUserid(rs.getInt("bu_id"));
				accounts.add(account);
			}
			return accounts;
		} catch (SQLException e) {
			System.out.println("Something gone done wrong in getAccounts AccountJDBCDAO.");
		}
		return null;
	}

}
