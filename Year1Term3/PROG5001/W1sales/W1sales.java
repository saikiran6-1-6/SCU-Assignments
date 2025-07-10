import java.util.Scanner;

/**
 * A Program to compute the average price of ten most recent sales
 * and find cheapest and most expensive one's among them.
 *
 * @author (Saikiran Reddy Anugula)
 * @version (10/06/2025)
 */
public class W1sales
{
    public static void main(String[] args) { 
        Scanner scanner = new Scanner(System.in);
        double[] prices = new double[10];
        double sum = 0;
        double min, max;
        
        for (int i = 0; i < 10; i++) {
            System.out.print("Input the prices of ten recent sales " + (i + 1) + ": ");
            prices[i] = scanner.nextDouble();
        }
        
        // setting min price to price of first sale and max price to price of first sale
        min = prices[0];
        max = prices[0];
        
        //calculating sum of prices of 10 recent sales along with min and max.
        for (int i = 0; i < 10; i++) {
            sum = sum + prices[i];
            if (prices[i] < min ) {
                min = prices[i];
            }
            
            if (prices[i] > max) {
                max = prices[i];
            }
        }
        
        double average = sum / 10;
        
        System.out.println("Average price of ten recent sales is : " + average);
        System.out.println("Cheapest house price: " + min);
        System.out.println("Most expensive house price: " + max);
        
        scanner.close();

    }
}