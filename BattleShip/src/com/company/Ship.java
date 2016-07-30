package com.company;
import java.util.Scanner;
import java.util.ArrayList;

public class Ship {
    private ArrayList<String> location;
    private int currentGuess = 0;
    private int maxGuesses = 0;

    Ship(ArrayList<String> loc) {
        setLocation(loc);
    }

    String checkGuess(String guess) {
        String result = Result.SHIP_MISSED;;

        int index = location.indexOf(guess);
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


}
