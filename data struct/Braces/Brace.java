package Lab12;

import java.util.Scanner;

public class Brace {


    public Brace(){



    }



    public static boolean verifyBraces(String exp){

        MyStack<String> stack = new MyStack<String>();

        char c = '0';
        char current = '1';
        //System.out.println("Exp: " + exp.length());
       for (int i = 0; i < exp.length() ; i++){
           c = exp.charAt(i);
           //System.out.println("i: "+i);
           //System.out.println("c: "+c);
           if (c=='{' || c=='[' || c=='(') {        //opening
               current = c;
               stack.push(Character.toString(c));

               //System.out.println("c: "+c);
           }
           else if (c=='}' || c==']' || c==')'){    //closing
               //System.out.println("ya ");
               //System.out.println("c: "+c);
               //System.out.println("Current: "+current);


               if (stack.top().equals("(") && Character.toString(c).equals(")")){
                   stack.pop();
               }
               else if (stack.top().equals("[") && Character.toString(c).equals("]")){
                   stack.pop();
               }
               else if (stack.top().equals("{") && Character.toString(c).equals("}")){
                   stack.pop();
               }



           }

       }

       if (stack.size()==0){
           return true;
       }
       else {
           return false;
       }



    }


    public static void main(String[] args) {

       Scanner sc = new Scanner(System.in);
       String str = sc.nextLine();
//
//        Brace brace = new Brace();
//        //System.out.print(brace.verifyBraces("(15-{6*[9*7])}-10"));
//        //System.out.println(brace.verifyBraces("1234"));
//        //System.out.print(brace.verifyBraces("(15-{6*[9*7])}-10"));
//        System.out.print(brace.verifyBraces("(3+9)*(5+(10/2)"));
//        //System.out.print(brace.verifyBraces("(3+9)*(5+(10/2))*{6-(8*[(9/3+1)-7])}"));

        MyStack<String> stack = new MyStack<String>();

        char c = '0';
        char current = '1';
        //System.out.println("Exp: " + exp.length());
        for (int i = 0; i < str.length() ; i++){
            c = str.charAt(i);
            //System.out.println("i: "+i);
            //System.out.println("c: "+c);
            if (c=='{' || c=='[' || c=='(') {        //opening
                current = c;
                stack.push(Character.toString(c));

                //System.out.println("c: "+c);
            }
            else if (c=='}' || c==']' || c==')'){    //closing
                //System.out.println("ya ");
                //System.out.println("c: "+c);
                //System.out.println("Current: "+current);


                if (stack.top().equals("(") && Character.toString(c).equals(")")){
                    stack.pop();
                }
                else if (stack.top().equals("[") && Character.toString(c).equals("]")){
                    stack.pop();
                }
                else if (stack.top().equals("{") && Character.toString(c).equals("}")){
                    stack.pop();
                }
                else {
                    System.out.println("false");
                }

            }

        }

        if (stack.size()==0){
            System.out.print(true);
        }
        else {
            System.out.print(false);
        }
    }
}
