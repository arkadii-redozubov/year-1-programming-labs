//id: 3187445 name: Arkadii Redozubov
package griffith;
import java.util.Scanner;

public class LabTwo {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		//Exercise 4 
		//Menu System, works until the users wants to exit (uses while, switch, void method for menu) 
		boolean isRunning = true;
		printMenu();
		String option = scanner.nextLine();
		while (isRunning) {
		switch(option) {
		case "1" -> {
			// Exercise 1
			//Caesar Cipher. Encodes the user's word by shifting every letter to a number of places
			//Using the unicode's value of the letter, it adds the user's number to be shifted (when the unicode's value goes higher than Z or z, it continues from A or a) 
			//As a result, it prints out the encodes word, where every letter is shifted to a given number of places (other symbols stay the same)
			int position = 0;
			String input;
			while(true) {
				System.out.println("----------");
				System.out.println("Enter the amount of positions to shift");
				//Check if the position is a number, using try & catch to check the input
				try {
					position = scanner.nextInt();
					scanner.nextLine();
					break;
				}
				catch(Exception e) {
					System.out.println("Not a number");
					scanner.nextLine();
				}
			}
			//Check if the input is Blank
			 do{
				System.out.println("Enter a word to shift");
				input = scanner.nextLine();
			}while(input.isBlank());  
			 //Print and method call, insert arguments
			 System.out.println(caesarCipher(input, position));
			 //Appears in 3 cases (If the user enters "m"/"M", return to menu, if sth else - execute the same case)
			 System.out.println("Press m to return to menu or any other key to run the task again");
			 String newOption = scanner.nextLine().toLowerCase();
			 if(newOption.equals("m")) {
				 option = newOption;
			 }
		}
		case "2" -> {
			//Exercise 2
			//The program draws a triangle through the given symbol, using the user's start and end points 
			//Drawing is carried out using nested for loops, checks user's input for incorrect values 
			String pattern;
			int start, end;
			System.out.println("----------");
			//ask for a pattern until something is entered 
			do {
				System.out.print("Enter a pattern: ");
				pattern = scanner.nextLine().toLowerCase();
			}while(pattern.isBlank());
			
			//ask the user to enter the start and end amounts until int type is entered, using try & catch to check the input
			while(true) {
				try {
					System.out.print("Enter a start amount: ");
					start = scanner.nextInt();
					System.out.print("Enter an end amount: ");
					end = scanner.nextInt();
					System.out.println();
					break;
				}
				catch(Exception e) {
					System.out.println("Enter a number");
					scanner.nextLine();
				}
			}
			scanner.nextLine();
			//method call, insert arguments 
			printTriangle(pattern, start,end);
			System.out.println("Press m to return to menu or any other key to run the task again");
			String newOption = scanner.nextLine().toLowerCase();
			 if(newOption.equals("m")) {
				 option = newOption;
			}
		}
		case "3"-> {
			//Exercise 3
			//The program takes two words and returns if they are anagrams or not, uses nested for loops, boolean array for duplicates
			System.out.println("----------");
			System.out.println("Enter two words to see if they are anagrams");
			String first = scanner.nextLine();
			String second = scanner.nextLine();
			if(anagramCheck(first, second)) {
				System.out.println(first + " is an anagram of " + second);
			}
			else {
				System.out.println(first + " is not an anagram of " + second);
			}
			 System.out.println("Press m to return to menu or any other key to run the task again");
			 String newOption = scanner.nextLine().toLowerCase();
			 if(newOption.equals("m")) {
				 option = newOption;
			 }
		}
		//If user choice is "m", show the menu, get a choice 
		case "m"-> {
			printMenu();
			option = scanner.nextLine();
		}
		//If user choice is "4", exit the program
		case "4" -> {
			isRunning = false;
		}
		//If at the menu system user's choice is not 1 or 2 or 3 or 4, then show menu and ask for choice again
		default -> {
			System.out.println("Invalid option\n");
			printMenu();
			option = scanner.nextLine();
		}
		}
		}
		scanner.close();
	
		
}

	
	//caesarCipher method shifts every letter to the given number by adding the given value to the letter's unicode
	//Using StringBuilder to make a new word (appending new letters)
	public static String caesarCipher(String input, int position) {
		StringBuilder changedInput = new StringBuilder();
		char changedLetter = 0;
		//casting user's input to Char Array and looping through it
		for(char letter: input.toCharArray()) {
			//If the letter is UpperCase (65 = A, 90 = Z)
			if(letter >=65 && letter <=90 ) {
				//If the letter's unicode is greater than Z, continuing from A 
				if((char) (letter+position) > 90) {
					changedLetter = (char)(((letter + position) -91) + 65);
					changedInput.append(changedLetter);
				}
				//Shifts the letter to the given number
				else {
					changedLetter = (char) (letter + position);
					changedInput.append(changedLetter);
				}
			}
			//If the letter is LoweCase (97 = A, 122 = Z)
			else if(letter >=97 && letter <=122) {
				//If the letter's unicode is greater than z, continuing from a
				if((char) (letter+position) > 122) {
					changedLetter = (char)(((letter + position) - 123) + 97);
					changedInput.append(changedLetter);
				}
				//Shifts the letter to the given number
				else {
					changedLetter = (char) (letter + position);
					changedInput.append(changedLetter);
				}
			}
			//Just append if it is not a letter
			else {
				changedInput.append(letter);
			}
		}
		//cast StringBuilder to String and return 
		return changedInput.toString();
	}
	
	//Void print triangle method(does not return anything) : getting user's pattern, start and end amounts. 
	//iterating "end" amount of times (repeating a row "end" times) -> iterating inside the end loop to add spaces and to print out the amount of patterns 
	public static void printTriangle(String pattern, int start, int end) {
		//iterating through the first word
		for(int i = start; i<=end; i++) {
			//j<end because the last row should not have a space (according to the example)
			for(int j = i; j<end;j++) {
				System.out.print(" ");
			}
			// printing out a new pattern every time until it hits the end amount
			for(int f = 0; f<i; f++) {
				System.out.print(pattern);
			}
			//Add a new line, since we print spaces and patterns on the one line
			System.out.println();
		}
	}
	
	//anagramCheck method returns a boolean variable = true if two words are anagrams, and false if they are not
	//It modifies user's input, eliminating spaces, ignoring cases. The method compares words by their length, returns false if length are not equal
	//Otherwise it uses nested for loops to iterate though the first word, recording the letter and than comparing it to the letters of the second word, if the letter is not found, return false
	//It also has a boolean array for duplicates, which takes the second letter's length as a number of elements in the array (default values is false)
	//While iterating through the second word, it applies true to isAnagram variable if two conditions are true: the letter in the boolean array is false, and if it equals to the first word's letter	
	public static boolean anagramCheck(String first, String second) {
		boolean isAnagram = false;
		char letter;
		//Handles whitespace, different case
		String changedFirst = first.replace(" ", "").toLowerCase();
		String changedSecond = second.replace(" ", "").toLowerCase();
		//returns false straight away if length is different
		if(!(changedFirst.length() == changedSecond.length())) {
			return isAnagram;
		}
		//boolean array handles duplicates, default values is false (changedSecond.length = number of elements)
		boolean[] letterUsed = new boolean[changedSecond.length()];
		//Iterates through the first word
		for(int i = 0; i<changedFirst.length(); i++) {
			//assigns the letter
			letter = changedFirst.charAt(i);
			//reset isAnagram
			isAnagram = false;
			//Iterates through the second one
			for(int j = 0; j<changedSecond.length(); j++) {
				//If the letter has not been used yet and equals to the first word's letter
				if(!letterUsed[j] && changedSecond.charAt(j) == letter) {
					//changes the array's element to true 
					letterUsed[j] = true;
					isAnagram = true;
					break;
				}
			}
			//returns false if the matched letter has not been found
			if(!isAnagram) {
				return isAnagram;
				
			}
		}
		
		return isAnagram;
	}
	
	public static void printMenu() {
		System.out.println("Choose a task");
		System.out.println("1: Caesar Cipher\n2: Print Triangle\n3: Check Anagram\n4: Quit");
		System.out.print("Enter option: ");
	}
}



