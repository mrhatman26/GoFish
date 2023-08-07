import java.util.Random;

public class Player {
    private String name;
    private String cards;
    private int pairs;
    private Boolean isPlayer;
    Player(){
        Random rand = new Random();
        this.name = Main.Names[rand.nextInt(Main.Names.length)];
        this.cards = "";
        this.isPlayer = false;
        this.pairs = 0;
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

    //Methods
    public void pairCheck(){
        String[] cardList = cards.split(" \\+ ");
        Boolean foundPairs = false;
        for (int i = 0; i < cardList.length; i++){
            for (int j = 0; j < cardList.length; j++){
                if (cardList[j].equals(cardList[i])){
                    if (i != j) {
                        System.out.println("cardList[" + j +"](j) with the value of " + cardList[j] + " is equal to cardList[" + i + "](i) with value of " + cardList[i]);
                        cardList[i] = "";
                        cardList[j] = "";
                        this.setPairs(this.getPairs() + 1);
                        foundPairs = true;
                    }
                }
            }
        }
        this.setCards(Misc.arrayToString(cardList, true, true));
        if (foundPairs){
            if (this.getIsPlayer()) {
                System.out.println("You found some pairs! Your score is now " + this.getPairs());
                Misc.pauseSeconds(1);
                System.out.println("Your cards are now:\n" + this.getCards());
            }
            else{
                System.out.println("It would seem that " + this.getName() + " found some pairs.");
            }
            Misc.pauseSeconds(2);
        }
    }
}
