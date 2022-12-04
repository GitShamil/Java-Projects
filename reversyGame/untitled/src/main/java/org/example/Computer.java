package org.example;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/*bot
 * */
public final class Computer extends Player {
    //    complexity or deep of bot
    private final Integer complexity;

    public Computer(Integer numberOfPlayer, Board board, Integer dep) {
        super(numberOfPlayer, board);
        this.complexity = dep;
    }

    @Override
    public void makeMotion() {
        Square bestSquare = null;
        double points = -100000;

        for (int i = 0; i < Board.sizeOfField; i++) {
            for (int j = 0; j < Board.sizeOfField; j++) {
                if (board.getSquare(i, j).isAvailable()) {
                    double currentPoints = R(board.getSquare(i, j), complexity);
                    if (points == currentPoints) {
                        Random rand = new Random();
                        if (rand.nextBoolean()) {
                            continue;
                        }
                    }
                    if (points <= currentPoints) {
                        points = currentPoints;
                        bestSquare = board.getSquare(i, j);
                    }
                }
            }
        }
        board.putAtSquare(bestSquare.getI(), bestSquare.getJ());
    }

    //    method count benefits of move to sq
    private double R(Square sq, Integer dep) {
        if (dep == 0) {
            return 0;
        }
        var changes = board.putAtSquare(sq.getI(), sq.getJ());
        double currentUpgradePoints = countPointChangesAfterStep(changes);
        double pointsFromNextOperation = -50000;
        if (!board.isThereAvailableSquares()) {
            Integer numWhoWon = board.whoWon();
            if (Objects.equals(board.getOrder(), numWhoWon)) {
                board.moveBack(1);
                return -10000;
            } else {
                board.moveBack(1);
                return 10000;
            }
        }
        if (dep == 1) {
            board.moveBack(1);
            return currentUpgradePoints;
        }
        //1
        // System.out.println(board + "\n");
        for (int i = 0; i < Board.sizeOfField; i++) {
            for (int j = 0; j < Board.sizeOfField; j++) {
                if (board.getSquare(i, j).isAvailable()) {
                    pointsFromNextOperation = Math.max(R(board.getSquare(i, j), dep - 1), pointsFromNextOperation);
                }
            }
        }
        board.moveBack(1);
        return currentUpgradePoints - pointsFromNextOperation;
    }

    private double countPointChangesAfterStep(ArrayList<Square> changes) {
        double sum = 0;
        if (changes.get(0).isEdge()) {
            sum += 0.4;
        }
        if (changes.get(0).isCorner()) {
            sum += 0.8;
        }
        for (int i = 1; i < changes.size(); i++) {
            if (changes.get(i).isEdge()) {
                sum += 2;
            } else {
                sum += 1;
            }
        }
        return sum;
    }
}
