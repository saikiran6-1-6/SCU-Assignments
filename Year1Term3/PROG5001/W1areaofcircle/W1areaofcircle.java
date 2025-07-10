import java.util.Scanner;

/**
 * A Program to calculate area of circle.
 *
 * @author (Saikiran Reddy Anugula)
 * @version (10/06/2025)
 */
public class W1areaofcircle
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        //Get the radius from user input
        System.out.print("Input the radius of the circle you want to find the area for: ");
        double r = scanner.nextDouble();
        
        if ( r <= 0 ) {
            System.out.print("Enter the radius value which is not zero ");
        }
        
        if ( r > 0 ) {
        //calculate the area based on the radius input
        double area = 3.14 * r * r;
        //output the area of circle
        System.out.print("The area of circle is: " + area);        
        }


        
        scanner.close();
        
    }
}