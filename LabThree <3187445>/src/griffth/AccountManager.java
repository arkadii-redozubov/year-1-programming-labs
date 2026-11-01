//Arkadii Redozubov
//3187445
package griffth;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

//TASK 3 - Objects and Files
public class AccountManager {
//	Make scanner, ArrayList and File static (can be accessed through the class)
	public static Scanner scanner = new Scanner(System.in);
	public static ArrayList<BankAccount>accounts= new ArrayList<BankAccount>();
	public static File savedAccounts = new File("savedAccounts.txt");
	
//	Main method (create an object of AccountManager, call the menu method)
	public static void main(String[]args) {
		AccountManager manager = new AccountManager();
		manager.menu();
		
		
	}
	
//	Creates an object of BankAccount class, adds it to an ArrayList
	public void addAccount(int number) {
		BankAccount newAccount = new BankAccount(number);
		accounts.add(newAccount);
	}
	
//	Saves changes by writing them in the savedAccounts file
	public void saveAccounts() throws IOException {
		try(PrintWriter writer = new PrintWriter(new FileWriter(savedAccounts))){ //Use PrintWriter, allows writing through a print function
			if(accounts.size() !=0) { //Check if an ArrayList is empty
				for(BankAccount account:accounts) {
					writer.printf("%d, ", account.getAccountNumber());
					writer.printf("%.2f\n", account.getBalance());
				}
			}
			
		}
	}
	
//	Loads all existing accounts from the savedAccounts file
	public void loadAccounts() throws FileNotFoundException, IOException {
		int number = 0;
		double balance = 0.0;
		String line;
		String[] arr = new String[2];
		try(BufferedReader reader = new BufferedReader(new FileReader(savedAccounts))){ // use BufferedReader, good for large amount of data
//			Reading from a file logic
			while((line = reader.readLine()) != null) { 
				arr = line.split(","); //split elements with a comma
				number = Integer.parseInt(arr[0].trim()); //parse the first element using a wrapper class Integer, trim() to remove spaces 
				balance = Double.parseDouble(arr[1].trim()); //parse the second element using a wrapper class Double
				BankAccount account = new BankAccount(number, balance); //create an object of BankAccount
				accounts.add(account); //Add an object to an ArrayList
			}
			
		}
	}
	
//	Menu method, asks for the account number, prints the menu when logged
//	allows creating an account, get balance, deposit, withdraw... handles exceptions
	public void menu() {
		int accountNumber;
		boolean isRunning = true;
//		If a savedAccount file exists - load data from it 
		if(savedAccounts.exists() && savedAccounts.length() !=0) {
			try {
				loadAccounts();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while(isRunning) {
		System.out.println("Enter the account number: ");
		accountNumber = intCheck(); //save the account number from the user, call method intCheck() 
		boolean accountExists = false;
		BankAccount userAccount = null;
//		Check if the entered account exists
		for(BankAccount account:accounts) {
			if(account.getAccountNumber() == accountNumber) {
				accountExists = true;
				userAccount = account;
				break;
			}
			}
//		If the account exists - provide access to the menu interface
		if(accountExists) {
			int accountMenuChoice;
			boolean accountMenuRunning = true;
			while(accountMenuRunning) {
//			Print the menu 
			System.out.println("\nGet account balance [1]");
			System.out.println("Deposit             [2]");
			System.out.println("Withdraw            [3]");
			System.out.println("Switch Account      [4]");
			System.out.println("Remove Account      [5]");
			System.out.println("Save Changes        [6]");
			System.out.println("Quit                [7]");
			while(true) {
				accountMenuChoice = intCheck();
//				If the chosen option does not exist - ask to try again
				if(!(accountMenuChoice >=1 && accountMenuChoice <=7)) {
					System.out.println("Enter a number from 1 to 7");
					scanner.nextLine();
					continue;
				}
				break;	
				}
//			Depending on the choice - execute the following case
			switch(accountMenuChoice) {
//			Show the account balance
			case 1 -> {
				System.out.println("Balance: " + userAccount.getBalance());
			}
//			Deposit the entered amount
			case 2 -> {
				double depositAmount;
				System.out.println("Enter the amount to deposit");
				while(true) {
					depositAmount = doubleCheck(); //call the doubleCheck method 
					break;	
					}
				userAccount.deposit(depositAmount);
			}
//			Withdraw the entered amount
			case 3 -> {
				System.out.println("Enter the amount to withdraw");
				double withdrawAmount = doubleCheck();
				userAccount.withdraw(withdrawAmount);
			}
//			Hide the menu, come back to asking for the account number
			case 4 ->{
				accountMenuRunning = false;
			}
//			Delete account 
			case 5 ->{
				String removeConfirmation;
//				Remove confirmation
				while(true) {
					System.out.println("Are you sure that you want to remove the account? (Y/N)");
					removeConfirmation = scanner.nextLine().toUpperCase().trim(); //Make the input case insensitive
//					Safety check
					if(removeConfirmation.equals("Y")) {
						System.out.println("Enter the account number to remove ");
						int numberConfirm = intCheck();
						if(accountNumber == numberConfirm) {
							accounts.remove(userAccount); //remove from an ArrayList
							System.out.println("Successfully removed!");
							accountMenuRunning = false; //hide the menu, come back to asking for the account number
						}
						else {
							System.out.println("The account number does not match");
						}
						break;
					}
					else if(removeConfirmation.equals("N")) {
						break;
					}
				}
			}
//			Save Changes
			case 6 ->{
				try {
					saveAccounts();
					System.out.println("Changes saved");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
//			Quit option, save changes before quitting 
			case 7 ->{
				try {
					saveAccounts();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				accountMenuRunning = false;
				isRunning = false;
			}
			}
			
			}
			
			
		}
//		If the account does not exist - ask if the user wants to create a new one
		else {
			System.out.println("Account with number "+accountNumber+" does not exist");
			String accountCreation;
			while(true) {
				System.out.println("Do you want to create a new account? (Y/N) ");
				accountCreation = scanner.nextLine().toUpperCase().trim();
				if(accountCreation.equals("Y")) {
					addAccount(accountNumber); //create an account
					System.out.println("Successfully created!");
				}
				else if(accountCreation.equals("N")){
					int continueOption;
//					Added an opportunity to quit without login to the account 
					while(true) {
						System.out.println("Login    [1] ");
						System.out.println("Quit     [2] ");
						if(!scanner.hasNextInt()) {
							System.out.println("Enter a number 1 or 2");
							scanner.nextLine();
							continue;
						}
						continueOption = scanner.nextInt();
						if(continueOption != 1 && continueOption !=2) {
							scanner.nextLine();
							continue;
						}
						scanner.nextLine();
						break;
						
						}
					if(continueOption == 2) {
						isRunning = false;
					}	
				}
				break;
			}
	
	
		}
		}
					
	}
	
//	The method checks if the input is an integer (Added to reduce code duplication)
	public int intCheck() {
		int number;
		while(true) {
			System.out.print("> ");
			if(!scanner.hasNextInt()) {
				System.out.println("Enter a number");
				scanner.nextLine();
				continue;
			}	
			number = scanner.nextInt();
			scanner.nextLine();
			break;	
			}
		return number;
	}
	
//	The method checks if the input is a double (Added to reduce code duplication)
	public double doubleCheck() {
		double number;
		while(true) {
			System.out.print("> ");
			if(!scanner.hasNextDouble()) {
				System.out.println("Enter a number");
				scanner.nextLine();
				continue;
			}	
			number = scanner.nextDouble();
			scanner.nextLine();
			break;	
			}
		return number;
	}
	

}
