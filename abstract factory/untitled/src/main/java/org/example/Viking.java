package org.example;

/*the concrete opponent (viking)*/
public class Viking extends Opponent{
    public Viking(String name) {
        super(name);
    }
    @Override
    public void fight(){
        System.out.println("panching with hammer");
    }
}
