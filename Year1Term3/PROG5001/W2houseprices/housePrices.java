import java.util.Scanner;
import java.util.Arrays;

public class housePrices
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

        System.out.println("The house prices are: " + Arrays.toString(prices));
        
        for (int i=0; i<prices.length; i++){
            if (prices[i] > 500000)
            {
                System.out.println("The prices of houses which are above 500000 are: " + prices[i]);
            }
        }

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
        
        System.out.println("Average price of ten houses is : " + average);
        System.out.println("Cheapest house price: " + min);
        System.out.println("Most expensive house price: " + max);
        
        scanner.close();
    }
}