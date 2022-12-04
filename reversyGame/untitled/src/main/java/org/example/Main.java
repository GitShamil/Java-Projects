package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game.in = new Scanner(System.in);
        Integer command;
        while(true){
            do {
                System.out.println("write command \n \"1\" - start \n \"2\" - score \n \"3\" - exit ");
                command = Utilities.stringToInteger(Game.in.nextLine());
            } while (command == null || command < 1 || command > 3);
            switch (command) {
                case 1 -> {
                    Game game = Game.createGame();
                    game.start();
                }
                case 2 -> {
                    System.out.println("Best score was: " + Game.getBestScore() + "\n");
                }
                case 3 -> {
                    return;
                }
            }
        }
    }
}