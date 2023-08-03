import java.util.Random;

public class Player {
    private String name;
    private String cards;
    private Boolean isPlayer;
    Player(){
        Random rand = new Random();
        this.name = Main.Names[rand.nextInt(Main.Names.length)];
        this.cards = "";
        this.isPlayer = false;
    }

    //Get methods
    public String getName(){
        return this.name;
    }

    public String getCards(){
        return this.cards;
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

    public void setIsPlayer(Boolean value){
        this.isPlayer = value;
    }
}
