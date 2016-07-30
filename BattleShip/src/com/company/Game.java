package com.company;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class Game {

    private static int currentGuess = 0;
    private static int maxGuesses = 10;
    private static boolean  gameOver = false;

    public static void main(String[] args) {
        GameHelper gameHelper = new GameHelper();

        ArrayList<String> location = gameHelper.createLocation(3);
          /*  location.add("1");
            location.add("2");
            location.add("3"); */
        Ship ship = new Ship(location);
        ArrayList<String> guesses = new ArrayList<String>();
        String guess;
        String result;


        while(!gameOver)
        {
            guess = gameHelper.getUserInput();
            result = ship.checkGuess(guess);

            if(guesses.contains(guess))
                result = Result.ALREADY_GUESSED;
            if(guesses.size() >= maxGuesses)
                result = Result.GAME_OVER;

            System.out.println(result);
            if(result.equals(Result.SHIP_SUNK) || result.equals(Result.GAME_OVER)) {
                gameOver = true;
                break;
            }
            guesses.add(guess);
            System.out.println("you have: " + (maxGuesses - guesses.size()) + " guesses left");
        }
    }


    public static void updateGuesses(){

    }



}
