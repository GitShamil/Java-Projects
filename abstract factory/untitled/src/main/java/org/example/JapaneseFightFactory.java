package org.example;

import java.util.ArrayList;
import java.util.List;

/*Factory for creating Fight in Japan*/
public class JapaneseFightFactory implements FightFactory{

    // get ninjas for fight
    @Override
    public List<? extends Opponent> getOpponents() {
        List<Ninja> ninjas = new ArrayList<Ninja>();
        ninjas.add(new Ninja("kik"));
        return ninjas;
    }

    // get ring made of bamboo for fight in Japan
    @Override
    public Ring getRing() {
        return new RingFromBamboo();
    }
}
