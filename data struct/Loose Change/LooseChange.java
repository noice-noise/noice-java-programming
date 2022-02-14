//package Lab03;

import java.util.Scanner;

public class LooseChange {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] denomination = new int[9];
        int noItems;
        int[] items;
        

        int count = 0;
        int cost = 0;
        int paid  = 0;
        int change = 0;
        int temp = 0;

        for (count=0; count<9; count++){
            denomination[count] = 0;
        }

        System.out.println();

    


        /*
        for (count=0; count<9; count++){
            System.out.println("Count: "+count);
        }*/

        System.out.print("Enter number of items: ");
        noItems = sc.nextInt();

        items = new int[noItems];

        for (count=0; count<noItems; count++){
            System.out.print("Enter item #" + (count+1) + ":");
            items[count] = sc.nextInt();
            cost += items[count];
        }

        for (count=0; count<noItems; count++){
            System.out.println(items[count]);
        }

        System.out.print("Enter amount tendered: ");
        paid = sc.nextInt();

        System.out.println("Cost: "+cost);
        System.out.println("Paid: "+paid);

        temp = paid - cost;

        if (temp>=1000){
            denomination[0] = temp/1000;
            temp = temp%1000;
        }

        System.out.printf ("temp: %d", temp);
        System.out.printf ("deno: %d", denomination[0]);

        if (temp>=500){
            denomination[1] = temp/500;
            temp = temp%500;
        }
//	printf ("temp: %d", temp);

        if (temp>=200){
            denomination[2] = temp/200;
            temp = temp%200;
        }
//	printf ("temp: %d", temp);


        if (temp>=100){
            denomination[3] = temp/100;
            temp = temp%100;
        }
//	printf ("temp: %d", temp);


        if (temp>=50){
            denomination[4] = temp/50;
            temp = temp%50;
        }
//	printf ("temp: %d", temp);


        if (temp>=20){
            denomination[5] = temp/20;
            temp = temp%20;
        }
//	printf ("temp: %d", temp);


        if (temp>=10){
            denomination[6] = temp/10;
            temp = temp%10;
        }
//	printf ("temp: %d", temp);

        if (temp>=5){
            denomination[7] = temp/5;
            temp = temp%5;
        }
//	printf ("temp: %d", temp);

        if (temp>=1){
            denomination[8] = temp/1;
            temp = temp%1;
        }
//	printf ("temp: %d", temp);
        System.out.println("1000 500 200 100 50 20 10 5 1");

        for (count=0; count<9; count++){
            System.out.print(denomination[count] + " ");
        }





    }
}
