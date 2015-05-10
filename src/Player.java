/**
 * Created by Bimurto on 11-May-15.
 */
public class Player {
    String type;
    char symbol;

    public Player(String type,char symbol){
        this.type = type;
        this.symbol = symbol;
    }

    public Player(String type){
        this.type = type;
        if(type.equals(Constants.MAX))
            symbol = Constants.X;
        else symbol = Constants.O;
    }

    @Override
    public String toString() {
        return type;
    }
}
