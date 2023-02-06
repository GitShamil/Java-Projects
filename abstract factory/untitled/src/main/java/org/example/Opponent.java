package org.example;

/*abstract class fight opponent*/
public abstract class Opponent {
    String name;
    public Opponent(String name){
        this.name = name;
    }

    public void fight(){
        System.out.println("panching");
    }
}
