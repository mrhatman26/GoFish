import java.util.Random;

public class Player {
    private String name;
    private String cards;
    String choice = "";
    private int pairs;
    int choiceInt = 1;
    private Boolean isPlayer;
    private Boolean lied;
    Player(){
        Random rand = new Random();
        this.name = Main.Names[rand.nextInt(Main.Names.length)];
        this.cards = "";
        this.isPlayer = false;
        this.pairs = 0;
        this.lied = false;
    }

    //Get methods
    public String getName(){
        return this.name;
    }

    public String getCards(){
        return this.cards;
    }

    public int getPairs(){
        return this.pairs;
    }

    public Boolean getIsPlayer(){
        return this.isPlayer;
    }

    public Boolean getLied(){
        return this.lied;
    }

    //Set methods
    public void setName(String value){
        this.name = value;
    }

    public void setCards(String value){
        this.cards = value;
    }

    public void setPairs(int value){
        this.pairs = value;
    }

    public void setIsPlayer(Boolean value){
        this.isPlayer = value;
    }

    public void setLied(Boolean value){
        this.lied = value;
    }

    //Methods
    public void pairCheck() {
        String[] cardList = cards.split(" \\+ ");
        Boolean foundPairs = false;
        for (int i = 0; i < cardList.length; i++) {
            for (int j = 0; j < cardList.length; j++) {
                if (cardList[j].equals(cardList[i])) {
                    if (i != j) {
                        cardList[i] = "";
                        cardList[j] = "";
                        this.setPairs(this.getPairs() + 1);
                        System.out.println("Pairs is now: " + this.getPairs());
                        foundPairs = true;
                    }
                }
            }
        }
        this.setCards(Misc.arrayToString(cardList, true, true));
        if (foundPairs) {
            if (this.getIsPlayer()) {
                System.out.println("\nYou found some pairs! Your score is now " + this.getPairs());
                Misc.pauseSeconds(1);
                System.out.println("Your cards are now:\n" + this.getCards());
                UserInput.pauseForEnterKey();
            } else {
                System.out.println("It would seem that " + this.getName() + " found some pairs.");
                Misc.pauseSeconds(2);
            }
        }
        //To do: Check if player is out of cards!
        //Also to do: Check why this method is giving players the wrong amount of pairs!
    }

    public void playerTurn(Player[] players, Dealer dealer){
        if (this.getIsPlayer()) { // Player's turn
            getPlayerChoice(players);
            getLieChoice();
            getCardChoice(dealer);
            //System.out.println("Player choice is: " + choiceInt + "\nLie choice is: " + this.getLied() + "\nCard choice is: " + choice);
            //UserInput.pauseForEnterKey();
            hasCardCheck(this, players[choiceInt], dealer);
        }
        /*else{
            //AI Turn
        }*/
    }

    public void hasCardCheck(Player playerChecking, Player playerToCheck, Dealer dealer){
        String[] playerCheckingCards;
        Boolean pairFound = false;
        if (playerChecking.getIsPlayer()) {
            if (playerChecking.getLied()) {
                playerCheckingCards = dealer.getCards();
            } else {
                playerCheckingCards = playerChecking.getCards().split(" \\+ ");
            }
            String[] playerToCheckCards = playerToCheck.getCards().split(" \\+ ");
            //System.out.println("\n" + playerToCheck.getCards() + "\n");
            for (int i = 0; i < playerToCheckCards.length; i++){
                //System.out.println(i);
                if (playerToCheckCards[i].equals(playerCheckingCards[Integer.valueOf(choice)])){
                    playerCheckingCards[Integer.valueOf(choice)] = "";
                    playerToCheckCards[i] = "";
                    playerChecking.setPairs(playerChecking.getPairs() + 1);
                    pairFound = true;
                    break;
                }
            }
            if (pairFound) {
                playerToCheck.setCards(Misc.arrayToString(playerCheckingCards, true, true));
                playerChecking.setCards(Misc.arrayToString(playerCheckingCards, true, true));
                System.out.println(playerToCheck.getName() + " had your card! Your score is now " + playerChecking.getPairs());
                Misc.pauseSeconds(2);
                System.out.println("Your cards are now: " + playerChecking.getCards());
                UserInput.pauseForEnterKey();
            }
            else {
                System.out.println(playerToCheck.getName() + " tells you to Go Fish.");
                dealer.giveCard(playerChecking);
                UserInput.pauseForEnterKey();
            }
        }
    }

    private void getPlayerChoice(Player[] players){
        Boolean uInputAccepted = false;
        while (!uInputAccepted) {
            if (players.length > 2 ) {
                System.out.println("\nIt's your turn, which player do you want to pick?");
                Misc.pauseSeconds(1);
                System.out.println("Players:");
                for (int i = 1; i < players.length; i++) {
                    System.out.println(i + ": " + players[i].getName());
                }
                choice = (UserInput.getUserInput("Player Number"));
                if (!Misc.isNumber(choice)) {
                    System.out.println("\nPlayer choice must be a number!");
                    Misc.pauseSeconds(3);
                } else {
                    choiceInt = Integer.parseInt(choice);
                    if (!Misc.checkInRange(choiceInt, 1, players.length, true, true)) {
                        System.out.println("\nPlayer choice must match a player number!");
                        Misc.pauseSeconds(3);
                    } else {
                        System.out.println("\nYou chose " + players[choiceInt].getName());
                        Misc.pauseSeconds(3);
                        uInputAccepted = true;
                    }
                }
            }
            else{
                System.out.println("\nIt's your turn.");
                choiceInt = 1;
                uInputAccepted = true;
            }
        }
    }

    private void getLieChoice(){
        Boolean uInputAccepted = false;
        while (!uInputAccepted){
            System.out.println("\nDo you want to use your cards or lie with a fake card?");
            choice = UserInput.getUserInput("Lie (L) or Truth (T)");
            if (choice.equals("")){
                System.out.println("\nPlease enter something!");
                Misc.pauseSeconds(3);
            }
            else if (!choice.toUpperCase().equals("L") && !choice.toUpperCase().equals("T")){
                System.out.println("Please enter either L or T!");
                Misc.pauseSeconds(3);
            }
            else{
                choice = choice.toUpperCase();
                break;
            }
        }
    }

    private void getCardChoice(Dealer dealer){
        String[] cardChoices;
        if (choice.equals("L")){
            cardChoices = dealer.getCards();
            this.setLied(true);
        }
        else{
            cardChoices = this.getCards().split(" \\+ ");
            this.setLied(false);
        }
        while (true) {
            System.out.println("\nWhich card would you like to ask for?\nYou can pick from:");
            for (int i = 0; i < cardChoices.length; i++) {
                System.out.println(String.valueOf(i + 1) + ": " + cardChoices[i]);
            }
            choice = UserInput.getUserInput("Card number");
            if (!Misc.isNumber(choice)){
                System.out.println("\nInput must be a number");
                Misc.pauseSeconds(3);
            }
            else if (!Misc.checkInRange(Integer.valueOf(choice), 1, cardChoices.length, true, true)){
                System.out.println("\nInput must match card number!");
                Misc.pauseSeconds(3);
            }
            else{
                choice = String.valueOf(Integer.valueOf(choice) - 1);
                break;
            }
        }
    }
}
