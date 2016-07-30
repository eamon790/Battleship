import java.util.ArrayList;

public class Ship {
    private ArrayList<String> location;
    private String name;
    Ship(ArrayList<String> loc, String shipName) {
        setLocation(loc);
        setName(shipName);
    }

    String checkGuess(String guess) {
        String result = Result.SHIP_MISSED;

        int index = location.indexOf(guess.toLowerCase());
        if(index >= 0) //indexOf will return -1 if index given is not in ArrayList
        {
            location.remove(index);
            result = Result.SHIP_HIT;
        }else {
            result = Result.SHIP_MISSED;
        }
        if(location.isEmpty())
        {
            result = Result.SHIP_SUNK;
        }
        return result;
    }

    void setLocation(ArrayList<String> loc) {
         location = loc;
    }
    void setName(String aName){name = aName;}
    String getName(){return name;}
}
