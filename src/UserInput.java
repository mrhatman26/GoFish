import java.util.Scanner;

public class UserInput {
    static Scanner uInput = new Scanner(System.in);

    public static String getUserInput(String message){
        if (!message.equals("")) {
            System.out.print(message + ": ");
        }
        else{
            System.out.print("Input: ");
        }
        String input = Main.uInput.nextLine();
        return input;
    }

    public static void closeUserInput(){
        uInput.close();
    }
}
