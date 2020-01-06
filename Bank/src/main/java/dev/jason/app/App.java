package dev.jason.app;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.crypto.ExemptionMechanismException;

import dev.jason.enities.Account;
import dev.jason.enities.User;
import dev.jason.services.UserService;
import dev.jason.services.UserServiceImpl;

public class App {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		UserService us = new UserServiceImpl();
		boolean isRunning = true;
		int result = -1;
		//User loggedUser = null;
		System.out.println("Welcome to L.R.Jenkins Bank\n");
		// Main program loop
		do {

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

			switch (result) {
			case 1: // Create user account
				result = -1;
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
							System.out.println("You must enter something.");
					} catch (NoSuchElementException e) {
						scan.nextLine();
						System.out.println("You must enter something with 5 or more characters.");
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
				result = -1;
				// Getting superuser status
				do {
					try {
						System.out.println("Master, are you a Super User?\n1) yes\n2) no\n");
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
				us.createUser(user);
				break;
			case 2: // Login
				// Getting username
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

				// Getting new password
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
				User loggedUser = new User(username, password, false);
				loggedUser.setUsername(username);
				loggedUser.setPassword(password);
				loggedUser = us.login(loggedUser);
				System.out.println(loggedUser.getUsername()); // null ?
				if (!loggedUser.isloggedin())
					System.out.println("Username and/or password is incorrect.");

				// Logged in do stuff
				do { // hehe... dodo
					do {
						try {
							System.out.println("Welcome. What would you like to do with your accounts?");
							System.out.println("1) Create Account\n2) Close Account\n3) Access Account\n4) Return");
							result = scan.nextInt();
							scan.nextLine();
							if (result >= 1 && result <= 4)
								break;
							else
								System.out.println("That is not a valid input.");
						} catch (InputMismatchException e) {
							result = -1;
							scan.nextLine();
							System.out.println("That is not a valid input.");
						}
					} while (true);

					switch (result) {
					case 1: // Create Account
						do {
							try {
								// TODO:: Check for superuser access for foreign account access
								System.out.println("What is the accounts new name?");
								String accountname = scan.nextLine();
								System.out.println("How much are you depositing?");
								float accountbalance = scan.nextFloat();
								scan.nextLine();
								if (accountbalance > 0.0f) {
									Account account = new Account(accountname, accountbalance, loggedUser.getId());
									us.createAccount(account);
									System.out.println(
											account.getName() + " for " + loggedUser.getUsername() + " has been created.");
									break;
								}
							} catch (InputMismatchException e) {
								scan.nextLine();
								System.out.println("That is not a valid input.");
							}

						} while (true);
						break;
					case 2: // Close Account
						break;
					case 3: // Access Account
						do {
							try {
						System.out.println("What account would you like to access?");
						us.printAccounts(loggedUser); // Not printing information
						System.out.println(loggedUser);
						break;
							} catch (Exception e) {
								
							}
						} while(true);
						break;
					case 4: // Return to last
						break;
					}
				} while (true);


			case 3: // End Program
				isRunning = false;
				System.out.println("Enjoy your day master. Ending program.");
				continue;
			}

			

		} while (isRunning);
		scan.close();
	}
}
