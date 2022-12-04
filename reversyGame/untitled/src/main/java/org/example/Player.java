package org.example;

/*
 * abstract class of player
 * */
public abstract class Player {
    protected Board board;

    protected Integer numberOfPlayer;

    //    order of player
    public Integer getNumberOfPlayer() {
        return numberOfPlayer;
    }

    public void setNumberOfPlayer(Integer numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
    }

    public Player(Integer numberOfPlayer, Board board) {
        setNumberOfPlayer(numberOfPlayer);
        this.board = board;
    }

    //    getScore of this player
    public Integer getScore() {
        return board.getScore(getNumberOfPlayer());
    }

    // make step
    public abstract void makeMotion();

}
