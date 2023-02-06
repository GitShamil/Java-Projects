package org.example;

/*concrete opponent(ninja) for fighting in Japan*/
public class Ninja extends Opponent{
    public Ninja(String name) {
        super(name);
    }
    @Override
    public void fight(){
        System.out.println("panching with nonchaki");
    }
}
