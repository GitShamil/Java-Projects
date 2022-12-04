package org.example;

import java.util.Objects;
import java.util.Scanner;

/*
 * class of Game
 * you should create instance of that if you want create a game
 * */
public class Game {

    //    input
    public static Scanner in;
    //    first player
    private final Player player1;
    //    second player
    private final Player player2;
    private final Board board;

    public static Integer getBestScore() {
        return bestScore;
    }

    //    bestscore of this session
    private static Integer bestScore = 0;

    //    constructor
    public Game(Boolean againstHuman) {
        if (againstHuman) {
            board = Board.createBoard();
            player1 = new Human(1, board);
            player2 = new Human(2, board);
        } else {
            board = Board.createBoard();
            player1 = new Human(1, board);
            player2 = new Computer(2, board, 0);
        }
    }

    //        constructor with complexity of computer
    public Game(Boolean againstHuman, Integer complexity) {
        if (!againstHuman) {
            board = Board.createBoard();
            player1 = new Human(1, board);
            player2 = new Computer(2, board, complexity);
        } else {
            board = Board.createBoard();
            player1 = new Human(1, board);
            player2 = new Human(2, board);
        }
    }

    //    method for creating game
    public static Game createGame() {
        Integer against;
        do {
            System.out.println("Choose game mode \n \"1\"" +
                    " - human-computer \n \"2\" - human-human");
            against = Utilities.stringToInteger(in.nextLine());
        } while (against == null || (against != 1 && against != 2));
        if (against == 1) {
            Integer complexity;
            do {
                System.out.println("\nchoose complexity (number from 0 to 4)");
                complexity = Utilities.stringToInteger(in.nextLine());
            } while (complexity == null || complexity < 0 || complexity > 4);
            return new Game(false, complexity);
        }
        return new Game(true);
    }

    //    method for start game
    public void start() {
        boolean ended = false;
        System.out.println("\"help\" for helping in any moment, \"back\" for one step back ");
        do {
            if (board.isThereAvailableSquares()) {
                System.out.println(board + "\n");
                if (board.getOrder() == 1) {
                    player1.makeMotion();
                } else if (board.getOrder() == 2) {
                    player2.makeMotion();
                }
            } else {
                ended = true;
            }
        } while (!ended);
        endOfTheGame();
    }

    //    method which work when game is ended
    protected void endOfTheGame() {
        System.out.println(board + "\n");
        Integer numOfWinner = board.whoWon();
        if (numOfWinner != 0) {
            System.out.println("Player ❤" + numOfWinner + "won❤ with " +
                    +board.getScore(numOfWinner) + " points\n");
        } else {
            System.out.println("DRAW\n");
        }
        countBestScore();
    }

    protected void countBestScore() {
        if (isHuman(player1.getNumberOfPlayer())) {
            bestScore = Math.max(bestScore, player1.getScore());
        }
        if (isHuman(player2.getNumberOfPlayer())) {
            bestScore = Math.max(bestScore, player2.getScore());
        }
    }


    protected boolean isComputer(Integer numPlayer) {
        if (Objects.equals(numPlayer, player1.getNumberOfPlayer()) && player1.getClass() != Computer.class) {
            return false;
        }
        if (Objects.equals(numPlayer, player2.getNumberOfPlayer()) && player2.getClass() != Computer.class) {
            return false;
        }
        return true;
    }

    protected boolean isHuman(Integer numPlayer) {
        return !isComputer(numPlayer);
    }
}
