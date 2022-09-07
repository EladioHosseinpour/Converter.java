import java.util.Scanner;
import java.util.ArrayList;
public class Converter {
    private static Scanner input = new Scanner(System.in);
    public static void run(){
        while (true){
            System.out.println(
                    """
                    Select an option:
                    1. Text to binary
                    2. binary to text
                    3. Quit
                    
                    Enter choice:\s
                    """);
            int choice = input.nextInt();
            input.nextLine();
            if (choice == 1){
                textToBinary();
            }
            else if (choice == 2){
                binaryToText();
            }
            else if (choice == 3){
                System.exit(0);
            }
            else{
                System.out.println("You have not entered a valid option.");
            }
        }
    }

    private static void binaryToText() {
        System.out.println("Enter binary (in bytes): ");
        String binaryInput = input.nextLine();
        char[] binary = binaryInput.toCharArray();
        String text = "";
        String binaryString = "";

        int numSpaces = 0;
        for (int i = 0; i < binaryInput.length(); i += 1){
            if (binary[i] == ' '){
                numSpaces++;
            }
            else{
                binaryString += binary[i];
            }
        }
        int numChar = (binaryInput.length() - numSpaces)/8;
        binary = binaryString.toCharArray();

        ArrayList<Integer> asciiNums = new ArrayList<Integer>();
        int powerTwo = 128;
        int asciiCount = 0;
        int timesCount = 0;
        String characterList = "abcdefghijklmnopqrstuvwxyz,.:;[]!?'()/{}ABCDEFGHIJKLMNOPQRSTUVWXYZ<>|#@$%& ";
        char[] characters = characterList.toCharArray();
        System.out.println(characterList);
        for (int x = 0; x < binaryString.length(); x++){
            timesCount += 1;

            if (binary[x] == '1'){
                asciiCount += powerTwo;
            }
            powerTwo /= 2;
            if (timesCount == 8){
                asciiNums.add(asciiCount);
                asciiCount = 0;
                timesCount = 0;
                powerTwo = 128;
            }
        }

        for (int x = 0; x < asciiNums.size(); x++){
            for (int i = 0; i < characterList.length(); i++){
                int asciiNum = characterList.charAt(i);
                if (asciiNums.get(x) == asciiNum){
                    text += characters[i];
                }
            }
        }
        System.out.println(text);
    }
    private static void textToBinary() {
        System.out.println("Enter text: ");
        String text = input.nextLine();
        int length = text.length();
        String result = "";

        for (int i = 0; i < length; i++){
            int num = 128;
            int asciiNum = text.charAt(i);
            for (int y = 0; y < 8; y++){
                if (asciiNum >= num){
                    result += "1";
                    asciiNum -= num;
                }
                else{
                    result += "0";
                }
                num /= 2;
            }
            result += " ";
        }

        System.out.println(result);
        //String.charAt() -- Use this
        //int ascii = String.charAt(i);
    }
}
