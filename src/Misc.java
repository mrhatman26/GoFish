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
            System.out.println("(Minimum is 2 and max is 10 if you want to stay sane)");
            System.out.print("Player count: ");
            String input = Main.uInput.nextLine();
            if (!isNumber(input)){
                System.out.println("\nInput must be a whole number!\n");
                pauseSeconds(1);
            }
            else{
                playerCount = Integer.parseInt(input);
                if (playerCount < 2){
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
}