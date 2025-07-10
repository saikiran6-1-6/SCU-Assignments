import java.util.Scanner;

/**
 * A Program to calculate area of triangle.
 *
 * @author (Saikiran Reddy Anugula)
 * @version (10/06/2025)
 */
public class W1areaoftriangle
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        //Get the base from user input
        System.out.print("Input the base of the triangle you want to find the area for: ");
        double base = scanner.nextDouble();
        
        //Get the height from user input
        System.out.print("Input the height of the triangle you want to find the area for: ");
        double height = scanner.nextDouble();
        
        if ( base <= 0 || height <= 0) {
            System.out.print("base and height of triangle cannot be zero  ");
        }
        
        if ( base > 0 || height > 0 ) {
        //calculate the area based on the base and height input
        double area = 0.5 * base * height;
        //output the area of triangle
        System.out.print("The area of triangle is: " + area);        
        }


        
        scanner.close();
        
    }
}