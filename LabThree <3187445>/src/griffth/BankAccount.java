//Arkadii Redozubov
//3187445
package griffth;

// TASK 3
public class BankAccount {
	private int accountNumber;
	private double balance;
	
//	2 constructors, first - for an account creation, second - for loading data from the savedAccounts file
	public BankAccount(int accountNumber) {
		this.accountNumber = accountNumber;
		this.balance = 0;  //default value
	}
	public BankAccount(int accountNumber, double balance) {
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	public double getBalance() {
		return balance;
	}
	
//	Deposit method, amount to deposit check 
	public void deposit(double amount) {
		if(amount > 0) {
			balance+=amount;
			System.out.println("Successfully deposited!");
		}
		else {
			System.out.println("Invalid deposit amount");
		}
	}
	
//	Withdraw method, amount to withdraw check 
	public void withdraw(double amount) {
		if(amount >0 && amount <= balance) { //the withdraw amount can't be greater than the balance
			balance-=amount;
			System.out.println("Successfully withdrawed!");
		}
		else {
			System.out.println("Invalid withdraw amount");
		}
		
	}
	

}
