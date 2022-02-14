package Lab11;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class CountVowel {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        int j = 0;

        if (str.length()!=0){
            System.out.print(countVowels(0, 0, str));
        } else {
            System.out.print(j);
        }
    }




    public static int countVowels(int i, int j, String str){

        boolean vowel = false;
        char c = str.charAt(i);
        if(c==65 || c==69 || c==73 || c==79 || c==85 || c==97 || c==101 || c==105 || c==111 || c==117){
            vowel = true;
        }


        if(str==null || str=="" || str.length()==0){
            //System.out.println("yo");
            return 0;
        }
        else if (i==str.length()-1){
           if (vowel==true){
              // System.out.println("j: "+j);
               return j+1;
           }
           else{
               //System.out.println("----");
              // System.out.println("J: "+j);
               c = 0;
               return j;
           }
            //return j;
        }
        else if (vowel==true){
          //  System.out.println("j: "+j);
            return countVowels(i+1, j+1, str);
        }
        else if (i<str.length()){
          //  System.out.println("char: "+str.charAt(i));
            //countVowels(i+1, j, str);
            return countVowels(i+1, j, str);
        }
        else {

        }
        return j;
      //  System.out.println("j2: "+j);


    }
}
