package org.example;

import java.util.ArrayList;
import java.util.Stack;

/*
 * class board where save information about board and method for changing board
 * */
public class Board {
    //    size of field
    static int sizeOfField = 8;

    //    get order of player right now
    public Integer getOrder() {
        return order;
    }

    private Integer order;
    //    history of boards
    private Stack<Square[][]> historyTables;

    //    table of board
    private Square[][] table;

    public void setTable(Square[][] table) {
        this.table = table;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public boolean isHistoryTablesEmpty() {
        return historyTables.size() <= 1;
    }

    public Square[][] popHistoryTables() {
        historyTables.pop();
        return Utilities.deepCopy(historyTables.peek());
    }

    public void pushHistoryTables(Square[][] table) {
        this.historyTables.push(Utilities.deepCopy(table));
    }

    public Board() {
    }

    public void changerOrderOnOpposite() {
        order = 3 - order;
    }


    boolean isThereAvailableSquares() {
        for (int i = 0; i < sizeOfField; i++) {
            for (int j = 0; j < sizeOfField; j++) {
                if (table[i][j].isAvailable())
                    return true;
            }
        }
        return false;
    }

    Integer whoWon() {
        if (isThereAvailableSquares()) {
            throw new RuntimeException("The game is going on!");
        }
        Integer first = 0;
        Integer second = 0;
        for (int i = 0; i < sizeOfField; i++) {
            for (int j = 0; j < sizeOfField; j++) {
                if (table[i][j].isBlack()) {
                    ++second;
                }
                if (table[i][j].isWhite()) {
                    ++first;
                }
            }
        }
        if (first > second) {
            return 1;
        }
        if (first < second) {
            return 2;
        }
        return 0;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < sizeOfField; i++) {
            output += String.valueOf(i + 1) + '\t';
            for (int j = 0; j < sizeOfField; j++) {
                output += table[j][i] + "\t";
            }
            output += "\n";
        }
        output += '\t';
        for (int i = 0; i < sizeOfField; i++) {
            output += String.valueOf((char) ('a' + i)) + "\t";
        }
        return output;
    }

    public Square[][] getTable() {
        return table;
    }

    public Square getSquare(Integer i, Integer j) {
        return getTable()[i][j];
    }

    public static Board createBoard() {
        Board board = new Board();
        board.setOrder(1);
        board.historyTables = new Stack<>();
        board.setTable(new Square[sizeOfField][sizeOfField]);
        for (int i = 0; i < sizeOfField; i++) {
            for (int j = 0; j < sizeOfField; j++) {
                board.table[i][j] = new Square(i, j);
            }
        }
        board.table[sizeOfField / 2 - 1][sizeOfField / 2 - 1].setNotAvailableSquare(1);
        board.table[sizeOfField / 2 - 1][sizeOfField / 2].setNotAvailableSquare(2);
        board.table[sizeOfField / 2][sizeOfField / 2 - 1].setNotAvailableSquare(2);
        board.table[sizeOfField / 2][sizeOfField / 2].setNotAvailableSquare(1);
        board.establishAvailability();
        board.pushHistoryTables(board.getTable());
        return board;
    }

    //    method for changing availability of squares after step
    protected void establishAvailability() {
        for (int i = 0; i < sizeOfField; i++) {
            for (int j = 0; j < sizeOfField; j++) {
                if (getSquare(i, j).isEmpty()) {
                    var moves = getArrayMoves(i, j);
                    boolean isAvailable = checkingAvailability(moves);
                    getSquare(i, j).setAvailable(isAvailable);
                }
            }
        }
    }

    //    put color in square at (x,y)
    public ArrayList<Square> putAtSquare(Integer x, Integer y) {
        if (x < 0 || x >= sizeOfField || y < 0 || y >= sizeOfField) {
            throw new IllegalArgumentException("The square doesn't exist");
        }
        getSquare(x, y).setColorOfSquare(getOrder());
        var changes = changeColorsOfSquares(x, y);
        changerOrderOnOpposite();
        establishAvailability();
        pushHistoryTables(table);
        return changes;
    }

    //    class for changing squares after step
    protected abstract class Move {
        Integer i;
        Integer j;

        public Move(Integer i, Integer j) {
            this.i = i;
            this.j = j;
        }

        public abstract void makeStep();

        public abstract boolean isOnEdge();

    }

    protected ArrayList<Move> getArrayMoves(Integer i, Integer j) {
        var moves = new ArrayList<Move>(8);
        moves.add(new UpMove(i, j));
        moves.add(new RightMove(i, j));
        moves.add(new LeftMove(i, j));
        moves.add(new DownMove(i, j));
        moves.add(new UpRightMove(i, j));
        moves.add(new UpLeftMove(i, j));
        moves.add(new DownRightMove(i, j));
        moves.add(new DownLeftMove(i, j));
        return moves;
    }

    protected class UpMove extends Move {
        public UpMove(Integer i, Integer j) {
            super(i, j);
        }

        @Override
        public void makeStep() {
            --i;
        }

        @Override
        public boolean isOnEdge() {
            return i == 0;
        }
    }

    protected class LeftMove extends Move {
        public LeftMove(Integer i, Integer j) {
            super(i, j);
        }

        @Override
        public void makeStep() {
            --j;
        }

        @Override
        public boolean isOnEdge() {
            return j == 0;
        }
    }

    protected class RightMove extends Move {
        public RightMove(Integer i, Integer j) {
            super(i, j);
        }

        @Override
        public void makeStep() {
            ++j;
        }

        @Override
        public boolean isOnEdge() {
            return j == sizeOfField - 1;
        }
    }

    protected class DownMove extends Move {
        public DownMove(Integer i, Integer j) {
            super(i, j);
        }

        @Override
        public void makeStep() {
            ++i;
        }

        @Override
        public boolean isOnEdge() {
            return i == sizeOfField - 1;
        }
    }

    protected class UpLeftMove extends Move {
        public UpLeftMove(Integer i, Integer j) {
            super(i, j);
        }

        @Override
        public void makeStep() {
            --i;
            --j;
        }

        @Override
        public boolean isOnEdge() {
            return i == 0 || j == 0;
        }
    }

    protected class UpRightMove extends Move {
        public UpRightMove(Integer i, Integer j) {
            super(i, j);
        }

        @Override
        public void makeStep() {
            --i;
            ++j;
        }

        @Override
        public boolean isOnEdge() {
            return i == 0 || j == sizeOfField - 1;
        }
    }

    protected class DownLeftMove extends Move {
        public DownLeftMove(Integer i, Integer j) {
            super(i, j);
        }

        @Override
        public void makeStep() {
            ++i;
            --j;
        }

        @Override
        public boolean isOnEdge() {
            return i == sizeOfField - 1 || j == 0;
        }
    }

    protected class DownRightMove extends Move {
        public DownRightMove(Integer i, Integer j) {
            super(i, j);
        }

        @Override
        public void makeStep() {
            ++i;
            ++j;
        }

        @Override
        public boolean isOnEdge() {
            return i == sizeOfField - 1 || j == sizeOfField - 1;
        }
    }

    public void moveBack(Integer numOfMoving) {
        for (int i = 0; i < numOfMoving; i++) {
            if (isHistoryTablesEmpty()) {
                throw new IllegalArgumentException("there is no history :(");
            }
            setTable(popHistoryTables());
            changerOrderOnOpposite();
        }
    }


    protected boolean checkingAvailability(ArrayList<Move> moves) {
        for (int i = 0; i < 8; i++) {
            boolean wasOppositeColor = false;
            while (!moves.get(i).isOnEdge()) {
                moves.get(i).makeStep();
                if (getSquare(moves.get(i).i, moves.get(i).j).isOppositeColor(getOrder())) {
                    wasOppositeColor = true;
                } else {
                    break;
                }
            }
            if (wasOppositeColor && getSquare(moves.get(i).i, moves.get(i).j).isTheSameColor(getOrder())) {
                return true;
            }
        }
        return false;
    }

    protected ArrayList<Square> changeColorsOfSquares(Integer i, Integer j) {
        ArrayList<Square> changes = new ArrayList<>();
        changes.add(getSquare(i, j));
        var moves = getArrayMoves(i, j);
        for (int k = 0; k < 8; k++) {
            boolean wasOppositeColor = false;
            while (!moves.get(k).isOnEdge()) {
                moves.get(k).makeStep();
                if (getSquare(moves.get(k).i, moves.get(k).j).isOppositeColor(getOrder())) {
                    wasOppositeColor = true;
                } else {
                    break;
                }
            }
            if (wasOppositeColor && getSquare(moves.get(k).i, moves.get(k).j).isTheSameColor(getOrder())) {
                moves.get(k).i = i;
                moves.get(k).j = j;
                while (!moves.get(k).isOnEdge()) {
                    moves.get(k).makeStep();
                    if (getSquare(moves.get(k).i, moves.get(k).j).isOppositeColor(getOrder())) {
                        changes.add(getSquare(moves.get(k).i, moves.get(k).j));
                        getSquare(moves.get(k).i, moves.get(k).j).changeColorOnOpposite();
                    } else {
                        break;
                    }
                }
            }
        }
        return changes;
    }

    public Integer getScore(Integer numOfPlayer) {
        Integer score = 0;
        for (int i = 0; i < sizeOfField; i++) {
            for (int j = 0; j < sizeOfField; j++) {
                if (table[i][j].isTheSameColor(numOfPlayer)) {
                    ++score;
                }
            }
        }
        return score;
    }
}
