import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.concurrent.TimeUnit;
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

    public static int getPlayerCount(){
        int playerCount;
        while (true){
            System.out.println("How many players?");
            System.out.println("(Minimum is 1 and max is 10 if you want to stay sane)");
            System.out.print("Player count: ");
            String input = Main.uInput.nextLine();
            if (!isNumber(input)){
                System.out.println("\nInput must be a whole number!\n");
                pauseSeconds(1);
            }
            else{
                playerCount = Integer.parseInt(input);
                if (playerCount < 1){
                    System.out.println("\nInput must be greater than or equal to 2!\n");
                }
                else{
                    System.out.println(input + " players selected.\n");
                    pauseSeconds(1);
                    return playerCount;
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
                            arrayAsString = arrayAsString + "+" + value[i];
                        }
                        else{
                            arrayAsString = arrayAsString + value[i];
                        }
                    }
                }
                else {
                    if (addPlus) {
                        arrayAsString = arrayAsString + "+" + value[i];
                    } else {
                        arrayAsString = arrayAsString + value[i];
                    }
                }
            }
        }
        return arrayAsString;
    }
}