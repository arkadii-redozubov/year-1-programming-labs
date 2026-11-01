//Arkadii Redozubov
//3187445
package griffth;

//TASK 1
public class Circle {
//	Private variables, access through getters and setters
	private double radius;
	private String color;
	
//	Constructor
	public Circle(double radius, String color){
		this.radius = radius;
		this.color = color;
	}
	
//	Getters and Setters for radius and color
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
//	Calculates the area of a circle
	public double area() {
		return Math.PI * Math.pow(radius, 2); //use Math class to access PI value and a power method
	}
	

}
