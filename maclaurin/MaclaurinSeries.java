
/**
 * @author Comeros, Raul Jr. BSPCE - 2
 * Numerical Methods
 * GROUP 4 - sin 2x
 */

import java.util.Scanner;

public class MaclaurinSeries {
    private int sig = 5;
    private double es;
    private double ea;

    public MaclaurinSeries(){
        this.es = 0.5 * Math.pow(10, 2-this.sig);
    }


    /**
     * Returns the factorial of the number n
     * @param n the variable where the factorial starts which will reduce by one per iteration
     * @return returns the sum of all the product when the variable n reaches 1 or 0
     */
    public double getFactorial(double n){
        if (n==1 || n==0){
            return 1;
        }
        return n * getFactorial(n-1);
    }

    /**
     *  The function runs recursively until Ea value is less than Es
     * @param n     the variable that iterates by 1 per step if Ea is not less than Es
     * @param x     the variable x used to use the general equation
     * @param fx    the function value of the previous step which will be used to solve for Ea
     * @return  returns the variable fx if ea is less than es, otherwise pass the newFx as parameter then recursively call the function
     */
    public double Sin2(int n, int x, double fx){
        double newFx = 0;

        // base case of the recursion
        if ((ea < es) && n != 0){
            System.out.println("\t\nFinal Approximation\n\tsin(" + (2*x) + "): " + fx);
            System.out.println("\tEa : " + ea);
            System.out.println("\tEs : " + es);
            return fx;
        }

        System.out.println("\nStep " + n);
        newFx = getSummation(0, n, x, newFx);
        System.out.println("sin(" + (2*x) + "): " + newFx);
        ea = Math.abs((newFx - fx) / newFx);
        if (n>0){
            System.out.println("Ea : " + ea);
        }

        return Sin2(n+1, x, newFx);
    }

    /**
     * Returns the summation from a general equation starting from i until N
     * @param i the variable that marks the start of the summation
     * @param N the variable that marks the end of the summation
     * @param x the variable x used in the general equation
     * @param sum the summation value that is passed when the value of i is not equal to N
     * @return the summation when the value if i is equal to N
     */
    public double getSummation (double i, double N, double x, double sum){
        //double temp;
        if (i == N){
            return sum;
        }

        //sin 2x
        sum += ((Math.pow(-1, i)) * Math.pow((2 * x), ((2 * i) + 1))) / getFactorial((2 * i + 1));

        //cos x
        //sum += (Math.pow(-1, i)) * (Math.pow(x, 2 * i)) / (getFactorial(2 * i));

        //cos2x
        //sum += (Math.pow(-1, i) * Math.pow(4, i) * Math.pow(x, 2 * i)) / getFactorial(2 * i);
        return getSummation(i+1 , N, x, sum);
    }

    /**
     * Asks the user for the value of x
     * @param question the String question
     * @return an integer for the value of x, otherwise return -1 indicating invalid input
     */
    public static int getInput (String question){
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print(question);
            int n = sc.nextInt();
            return n;
        } catch (Exception e){
            System.out.println("\tInput invalid, please try again.");
            return -1;
        }
    }

    public static boolean start(){
        while (true){
            int x = -1;
            while (x == -1){
                x = getInput("\tEnter value of x: ");
            }
            MaclaurinSeries ts = new MaclaurinSeries();
            double approx = ts.Sin2(0, x, 0);
            System.out.println("\n\nAPPROXIMATION: " + approx);

            int inp = getInput("\nPlease press 1 to CONTINUE with another x value, \notherwise press any integer to QUIT:\n\t");
            if (inp==1){
                continue;
            }
            else{
                return true;
            }
        }
    }
}
