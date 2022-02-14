
import java.util.Scanner;

import static java.lang.Math.pow;

public class HexToDec12 {

    public static void main (String[] args){
        Scanner sc = new Scanner (System.in);

        String n;
        int count=0;
        int sum = 0;

        n = sc.nextLine();

        int count1 = n.length() - 1;
        for (count=0; count<n.length(); count++){
            if (n.charAt(count)>64){
                sum += (n.charAt(count) - 55) * (Math.pow(16, count1));
                count1--;
            }
            else
            {
                sum += (n.charAt(count) - 48) * (Math.pow(16, count1));
                count1--;
            }
        }

        System.out.println(sum);

    }





}
