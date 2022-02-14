
import java.util.Scanner;

public class Crypt {

    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
       int ascii, value;

       String str = sc.nextLine();

       int i = sc.nextInt();
/*
       if (i>=0 && i <= 26){
           i %= 26;
       }*/


       String caesar = "";


       for (int j = 0; j < str.length(); j++, ascii=0, value=0) {
           value = str.charAt(j);
               if (value>=97 && value <= 122){
                   if ((value+i)>122){
                       ascii = value + i - 122 + 96;
                       caesar = caesar.concat(Character.toString((char)ascii));
                   }
                   else if ((value>=97 && value <= 122)){
                       ascii = value + i;
                       caesar = caesar.concat(Character.toString((char)ascii));
                   }
               }
               else if (value>=65 && value <= 90){
                   if ((value+i)>90){
                       ascii = value + i - 90 + 64;
                       caesar = caesar.concat(Character.toString((char)ascii));
                   }
                   else if (value>=65 && value <= 90){
                       ascii = value + i;
                       caesar = caesar.concat(Character.toString((char)ascii));
                   }
               }
               else if (value==32){
                   caesar = caesar.concat(" ");
                   //continue;
               }
               else {
                   caesar = caesar.concat(Character.toString(str.charAt(j)));
               }
        }

        System.out.print(caesar);


    }
}
