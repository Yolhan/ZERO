package dev.jason.services;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import dev.jason.daos.AccountDAO;
import dev.jason.daos.AccountJDBCDAO;
import dev.jason.daos.UserDAO;
import dev.jason.daos.UserJDBCDAO;
import dev.jason.enities.Account;
import dev.jason.enities.User;

public class UserServiceImpl implements UserService {

	private UserDAO udao = new UserJDBCDAO();
	private AccountDAO adao = new AccountJDBCDAO();
	Scanner scan = new Scanner(System.in);
	// User loggedinuser;

	public User createUser() {

		String username;
		String password;
		boolean issuperuser = false;

		// Getting username
		do {
			try {
				System.out.println("What would you like your username to be?");
				username = scan.nextLine();
				if (username.length() >= 5)
					break;
				else
					System.out.println("You must enter something with 5 or more characters.");
			} catch (NoSuchElementException e) {
				scan.nextLine();
				System.out.println("You must enter something.");
			}
		} while (true);

		// Getting new password
		do {
			try {
				System.out.println("What would you like your password to be?");
				password = scan.nextLine();
				if (password.length() >= 8)
					break;
				else
					System.out.println("You must enter something with 8 or more characters.");
			} catch (NoSuchElementException e) {
				scan.nextLine();
				System.out.println("You must enter something.");
			}
		} while (true);
		int result = -1;
		// Getting superuser status
		do {
			try {
				System.out.println("Are you a Super User?\n1) yes\n2) no\n");
				result = scan.nextInt();
				if (result > 0 && result <= 2) {
					switch (result) {
					case 1:
						issuperuser = true;
						break;
					case 2:
						issuperuser = false;
						break;
					}
					break;
				}
			} catch (InputMismatchException e) {
				scan.nextLine();
				System.out.println("That is not a valid option.");
			} catch (NoSuchElementException e) {
				scan.nextLine();
				System.out.println("That is not a valid option.");
			}
		} while (true);
		User user = new User(username, password, issuperuser);

		user = udao.createUser(user); // updates ID
		System.out.println("User " + user.getUsername() + " has been created.");
		return udao.getUserByID(user.getId());
	}

	public User login() {
		String username;
		String password;

		do {
			try {
				System.out.println("Username: ");
				username = scan.nextLine();
				break;
			} catch (NoSuchElementException e) {
				scan.nextLine();
				System.out.println("You must enter something with 5 or more characters.");
			}
		} while (true);

		// Getting password
		do {
			try {
				System.out.println("Password: ");
				password = scan.nextLine();
				break;
			} catch (NoSuchElementException e) {
				scan.nextLine();
				System.out.println("You must enter something.");
			}
		} while (true);

		User logininfo = new User(username, password, false);
		User pulleduser = udao.getUserByUsername(logininfo.getUsername()); // gets info
		// user = udao.getUserByID(logininfo.getId());
		if (pulleduser != null && pulleduser.getUsername().equals(logininfo.getUsername())
				&& pulleduser.getPassword().equals(logininfo.getPassword())) {
			// Good login
			pulleduser.setIsloggedin(true);
			pulleduser = udao.updateUser(pulleduser);
			System.out.println("User " + pulleduser.getUsername() + " has logged in.");
			return pulleduser;
		}
		System.out.println("The username and / or password is incorrect.");
		return null;

	}

	public User logout(User user) {
		User temp = udao.getUserByID(user.getId());
		if (temp != null && temp.isloggedin()) {
			user.setIsloggedin(false);
			user = udao.updateUser(user);
			System.out.println("User " + user.getUsername() + " has logged out.");
			return user;
		}
		System.out.println("User " + user.getUsername() + " is not logged in.");
		return null;
	}

	public Account getBalance(Account account) {
		Account temp = adao.getAccountByID(account.getId());
		if (temp != null)
			return temp;
		return null;
	}

	public Account createAccount(User user) {
		do {
			try {
				System.out.println("What is the accounts new name?");
				String accountname = scan.nextLine();
				System.out.println("How much are you depositing?");
				float accountbalance = scan.nextFloat();
				scan.nextLine();
				if (accountbalance > 0.0f) {
					Account account = new Account(accountname, accountbalance, user.getId());
					account = adao.createAccount(account); // Update account ID
					System.out.println(account.getName() + " for " + user.getUsername() + " has been created.");
					return adao.getAccountByID(account.getId());
				} else
					System.out.println("You must enter a positive number.");
			} catch (InputMismatchException e) {
				scan.nextLine();
				System.out.println("That is not a valid input.");
			}
		} while (true);
	}

	private Account depositToAccount(Account account, float amount) {
		if (amount > 0) {
			account.setBalance(account.getBalance() + amount);
			account = adao.updateAccount(account);
			System.out.println("Your new balance is " + account.getBalance());
		} else
			System.out.println("Transaction cancelled. You must deposit a positive amount.");
		return adao.getAccountByID(account.getId());
	}

	public boolean closeAccount(User user) {
		List<Account> accounts = null;
		if (user.getIsSuperUser())
			accounts = new ArrayList<Account>(adao.getAccounts());
		else
			accounts = new ArrayList<Account>(adao.getAccountsByUserID(user));
		if (accounts.size() == 0) {
			System.out.println("There are not accounts to close.");
			return false;
		}

		do {
			try {
				System.out.println("Which account would you like to close?");
				for (int i = 0; i < accounts.size(); i++) {
					System.out.println((i + 1) + ") " + accounts.get(i));
				}
				int result = scan.nextInt();
				scan.nextLine();
				if (result > 0 && result <= accounts.size()) {
					// Remove account
					Account account = accounts.get(result - 1);
					if (!adao.closeAccount(account)) {
						System.out.println("You must empty the account first");
						break;
					} else {
						System.out.println("Account " + account.getName() + " has been closed.");
						return true;
					}
				}
			} catch (InputMismatchException e) {
				scan.nextLine();
				System.out.println("That is not a valid input.");
			}
		} while (true);
		return false;
	}

	private Account withdrawFromAccount(Account account, float amount) {
		float balance = adao.getAccountByID(account.getId()).getBalance();
		if (amount <= balance) {
			account.setBalance(account.getBalance() - amount);
			account = adao.updateAccount(account);
			System.out.println("Your new balance is " + account.getBalance());
		} else
			System.out.println("You cannot withdraw more than what you have.");
		return adao.getAccountByID(account.getId());
	}

	public void printAccountsByID(User user) {
		List<Account> accounts = new ArrayList<Account>(adao.getAccountsByUserID(user));
		for (Account account : accounts) {
			System.out.println(account);
		}

	}

	public int initRequests() {
		int result = -1;
		do { // hehe.. It says dodo. That makes me happy =)
			try {
				System.out.println("What would you like to do?\n");
				System.out.println("1) Create User Account");
				System.out.println("2) Login");
				System.out.println("3) End Program");

				result = scan.nextInt();
				scan.nextLine();
				if (result > 0 && result <= 3)
					break;
				else
					System.out.println("That is not a valid option. Try again.");
			} catch (InputMismatchException e) {
				scan.nextLine();
				System.out.println("That is not a valid option. Try again.");
				result = -1;
			}
		} while (true);
		return result;
	}

	public int accountOptions(User user) {
		do {
			try {
				System.out.println("Welcome. What would you like to do with your accounts?");
				System.out.println("1) Create Account\n2) Close Account\n3) Account Transaction\n4) Logout");
				int result = scan.nextInt();
				scan.nextLine();
				if (result >= 1 && result <= 4)
					return result;
				else
					System.out.println("That is not a valid input.");
			} catch (InputMismatchException e) {
				scan.nextLine();
				System.out.println("That is not a valid input.");
			}
		} while (true);

	}

	public void accessAccount(User user) {
		List<Account> accounts = null;
		if (user.getIsSuperUser())
			accounts = new ArrayList<Account>(adao.getAccounts());
		else
			accounts = new ArrayList<Account>(adao.getAccountsByUserID(user));
		Account account = null;
		do {

			for (int i = 0; i < accounts.size(); i++) {
				System.out.println((i + 1) + ") " + accounts.get(i));
			}
			if (accounts.size() == 0) {
				System.out.println("\n\nThere are no accounts for this user.\n\n");
				return;
			}
			System.out.println("Would you like to access?");
			try {
				int result = scan.nextInt();
				scan.nextLine();
				if (result > 0 && result <= accounts.size() && accounts.size() > 0) {
					account = accounts.get(result - 1);
					break;
				}
			} catch (InputMismatchException e) {
				scan.nextLine();
				System.out.println("That is not a valid option.");
			}
		} while (true);

		do {
			System.out.println("Would you like to \n1) Withdraw\n2) Deposit\n3) Return");
			try {
				int result = scan.nextInt();
				if (result > 0 && result <= 3) {
					switch (result) {
					case 1:
						do {
							System.out.println("How much would you like to withdraw?");
							try {
								float amount = scan.nextFloat();
								scan.nextLine();
								account = this.withdrawFromAccount(account, amount);
								System.out.println("Your balance is " + account.getBalance());
								break;
							} catch (InputMismatchException e) {
								scan.nextLine();
								System.out.println("That is not a valid input.");
							}
						} while (true);
						break;
					case 2:
						do {
							System.out.println("How much would you like to deposit?");
							try {
								float amount = scan.nextFloat();
								scan.nextLine();
								account = this.depositToAccount(account, amount);
								System.out.println("Your balance is " + account.getBalance());
								break;
							} catch (InputMismatchException e) {
								scan.nextLine();
								System.out.println("That is not a valid input.");
							}
						} while (true);
						break;
					case 3:
						return;
					}
				}
			} catch (InputMismatchException e) {
				scan.nextLine();
				System.out.println("That is not a valid input.");
			}

		} while (true);

	}

	public void close() {
		scan.close();
	}

	public void printAccounts() {
		// TODO:: Need to make this method in JDBC sor super user
		List<Account> accounts = new ArrayList<Account>(adao.getAccounts());
		for (Account account : accounts) {
			System.out.println(account);
		}

	}

	public int superUserOptions() {
		do {
			System.out.println(
					"Would you like to:\n1)View Users\n2)Delete User Account\n3)Update User Account\n4)Access Banking Accounts\n5)Logout");
			try {
				int result = scan.nextInt();
				scan.nextLine();
				if (result > 0 && result <= 5)
					return result;
				else
					System.out.println("That is not a valid answer. Please try again.");
			} catch (InputMismatchException e) {
				scan.nextLine();
				System.out.println("That is not a valid input. Please try again.");
			}
		} while (true);
	}

	public void deleteUsers() {
		List<User> users = new ArrayList<User>(udao.getUsers());

		do {
			for (int i = 0; i < users.size(); i++) {
				System.out.println((i + 1) + ") " + users.get(i).getUsername());
			}
			try {
				System.out.println("Which User would you like to delete?\n0)Return");
				int result = scan.nextInt();
				scan.nextLine();
				if (result >= 0 && result <= users.size()) {
					if (result == 0)
						return;
					User user = users.get(result - 1);
					// System.out.println(user);
					if (udao.deleteUser(user))
						System.out.println("User " + user.getUsername() + " has been removed.");
//					else
//						System.out.println("There was an issue removing that user.");
					return;
				} else {
					System.out.println("That is not a valid response. Please try again.");
				}

			} catch (InputMismatchException e) {
				scan.nextLine();
				System.out.println("That is not a valid input.");
			}
		} while (true);

	}

	public void updateUser() {
		List<User> users = new ArrayList<User>(udao.getUsers());
		do {
			System.out.println("What user would you like to update?");
			for (int i = 0; i < users.size(); i++) {
				System.out.println((i+1) + ")" + users.get(i).getUsername());
			}
			try {
				int result = scan.nextInt();
				scan.nextLine();
				if (result > 0 && result < users.size()) {

					String username;
					String password;
					boolean issuperuser = false;

					// Getting username
					do {
						try {
							System.out.println("What would you like your username to be?");
							username = scan.nextLine();
							if (username.length() >= 5)
								break;
							else
								System.out.println("You must enter something with 5 or more characters.");
						} catch (NoSuchElementException e) {
							scan.nextLine();
							System.out.println("You must enter something.");
						}
					} while (true);

					// Getting new password
					do {
						try {
							System.out.println("What would you like your password to be?");
							password = scan.nextLine();
							if (password.length() >= 8)
								break;
							else
								System.out.println("You must enter something with 8 or more characters.");
						} catch (NoSuchElementException e) {
							scan.nextLine();
							System.out.println("You must enter something.");
						}
					} while (true);
					int result2 = -1;
					// Getting superuser status
					do {
						try {
							System.out.println("Are you a Super User?\n1) yes\n2) no\n");
							result2 = scan.nextInt();
							if (result > 0 && result2 <= 2) {
								switch (result2) {
								case 1:
									issuperuser = true;
									break;
								case 2:
									issuperuser = false;
									break;
								}
								break;
							}
						} catch (InputMismatchException e) {
							scan.nextLine();
							System.out.println("That is not a valid option.");
						} catch (NoSuchElementException e) {
							scan.nextLine();
							System.out.println("That is not a valid option.");
						}
					} while (true);
					User user = new User(username, password, issuperuser);
					user.setId(users.get(result-1).getId());
					System.out.println(user);
					user = udao.updateUser(user);
					break;
				}
			} catch (InputMismatchException e) {
				scan.nextLine();
				System.out.println("That is not a valid input. Please try again.");
			}
		} while (true);

	}

	public void printUsers() {
		List<User> users = new ArrayList<User>(udao.getUsers());
		for (User user : users) {
			System.out.println(user);
		}
		
	}

}
