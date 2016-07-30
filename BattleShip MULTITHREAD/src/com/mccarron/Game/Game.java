package com.mccarron.Game;

import com.mccarron.GameConstants;
import com.mccarron.Gui.Gui;

import java.util.ArrayList;

public class Game {
    ArrayList<Ship> ships = new ArrayList<Ship>();
    ArrayList<String> guesses = new ArrayList<String>();
    ArrayList<String> cellsHit = new ArrayList<String>();
    private String lastShipSunk;
    private boolean gameOver = false;
    GameHelper gameHelper = new GameHelper();
    private int guessesRemaining = 25;

    public static void main(String args[])
    {
        Game game = new Game();

        game.init();


        Gui gui = new Gui();
        gui.buildGui();

        game.runGame(gui);
    }

    private void init() //create 3 ships and place them on grid
    {
        for(int x = 0; x < GameConstants.NUM_OF_SHIPS; x++){
            ships.add(new Ship(gameHelper.createLocation(GameConstants.SHIP_SIZES[x]), GameConstants.getShipNameFromSize(GameConstants.SHIP_SIZES[x])));
        }

    }



    private String checkGuess(String guess){
        String result = GameConstants.RESULTS.SHIP_MISSED;
        for(Ship s : ships){
            result = s.checkGuess(guess);

            if(result.equals(GameConstants.RESULTS.SHIP_HIT))
                break;

            if(result.equals(GameConstants.RESULTS.SHIP_SUNK)) {
                for(String cell : s.getCellsHit()) {
                    cellsHit.add(cell);
                    lastShipSunk = s.getId();
                }
                ships.remove(s);
                break;
            }
            for(String lastGuess : guesses){
                if(lastGuess.equalsIgnoreCase(guess)){
                    result = GameConstants.RESULTS.ALREADY_GUESSED;
                }

            }
        }
        guesses.add(guess);
        if(!result.equals(GameConstants.RESULTS.ALREADY_GUESSED))
            guessesRemaining--;
        return result;
    }

    private void runGame(Gui gui)
    {
        while(!gameOver) {
            String result = "test";

            if (GuessHandler.wasCellGuessed()) {
                result = checkGuess(GuessHandler.getGuess());
                GuessHandler.onGuessChecked(result, cellsHit);
                gui.updateGui(guessesRemaining, result);
            }




            if(ships.isEmpty()){
                endGame(false, gui);
                gameOver = true;
                break;
            }
            if(guessesRemaining<=0){
                endGame(true, gui);
                gameOver = true;
                break;
            }
        }

    }

    private void endGame(boolean lost, Gui gui){
        gui.drawEndGameScreen(lost, "GAME OVER", "YOU SUNK ALL THE SHIPS!");
    }

    //non functional code
    /*public static void restart(){
        final String javaBin = System.getProperty("java.home");
        final File currentJar;
        try {
            currentJar = new File(Game.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Unable to restart application");
            System.exit(-1);
            return;
        }

        if(!currentJar.getName().endsWith(".jar")) {
            System.out.println("Jar found is not jar, exiting application");
            System.exit(-1);
            return;
        }
        final ArrayList<String> command = new ArrayList<String>();
        command.add(javaBin);
        command.add("-jar");
        command.add(currentJar.getPath());

        final ProcessBuilder launcher = new ProcessBuilder(command);
        try {
            launcher.start();
            System.exit(0);
        }catch(IOException IoE){
            IoE.printStackTrace();
            System.out.println("unable to restart application from current directory");
            System.exit(-1);
            return;
        }

    } */

}
