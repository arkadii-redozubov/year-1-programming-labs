//Arkadii Redozubov
//3187445
package griffth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
//		TASK 1 - ArrayLists and Objects
//		(The program takes four circles' radiuses and colors from the user, 
//      calculates the area of each one and prints the circle with the largest area)
		ArrayList<Circle> circles = new ArrayList<Circle>(); //ArrayList of objects Circle
		double radius;
		String color;
//		Take user input for four circles (radius + color)
		for(int i = 1; i<=4; i++) {
			while(true) {
			System.out.printf("Enter the radius for the %d circle: ", i);
			try {
			radius = scanner.nextDouble();
			scanner.nextLine(); //clear the scanner input buffer
			break;
			}
			catch(Exception e) {
				System.err.println("Please, enter a number");
				scanner.nextLine();
			}
			}
			System.out.printf("Enter the color for the %d circle: ", i);
			color = scanner.nextLine();
			
			circles.add(new Circle(radius, color)); //Add a newly created circle to an ArrayList
			
		}
		Circle largestCircle = circles.get(0); //Take the first circle from an ArrayList
		double largestArea = largestCircle.area(); //Take the first circle's area
		double currentArea;
//		Print details of each circle, determine the largest one
		for(int i = 0; i<circles.size(); i++ ) {
			System.out.println(i+1 + " Circle:");
			color =  circles.get(i).getColor();
			currentArea = circles.get(i).area();
			System.out.println("Color: " + color);
			System.out.printf("Area: %.2f\n", currentArea);
//			The largest area check 
			if(largestArea < currentArea) {
				largestCircle=circles.get(i);
				largestArea = currentArea;
			}
		}
		System.out.printf("The largest area has the %d circle", circles.indexOf(largestCircle)+1); //print the largest circle (index + 1, since they start from 0)
		
		
//		TASK 2 - Files
		countFiles("/Users/arkady/eclipse-workspace/FIlesHandling");

	}
	
//	(The method finds the given path, counts the total number of files and folders in that directory
//	and writes this information + pathname and the date logged to a csv file)
	public static void countFiles(String path) {
		int fileTotal = 0, folderTotal = 0;
		File file = new File(path); //given path
		if(file.exists() && file.isDirectory()) { //check if the given path is valid and if it exists
			File[] arr = file.listFiles(); //write all files and folders that were found to a file array 
			//If the array isn't null, count the total number of files and folders in the path 
			if(arr != null) {
				for(File fileOrFolder:arr) {
					if(fileOrFolder.isFile()) {
						fileTotal++;
					}
					else if(fileOrFolder.isDirectory()){
						folderTotal++;
					}
				}
			}
		File csvFile = new File("directory.csv"); //file with results
		boolean isNewCsvFile = false;
//		Check if a csv file is a new file
		if(!csvFile.exists() || (csvFile.length() == 0)) { 
			isNewCsvFile = true;
		}
		try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile, true))){ //declare PrintWriter, true means append 
			String header = "PathName,Files,Folder,Logged\n";
			String dateLogged = new SimpleDateFormat("MM/dd/yyyy").format(new Date()); //save the date logged to a csv file
//			Depending on whether the file is new, write the columns names
			if(isNewCsvFile) {
				writer.printf("%s", header );
			}
//			Write path data to a csv file
			writer.printf("%s,", file.getAbsolutePath());
			writer.printf("%d,", fileTotal);
			writer.printf("%d,",folderTotal);
			writer.printf("%s\n", dateLogged);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else {
			System.out.println("\n" + path + " is not a directory");
		}
	}
		
	}


