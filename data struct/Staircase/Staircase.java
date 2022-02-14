

import java.util.Scanner;

public class Staircase {


    public static void main (String args[]){

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        if ( n > 0){
            display(n, 1);
        }

    }


    public static void display (int n, int i){


        if(n==1){
            System.out.print("#");
        }
        else if (i<=n){
            for(int j=0; j<i; j++){
                System.out.print("#");
            }
            System.out.print("\n");
            display(n, i+1);
        }





    }

}


/*


            for (int j=0; j<i; j++){
                System.out.print("# ");
            }
 */