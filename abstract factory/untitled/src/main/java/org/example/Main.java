package org.example;

public class Main {
    public static void main(String[] args) {
        FightFactory ninjaFightFactory = new JapaneseFightFactory();
        for(Opponent opponent: ninjaFightFactory.getOpponents()){
            System.out.println(opponent.name);
        }
    }
}