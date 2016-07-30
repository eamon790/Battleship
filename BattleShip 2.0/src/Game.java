import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class Game {
    private GameHelper gameHelper = new GameHelper();
    private int[] shipSizes = {5,4,3};
    private ArrayList<Ship> ships = new ArrayList<Ship>(shipSizes.length);
    private ArrayList<String> guesses = new ArrayList<String>();
    private int maxGuesses = 40;
    private boolean  gameOver = false;

    private void init()
    {
        Ship ship1 = new Ship(gameHelper.createLocation(shipSizes[0]), ShipTypes.getTypeFromLength(shipSizes[0]));
        Ship ship2 = new Ship(gameHelper.createLocation(shipSizes[1]), ShipTypes.getTypeFromLength(shipSizes[1]));
        Ship ship3 = new Ship(gameHelper.createLocation(shipSizes[2]), ShipTypes.getTypeFromLength(shipSizes[2]));

        ships.add(ship1);
        ships.add(ship2);
        ships.add(ship3);

        displayStartingMessage();
    }

    private void start()
    {
        String result = Result.SHIP_MISSED;
        boolean gameLost = false;
        while(!gameOver)
        {
            result = checkGuess(gameHelper.getUserInput());
            if(result.equals(Result.GAME_OVER)) {
                gameOver = true;
                gameLost = true;
            }
            System.out.println(result);
            System.out.println("You have: " + (maxGuesses - guesses.size()) + " Guesses remaining");
            if(ships.isEmpty()) {
                gameOver = true;
                gameLost = false;
            }
        }
        finishGame(gameLost);
    }

    private String checkGuess(String guess)
    {
        String result = Result.SHIP_MISSED;

        if(guesses.size() >= maxGuesses){
        result = Result.GAME_OVER;
        return result;
        }
        for(Ship ship : ships)
        {
            result = ship.checkGuess(guess);

            if(result.equals(Result.SHIP_HIT))
            {
                break;
            }

            if(result.equals(Result.SHIP_SUNK))
            {
                result = ("You sunk " + ship.getName() + "! You have " + (ships.size() - 1) + " ships left to sink");
                ships.remove(ship);
                break;
            }

        }


        if(guesses.contains(guess))
        {
            result = Result.ALREADY_GUESSED;
        }
        guesses.add(guess);
        return result;
    }


    private void finishGame(Boolean lost)
    {
        if(lost){
            System.out.println("Out of Guesses, Game Over!");
        }else{
            System.out.println("All ships sunk! you finished with: " + guesses.size() + " Guesses");
        }


    }
    private void displayStartingMessage()
    {
        System.out.println("You goal is to sink all " + shipSizes.length + " ships");
        System.out.println("The ships are on a " + gameHelper.getGridDimensions() + " grid");
        System.out.println("use alpha-numerical coordinate pairs such as 'a5' to guess locations");
        System.out.println("You have " + maxGuesses + " guesses, Good Luck!");
        System.out.println();
    }
    public static void main(String[] args) {
        Game game = new Game();
        game.init();
        game.start();
    }
}
