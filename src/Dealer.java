import java.util.Random;

public class Dealer {
    private final String name;
    private final String[] cards = {"A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    Dealer(){
        this.name = "Adeal";
    }

    public String getName(){
        return this.name;
    }

    public String[] getCards(){
        return this.cards;
    }

    public String getSingleCard(int index){
        return cards[index];
    }

    public void firstServe(Player[] players, int playerCount){
        Random rand = new Random();
        String[] newCards = new String[7];
        for (int i = 0; i < playerCount; i++){
            for (int j = 0; j < newCards.length; j++){
                newCards[j] = cards[rand.nextInt(cards.length)];
                if (players[i].getCards().equals("")){
                    players[i].setCards(newCards[j]);
                }
                else{
                    players[i].setCards(players[i].getCards() + " + " + newCards[j]);
                }
            }
            if (!players[i].getIsPlayer()){
                System.out.println("\nThe dealer handed " + players[i].getName() + " their first 7 cards.");
                Misc.pauseSeconds(1);
            }
            else{
                System.out.print("\nThe dealer hands you your first 7 cards.\nThey are: ");
                System.out.println(players[i].getCards());
                UserInput.pauseForEnterKey();
            }
            players[i].pairCheck();
        }
    }

    public void giveCard(Player player){
        Random rand = new Random();
        String newCard = cards[rand.nextInt(cards.length)];
        player.setCards(player.getCards() + " + " + newCard);
        if (player.getIsPlayer()) {
            UserInput.pauseForEnterKey();
            System.out.println("\nThe dealer hands you a new card. It is a " + newCard + "\nYour cards are now: " + player.getCards());
            UserInput.pauseForEnterKey();
        }
        else{
            System.out.println("The dealer hands " + player.getName() + " a new card.");
        }
        player.pairCheck();
    }
}
