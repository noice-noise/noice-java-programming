package Lab10;

import java.util.Scanner;

public class Fibonacci {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.print(getFibonacci(sc.nextInt()));

    }


    public static int getFibonacci(int n) {


            if (n <= 1) {
                return 1;
            }
            else if (n == 2){
                return 1;
            }
            else  {
                return getFibonacci(n - 1) + getFibonacci(n - 2);
            }

    }
}
