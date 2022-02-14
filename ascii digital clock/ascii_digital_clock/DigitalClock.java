import java.util.Scanner;
/**
 * @author Comeros, Raul Jr.
 * @since April 4, 2020
 * @date created: April 4, 2020 | 10:53PM
 * @version: 1.0 April 6, 2020
 */


public class DigitalClock {

    private static final int MAX_HEIGHT = 5;
    private static final String SPACE_BETWEEN_NUMBERS = "    ";

    private String time;
    private String[] dcTime;

    public DigitalClock(String time) {
        this.time = time;
        dcTime = new String[MAX_HEIGHT];
        convertTimeToDC();
    }

    public void convertTimeToDC(){
        for (int count = 0; count < time.length(); count++) {
            switch (time.charAt(count)){
                case '0':
                    add(NumbersLibrary.getZero());
                    break;
                case '1':
                    add(NumbersLibrary.getOne());
                    break;
                case '2':
                    add(NumbersLibrary.getTwo());
                    break;
                case '3':
                    add(NumbersLibrary.getThree());
                    break;
                case '4':
                    add(NumbersLibrary.getFour());
                    break;
                case '5':
                    add(NumbersLibrary.getFive());
                    break;
                case '6':
                    add(NumbersLibrary.getSix());
                    break;
                case '7':
                    add(NumbersLibrary.getSeven());
                    break;
                case '8':
                    add(NumbersLibrary.getEight());
                    break;
                case '9':
                    add(NumbersLibrary.getNine());
                    break;
                case ':':
                    add(getColon());
                    break;
                default:
                    throw new IllegalArgumentException("Time string must only contain numbers and ':'");
            }
        }
    }

    public static String[] parseNumber(String numStr){
        String[] parsedStr = new String[MAX_HEIGHT];
        String str = "";

        int currentRow = 0;
        int charCounter = 0;
        for ( ; charCounter < numStr.length(); charCounter++) {
            if(numStr.charAt(charCounter) == '\n'){
                str.concat("\n");
                parsedStr[currentRow] = str;
                currentRow++;
                str = "";
                continue;
            }
            str = str.concat(String.valueOf(numStr.charAt(charCounter)));
        }
        parsedStr[currentRow] = str;
        return parsedStr;
    }

    public static void displayParsedString(String[] str){
        for (int row = 0; row < str.length; row++) {
            System.out.println(str[row]);
        }
    }

    private void add(String str) {
        String[] parseNum = parseNumber(str);

        for (int row = 0; row < MAX_HEIGHT; row++) {

            if(isNull(dcTime[row])){
                dcTime[row] = parseNum[row] + SPACE_BETWEEN_NUMBERS;
                continue;
            }
            dcTime[row] = dcTime[row].concat(parseNum[row] + SPACE_BETWEEN_NUMBERS);
        }
    }

    public String getColon(){
        return
            "  \n" +
            "[]\n" +
            "  \n" +
            "[]\n" +
            "  ";
    }

    public boolean isNull(Object obj){
        if(obj == null){
            return true;
        }
        return false;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true){
            try {
                String input = "";

                System.out.println("\nExample: 12:32:12");
                System.out.print("Enter time to display: ");
                input = sc.nextLine().trim();
                DigitalClock dc = new DigitalClock(input);
                dc.displayParsedString(dc.dcTime);
                System.out.print("\n\nEnter 0 to quit...\nOtherwise to continue...\n| ");
                input = sc.nextLine();
                if (input.equals("0") || input.equals("0 ")){
                    break;
                }
            }
            catch (Exception e){
                System.out.println("\tInvalid input, please try again.\n");
            }
        }
    }
}