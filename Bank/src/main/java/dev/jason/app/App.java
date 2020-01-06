package dev.jason.app;

import dev.jason.enities.User;
import dev.jason.services.UserService;
import dev.jason.services.UserServiceImpl;

public class App {

	public static void main(String[] args) {

		// Scanner scan = new Scanner(System.in);
		UserService us = new UserServiceImpl();
		boolean isRunning = true;
		int result = 0;
		// User loggedUser = null;
		System.out.println("Welcome to L.R.Jenkins Bank\n");
		// Main program loop
		do {

			switch (result) {
			case 0:
				result = us.initRequests();
				break;
			case 1: // Create User Account
				us.createUser();
				result = 0;
				break;
			case 2: // Login
				// Getting username
				User loggedin = us.login();
				if (loggedin != null) {
					boolean isinaccountoptions = true;
					do {
						int accountresult = us.accountOptions(loggedin);
						switch (accountresult) {
						case 1: // Create account
							us.createAccount(loggedin);
							break;
						case 2: // Close Account
							us.closeAccount(loggedin);
							break;
						case 3: // Access Account
							us.accessAccount(loggedin);
							result = 0;
							break;
						case 4: // Logout
							us.logout(loggedin);
							isinaccountoptions = false;
							break;
						}
					} while (isinaccountoptions);
				}
				result = 0;
				break;
			case 3: // End Program
				isRunning = false;
				System.out.println("Enjoy your day. Ending program.");
				continue;
			}

		} while (isRunning);
		us.close();
	}
}
