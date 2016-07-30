/**
 * Created by Eamon on 5/3/2015.
 */
public class ShipTypes {
    public static final String BATTLESHIP = "Battleship"; //5
    public static final String PATROL_BOAT = "Battleship"; //<=3
    public static final String CARRIER = "Battleship"; //6
    public static final String GUNSHIP = "Gunship"; //4
    public static final String GENERIC = "ship";

    public static String getTypeFromLength(int shipLength)
    {
        if(shipLength <= 3) return PATROL_BOAT;
        else if(shipLength == 4) return GUNSHIP;
        else if(shipLength == 5) return BATTLESHIP;
        else if(shipLength >= 6) return CARRIER;
        else{
            return GENERIC;
        }
    }
}
