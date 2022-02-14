package Lab09;

import java.util.Scanner;

public class StringRev {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String newStr = "";
        reverse(str, str.length()-1, newStr);


    }


    public static String reverse(String str, int i, String newStr){

        if (str.length()==0){
            System.out.println("");
            return newStr;
        }
        else if (i>=0 || newStr.length()!=str.length()){
            newStr += str.charAt(i);
            if(i==0){

                System.out.println(newStr);
            }

            reverse(str, i-1, newStr);
        }


        return newStr;
    }
}






//System.out.println("String: "+reverse(str, str.length()-1, newStr));
/*
                //System.out.println("----");
            //System.out.println("length1: "+newStr.length()+"| "+"length2: "+newStr.length());
            //System.out.println(newStr);
 */