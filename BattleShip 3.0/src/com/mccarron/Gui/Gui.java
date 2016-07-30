package com.mccarron.Gui;
import com.mccarron.Game.Game;
import com.mccarron.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Gui {
    private ArrayList<Cell> grid = new ArrayList<Cell>(GameConstants.GRID_SIZE);
    private GridListener gridListener = new GridListener(grid);
    private Font coordinateFont = new Font(Font.DIALOG,Font.BOLD,21);
    OutputPanel outputPanel = new OutputPanel(coordinateFont);
    JFrame window = new JFrame();
    public void buildGui(){
        NumberCoordinates numCoords = new NumberCoordinates(coordinateFont);
        numCoords.setLayout(new BoxLayout(numCoords,BoxLayout.X_AXIS));
        AlphaCoordinates alphaCoords = new AlphaCoordinates(coordinateFont);
        GridPanel gridPanel = new GridPanel();



        for(int i = 0; i < GameConstants.GRID_SIZE; i++){
            grid.add(new Cell(i));
        }
        for(Cell x : grid){
            gridPanel.add(x);
            x.addActionListener(gridListener);
            x.setText(Integer.toString(x.index));
        }




        window.setSize(Dimensions.windowSize);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        window.getContentPane().add(BorderLayout.CENTER, gridPanel);
        window.getContentPane().add(BorderLayout.NORTH, numCoords);
        window.getContentPane().add(BorderLayout.SOUTH, outputPanel);
        window.getContentPane().add(BorderLayout.WEST, alphaCoords);


        window.setResizable(false);
        window.setVisible(true);
    }

    public void updateGui(int numOfGuesses, String resultPanelText){
        outputPanel.updatePanel(numOfGuesses, resultPanelText);
        window.repaint();
    }

    public void drawEndGameScreen(boolean lost, String gameOverText, String gameWonText){
        if(lost){
            window.getContentPane().removeAll();
            window.getContentPane().revalidate();

            window.getContentPane().setBackground(new Color(172, 48, 56));
            Graphics windowG = window.getContentPane().getGraphics();
            windowG.setFont(new Font(Font.DIALOG, Font.BOLD, 90));
            windowG.drawString(gameOverText, Dimensions.windowSize.width / 2 - (29 * gameOverText.length()), Dimensions.windowSize.height / 2);


        }else{
           window.getContentPane().removeAll();
           window.getContentPane().removeAll();

           window.getContentPane().setBackground(new Color(94, 172, 108));

            JButton newGame = new JButton("Play Again?");
            JButton endGame = new JButton("exit");
            window.getContentPane().add(newGame, BorderLayout.NORTH);
            window.getContentPane().add(endGame, BorderLayout.SOUTH);
            Graphics windowG = window.getContentPane().getGraphics();
            windowG.setFont(new Font(Font.DIALOG, Font.BOLD, 45));
            windowG.drawString(gameWonText, Dimensions.windowSize.width / 2 - (29 * gameOverText.length() + 30 ), Dimensions.windowSize.height / 2);
        }

    }

}
