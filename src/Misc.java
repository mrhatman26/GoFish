import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
public class Misc {
    public static Boolean isNumber(String value){
        try{
            Integer.parseInt(value);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    public static Boolean checkInRange(int value, int min, int max, boolean checkMin, boolean checkMax){
        if (value < min && checkMin){
            return false;
        }
        else if (value > max && checkMax){
            return false;
        }
        else{
            return true;
        }
    }

    public static int getPlayerCount(){
        int playerCount;
        Boolean inputAccepted = false;
        while (true){
            System.out.println("How many players?");
            System.out.println("(Minimum is 1 and max is 10 if you want to stay sane)");
            //System.out.print("Player count: ");
            //String input = Main.uInput.nextLine();
            String input = UserInput.getUserInput("Player count");
            if (!isNumber(input)){
                System.out.println("\nInput must be a whole number!\n");
                inputAccepted = false;
                pauseSeconds(1);
            }
            else{
                playerCount = Integer.parseInt(input);
                if (!checkInRange(playerCount, 1, 0,true, false )){
                    System.out.println("\nInput must be greater than or equal to 1!\n");
                    inputAccepted = false;
                }
                else{
                    inputAccepted = true;
                    if (playerCount > 10){
                        inputAccepted = false;
                        System.out.println("\nWarning: You have asked for 11 or more players!");
                        pauseSeconds(2);
                        System.out.println("Things might get a little slow!");
                        pauseSeconds(2);
                        if (UserInput.getUserInputBoolean("Are you sure you want " + playerCount + " players? (Y/N)")){
                            inputAccepted = true;
                        }
                        else{
                            System.out.println("\nThen ask for 10 or less players you silly!");
                            UserInput.pauseForEnterKey();
                            System.out.print("\n");
                        }
                    }
                    if (inputAccepted) {
                        System.out.println("\n" + input + " players selected.");
//                        pauseSeconds(1);
                        UserInput.pauseForEnterKey();
                        System.out.print("\n");
                        return playerCount;
                    }
                }
            }
        }
    }
    public static void pauseSeconds(int seconds){
        try{
            TimeUnit.SECONDS.sleep(seconds);
        }
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    public static String arrayToString(String[] value, boolean addPlus, boolean removeEmpty){
        String arrayAsString = "";
        for (int i = 0; i < value.length; i++){
            if (arrayAsString.equals("")){
                if (removeEmpty){
                    if (!value[i].equals("")){
                        arrayAsString = value[i];
                    }
                }
                else{
                    arrayAsString = value[i];
                }
            }
            else{
                if (removeEmpty){
                    if (!value[i].equals("")){
                        if (addPlus){
                            arrayAsString = arrayAsString + " + " + value[i];
                        }
                        else{
                            arrayAsString = arrayAsString + value[i];
                        }
                    }
                }
                else {
                    if (addPlus) {
                        arrayAsString = arrayAsString + " + " + value[i];
                    } else {
                        arrayAsString = arrayAsString + value[i];
                    }
                }
            }
        }
        return arrayAsString;
    }

    public static boolean checkPlayersFinished(Player[] players){
        for (int i = 0; i < players.length; i++){
            if (!players[i].getFinished()){
                return false;
            }
        }
        return true;
    }
}