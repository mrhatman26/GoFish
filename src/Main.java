import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
    public static int playerCount;
    public static final String[] Names = {"Clinton Sempers", "Glenn Bellincioni", "Cecylia Thompsett", "Burt McConnell", "Fingal Mac Giolla Eoin", "Haywood Ferri", "Florentina Alberici", "Argyro Gerst", "Władek Beyersdorf", "Rube Beyer", "Sheard Hardwick", "Marcy Albini", "Penelope Merchant", "Gilbert Shirley", "Amity Saylor", "Minako Pinto", "Leona McFarlane", "Jerrold Hahn", "Inés Provenzano", "Simonetta Lombardi", "Parsifal Wojewódka", "Muireann Guzmán", "Frankie De Felice", "Norene Parma", "Felicita O Meara", "Kam Mac Ruaidhrí", "Kalyani Pitt", "Sławomir Hutson", "Melville Banks", "Lemoine Miyagawa", "Carly Arce", "Cathleen Blanchard", "Chastity Schreier", "Ustinya McClelland", "Kassidy Goldschmidt", "Isaiah Waldroup", "Mika Rae", "Sammi Giehl", "Zinon Lippi", "Gavino Gerver", "Estefanía Symonds", "Ilona Mac Maghnuis", "Twyla Reid", "Nydia O Conghalaigh", "Platon Metaxas", "Giles Benetton", "Shelby Alvarez", "Aodhagán Fairbairn", "Finley Rosenberger", "Raffael Habicht", "Burgundy Law", "Murchadh Ruane", "Inga Rapp", "Cayden Sheehy", "Elise O Cuilinn", "Melvyn Lincoln", "Lizette Mac Mathghamhna", "Innes Duarte", "Yasu Conner", "Santina Gold", "Eustachy Willis", "Nazaret Morris", "Katia Carver", "René Derrickson", "Munroe McAfee", "Natalia Busto", "Natalee Palencia", "Annamaria Cookson", "Pip Gilbert", "Nelli Hughes", "Leela Rettig", "Marika McCaig", "Nektarios Fletcher", "Joni Phelps", "Jerri Grenville", "Esmae Haines", "Cathrin Cokes", "Brunhilde Krauß", "Corinne Bukowski", "Maria Hubbard", "Ethelinda Garrido", "Liv Mullins", "Crocetta Dürr", "Sotos Geisler", "Chihiro Fiscella", "Thad Reiter", "Evangeline Elliston", "Tercero MacGrory", "Ulick Borgnino", "Timotha Rosario", "Bee Narvaez", "Beatriz MacGrory", "Carl Boatwright", "Joye Watanabe", "Plinio Lagorio", "Edwena Serizawa", "Eleonora Kato", "Siegbert Tyler", "Ashlie Bustos", "Isaura Bonham"};
    static Scanner uInput = new Scanner(System.in);

    public static void main(String[] args) {
        playerCount = Misc.getPlayerCount() + 1;
        Player[] players = new Player[playerCount];
        for (int i = 0; i < playerCount; i++){
            Player newPlayer = new Player();
            players[i] = newPlayer;
            if (i == 0){
                players[i].setName("You");
                players[i].setIsPlayer(true);
            }
            System.out.println(players[i].getName() + " sat down at the table.");
            Misc.pauseSeconds(1);
        }
        Dealer dealer = new Dealer();
        System.out.println("\n" + dealer.getName() + " sits in his chair ready to deal the cards.");
        Misc.pauseSeconds(1);
        gameLoop(players, dealer);
    }

    public static void gameLoop(Player[] players, Dealer dealer){
        int rounds = 1;
        Boolean runLoop = true;
        while (runLoop){
            System.out.println(rounds + " begins...");
            Misc.pauseSeconds(1);
            dealer.firstServe(players, playerCount);
            break;
        }
        Misc.closeUserInput();
    }
}