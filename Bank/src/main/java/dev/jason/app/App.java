package dev.jason.app;

import dev.jason.enities.User;
import dev.jason.services.UserService;
import dev.jason.services.UserServiceImpl;

public class App {

	public static void main(String[] args) {

		// Scanner scan = new Scanner(System.in);
		UserService us = new UserServiceImpl();
		boolean isRunning = true;
		boolean isLoggedin = false;
		int result = 0;
		User loggedin = null;
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
				loggedin = us.login();
				while (loggedin != null) {
					//isLoggedin = true;
						boolean isinaccountoptions = false;
						do {
							int accountresult = -1;
							if (loggedin.getIsSuperUser()) {
								result = us.superUserOptions();
								if (result == 1) {
									us.printUsers();
								}
								if (result == 2) {
									us.deleteUsers();
								}
								if (result == 3) {
									us.updateUser();
								} 
								if (result == 4){
									accountresult = us.accountOptions(loggedin);
								}
								if (result == 5) {
									System.out.println("One");
									us.logout(loggedin);
									loggedin = null;
								}
							} else
								accountresult = us.accountOptions(loggedin);
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
							case 4: // Exit account Options
								System.out.println("Two");
								us.logout(loggedin);
								loggedin = null;
								isinaccountoptions = false;
								break;
							}
							//if (accountresult == 4) break;
						} while (isinaccountoptions);
				}
				result = 0;
				break;
			case 3: // End Program
				isRunning = false;
				System.out.println("Enjoy your day. Ending program.");
				continue;
			case 4: // Log out
				System.out.println("Three");
				us.logout(loggedin);
				loggedin = null;
				break;
			}

		} while (isRunning);
		us.close();
	}
}
