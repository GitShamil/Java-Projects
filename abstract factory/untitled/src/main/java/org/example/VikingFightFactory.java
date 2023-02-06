package org.example;

import java.util.ArrayList;
import java.util.List;

/*factory for creating Fight of vikings*/
public class VikingFightFactory implements FightFactory{
    @Override
    public List<? extends Opponent> getOpponents() {
        List<Viking> vikings = new ArrayList<Viking>();
        vikings.add(new Viking("viking Vasya"));
        return vikings;
    }

    @Override
    public Ring getRing() {
        return new RingInMountains();
    }
}
