package com.company;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
public class GameHelper{
    public ArrayList<String> createLocation(int length)
    {
        Random rand = new Random(System.nanoTime());
        ArrayList<Integer> iLocation = new ArrayList<Integer>();
        ArrayList<String> sLocation = new ArrayList<String>();
        iLocation.add(0, rand.nextInt(8));

        for(int i = 0; i <= length; i++)
        {
            iLocation.add(iLocation.get(i) + 1);
        }
        for(int cell : iLocation)
        {
            sLocation.add(Integer.toString(cell));
        }
        return sLocation;
    }

    public String getUserInput()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("enter a guess: ");
        return input.next();
    }
}
