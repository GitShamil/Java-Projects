package org.example;

import java.util.Objects;

public final class Human extends Player {
    public Human(Integer numberOfPlayer, Board board) {
        super(numberOfPlayer, board);
    }

    //    write square that available in console
    private void writeAvailableSquares() {
        System.out.println("You can go to these squares:");
        for (int i = 0; i < Board.sizeOfField; i++) {
            for (int j = 0; j < Board.sizeOfField; j++) {
                if (board.getSquare(j, i).isAvailable()) {
                    System.out.println(String.valueOf((char) ('a' + j)) + " " + (i + 1));
                }
            }
        }
    }

    @Override
    public void makeMotion() {
        try {
            char x = 0;
            Integer y = null;
            do {
                System.out.println("write your move player " + board.getOrder());
                String command = Game.in.nextLine();
                if (Objects.equals(command, "help")) {
                    writeAvailableSquares();
                } else if (Objects.equals(command, "back")) {
                    board.moveBack(2);
                    return;
                }
                var move = command.split(" ");
                if (move.length != 2) {
                    continue;
                }
                x = Utilities.stringToChar(move[0]);
                y = Utilities.stringToInteger(move[1]);
            } while (x == 0 || y == null);
            board.putAtSquare(x - 'a', y - 1);
        } catch (IllegalArgumentException argExc) {
            System.out.println(argExc.getMessage());
        }
    }
}
