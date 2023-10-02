import java.util.Random;

public class Player {
    private String name;
    private String cards;
    String choice = "";
    private int pairs;
    int choiceInt = 1;
    private boolean isPlayer;
    private boolean lied;
    private boolean finished;
    Player(){
        Random rand = new Random();
        this.name = Main.Names[rand.nextInt(Main.Names.length)];
        this.cards = "";
        this.isPlayer = false;
        this.pairs = 0;
        this.lied = false;
        this.finished = false;
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

    public boolean getIsPlayer(){
        return this.isPlayer;
    }

    public boolean getLied(){
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

    public void setIsPlayer(boolean value){
        this.isPlayer = value;
    }

    public void setLied(boolean value){
        this.lied = value;
    }

    public boolean getFinished(){
        return this.finished;
    }

    //Methods
    public void pairCheck() {
        String[] cardList = cards.split(" \\+ ");
        boolean foundPairs = false;
        for (int i = 0; i < cardList.length; i++) {
            for (int j = 0; j < cardList.length; j++) {
                if (!cardList[j].equals("") && !cardList[i].equals("")){
                    if (cardList[j].equals(cardList[i])) {
                        if (i != j) {
                            cardList[i] = "";
                            cardList[j] = "";
                            this.setPairs(this.getPairs() + 1);
                            foundPairs = true;
                        }
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
                System.out.println("It would seem that " + this.getName() + " found some pairs.");//\nThis player's cards are now: " + this.getCards() + "\nAnd their score is " + this.getPairs());
                Misc.pauseSeconds(2);
            }
        }
        //To do: Check if player is out of cards!
    }

    public void playerTurn(Player[] players, Dealer dealer){ // Player's turn
        getPlayerChoice(players);
        getLieChoice();
        getCardChoice(dealer);
        hasCardCheck(this, players[choiceInt], dealer);
        this.pairCheck();
        this.cardEmptyCheck();
    }

    public void hasCardCheck(Player playerChecking, Player playerToCheck, Dealer dealer){
        String[] playerCheckingCards;
        boolean pairFound = false;
        //if (playerChecking.getIsPlayer()) {
        if (playerChecking.getLied()) {
            playerCheckingCards = dealer.getCards();
        } else {
            playerCheckingCards = playerChecking.getCards().split(" \\+ ");
        }
        String[] playerToCheckCards = playerToCheck.getCards().split(" \\+ ");
        char cardChoiceBackup = playerCheckingCards[Integer.valueOf(choice)].charAt(0);
        //System.out.println("\n" + playerToCheck.getCards() + "\n");
        for (int i = 0; i < playerToCheckCards.length; i++){
            //System.out.println(i);
            if (playerToCheckCards[i].equals(playerCheckingCards[Integer.valueOf(choice)])){
                playerToCheckCards[i] = "";
                pairFound = true;
                break;
            }
        }
        if (pairFound) {
            playerToCheck.setCards(Misc.arrayToString(playerCheckingCards, true, true));
            playerChecking.setCards(Misc.arrayToString(playerCheckingCards, true, true));
            playerChecking.setCards(playerChecking.cards + " + " + cardChoiceBackup);
            if (this.getIsPlayer()) {
                System.out.println(playerToCheck.getName() + " had your card!");// Your score is now " + playerChecking.getPairs());
                Misc.pauseSeconds(2);
                System.out.println("Your cards are now: " + playerChecking.getCards());
            }
            else{
                System.out.println(this.getName() + " asked " + playerToCheck.getName() + " if they had a " + cardChoiceBackup + "..."); //playerCheckingCards[Integer.parseInt(choice)]
                Misc.pauseSeconds(1);
                System.out.println(playerToCheck.getName() + " had the card " + this.getName() + " wanted!");
                Misc.pauseSeconds(1);
            }
        }
        else {
            if (this.getIsPlayer()) {
                System.out.println(playerToCheck.getName() + " tells you to Go Fish.");
                dealer.giveCard(playerChecking);
            }
            else{
                System.out.println(this.getName() + " asked " + playerToCheck.getName() + " if they had a " + cardChoiceBackup + "...");
                Misc.pauseSeconds(1);
                System.out.println(playerToCheck.getName() + " tells " + this.getName() + " to Go Fish.");
                dealer.giveCard(playerChecking);
            }
        }
    }

    private void getPlayerChoice(Player[] players){
        if (this.getIsPlayer()) {
            boolean uInputAccepted = false;
            while (!uInputAccepted) {
                if (players.length > 2) {
                    System.out.println("\nIt's your turn, which player do you want to pick?");
                    Misc.pauseSeconds(1);
                    System.out.println("Players:");
                    for (int i = 1; i < players.length; i++) {
                        System.out.println(i + ": " + players[i].getName());
                    }
                    choice = (UserInput.getUserInput("Player Number (" + (players.length - 1) + ")")); //Remove the player length part!
                    if (!Misc.isNumber(choice)) {
                        System.out.println("\nPlayer choice must be a number!");
                        Misc.pauseSeconds(3);
                    } else {
                        choiceInt = Integer.parseInt(choice);
                        if (!Misc.checkInRange(choiceInt, 1, players.length - 1, true, true)) {
                            System.out.println("\nPlayer choice must match a player number!");
                            Misc.pauseSeconds(3);
                        } else {
                            System.out.println("\nYou chose " + players[choiceInt].getName());
                            UserInput.pauseForEnterKey();
                            uInputAccepted = true;
                        }
                    }
                } else {
                    System.out.println("\nIt's your turn.");
                    choiceInt = 1;
                    uInputAccepted = true;
                }
            }
        }
        else{
            System.out.println("\nIt's " + this.getName() + "'s turn.");
            Misc.pauseSeconds(1);
            Random rand = new Random();
            while (true) {
                choiceInt = rand.nextInt(players.length);
                if (players[choiceInt] != this){
                    break;
                }
            }
        }
    }

    private void getLieChoice(){
        if (this.getIsPlayer()) {
            boolean uInputAccepted = false;
            while (!uInputAccepted) {
                System.out.println("\nDo you want to use your cards or lie with a fake card?");
                choice = UserInput.getUserInput("Lie (L) or Truth (T)");
                if (choice.equals("")) {
                    System.out.println("\nPlease enter something!");
                    Misc.pauseSeconds(3);
                } else if (!choice.toUpperCase().equals("L") && !choice.toUpperCase().equals("T")) {
                    System.out.println("Please enter either L or T!");
                    Misc.pauseSeconds(3);
                } else {
                    choice = choice.toUpperCase();
                    break;
                }
            }
        }
        else{
            Random rand = new Random();
            byte aiChoice = (byte) rand.nextInt(100);
            if (aiChoice <= 75){
                choice = "T";
            }
            else{
                choice = "L";
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
        if (this.getIsPlayer()) {
            while (true) {
                System.out.println("\nWhich card would you like to ask for?\nYou can pick from:");
                for (int i = 0; i < cardChoices.length; i++) {
                    System.out.println(String.valueOf(i + 1) + ": " + cardChoices[i]);
                }
                choice = UserInput.getUserInput("Card number");
                if (!Misc.isNumber(choice)) {
                    System.out.println("\nInput must be a number");
                    Misc.pauseSeconds(3);
                } else if (!Misc.checkInRange(Integer.valueOf(choice), 1, cardChoices.length, true, true)) {
                    System.out.println("\nInput must match card number!");
                    Misc.pauseSeconds(3);
                } else {
                    choice = String.valueOf(Integer.valueOf(choice) - 1);
                    break;
                }
            }
        }
        else{
            Random rand = new Random();
            choice = String.valueOf(rand.nextInt(cardChoices.length));
        }
    }

    public void cardEmptyCheck(){
        String[] playerCardList;
        playerCardList = this.getCards().split(" \\+ ");
        if (playerCardList.length < 1){
            this.finished = true;
            if (this.isPlayer){
                System.out.println("Looks like you are out of cards!\nYou can no longer take any turns.\nYour final score is " + this.pairs + ".");
                UserInput.pauseForEnterKey();
            }
            else{
                System.out.println("Looks like " + this.getName() + " has ran out of cards and can no longer take any turns.");
                Misc.pauseSeconds(1);
            }
        }
        else{
            this.finished = false;
        }
    }
}
