//id: 3187445 full name: Arkadii Redozubov
package griffith;
import java.util.Scanner;

public class LabOne {

	public static void main(String[] args) {
		
	    // Exercise 1 
     	//This program converts the amount of foot user entered to meters
		//Getting the user's data, converting foot to meter, printing out the result
		
		Scanner scanner = new Scanner(System.in); //creating a scanner object
		double foot, meters; //declaring required double variables
		final double RATE = 0.3048; //declaring a constant of rate
		System.out.println("Enter amount in foot"); 
		foot = scanner.nextDouble(); //getting user's foot number
		meters = foot * RATE; //converting feet to meters through multiplying foot by rate 
		System.out.printf("%.1fft is %.2fm\n\n", foot, meters); //printing the result, rounded to 2 decimal places
		 
		
		
	    //Exercise 2
		//This program calculates the minimum amount of paint tins needed to color the wall
		//getting the user's data, calculating the area, how many tins needed, printing out the rounded up result
		
		//declaring constants: one paint tin, coverage per liter
		final double PAINT_TIN = 2.5; 
		final int COVERAGE_ONE_LITRE = 13; 
		double height, width, area, tinsRequired; //declaring double variables for calculations, input
		System.out.print("Enter your walls width in meters ");
		width = scanner.nextDouble(); //getting width from user
		System.out.print("Enter your walls height in meters ");
		height = scanner.nextDouble(); //getting height from user
		area = width * height; //calculating the wall area
		System.out.println("The wall is " + area + "sqm.");
		//calculating the number of tins needed by dividing the area by coverage per liter and the size of paint tin
		tinsRequired = (area / COVERAGE_ONE_LITRE) / PAINT_TIN; 
		//printing out the result, rounding up and casting to an int 
		System.out.println("You will need " + (int)Math.ceil((tinsRequired)) + " tins of paint.\n");
		
		
		//Exercise 3
		//This program is a simple calculator (Addition, Subtraction, Division) between 2 numbers
		//getting the user's data, checking for command, doing addition, subtraction, division (checking if the second number = 0), printing out the result
		
		//declaring input values, operation variables 
		double numberOne, numberTwo;
		double addition, subtraction, division;
		String command;
		//printing the rules
		System.out.println("Commands: Add, Sub, Div");
		System.out.println("Enter Command and two numbers e.g (Add 1 4):");
		//getting a command (using next(), since it takes everything before the space)
		//using toUpperCase() to be case-insensitive
		command = scanner.next().toUpperCase(); 
		//getting user numbers
		numberOne = scanner.nextDouble();
		numberTwo = scanner.nextDouble();
		//using switch statement 
		switch(command) {
		case "ADD":
			addition = numberOne + numberTwo; // addition
			System.out.println("Result: " +addition+"\n"); //printing out the result
			break; //using break to exit the switch
		case "SUB":
			subtraction = numberOne - numberTwo; // subtraction
			System.out.println("Result: "+subtraction+"\n");
			break;
		case "DIV":
			//dividing by zero check
			if(numberTwo == 0) {
				System.out.println("Cannot divide by zero\n");
			}
			else {
				division = numberOne / numberTwo; //division
			    System.out.println("Result: "+division +"\n");
			}
			break;
		default:
			System.out.println("Incorrect command\n"); //printing out the default statement 
		}
		
		
		//Exercise 4
		//This program checks the user's temperature and prints out the result according to the temperature measurement table
		//Getting the user's data, according to the age (child or adult) printing out the general temperature diagnose (lower, normal, higher than average, or a fever)
		
		//declaring variables age and temperature
		int age;
		double temperature;
		//getting age and temperature from user
		System.out.print("Enter your age: ");
		age = scanner.nextInt();
		System.out.print("Enter your temperature in C°: ");
		temperature = scanner.nextDouble();
		//checking if the age is less than 0, since it can't be negative
		if(age<0) {
			System.out.println("Age can't be negative");
		}
		//checking if the user is a child (age should be <= to 12)
		else if(age <= 12) {
			if(temperature<=36.4) //checking if the temperature is lower or equals to 36.4°C
				System.out.println("Your temperature is lower than average");
			//checking if the temperature is in the range from 36.5°C to 37.5°C
			else if(temperature>=36.5 && temperature<=37.5) 
				System.out.println("You have a normal temperature");
			//checking if the temperature is in the range from 37.6°C to 38.4°C
			else if(temperature>=37.6 && temperature<=38.4)
				System.out.println("Your temperature is higher than average");
			//else to print "You have a Fever" if none of the above are true
			else
				System.out.println("You have a Fever");
		}
		//checking for adult (age should be > 12)
		else{
			if(temperature<=35.9) //checking if the temperature is lower or equals to 35.9°C
				System.out.println("Your temperature is lower than average");
			//checking if the temperature is in the range from 36.0°C to 37.0°C
			else if(temperature>=36.0 && temperature<=37.0)
				System.out.println("You have a normal temperature");
			//checking if the temperature is in the range from 37.1°C to 38.0°C
			else if(temperature>=37.1 && temperature<=38.0)
				System.out.println("Your temperature is higher than average");
			//else to print "You have a Fever" if none of the above are true
			else
				System.out.println("You have a Fever");
		}

		
		
		scanner.close(); //closing scanner at the end of the program
		
		

	}

}
