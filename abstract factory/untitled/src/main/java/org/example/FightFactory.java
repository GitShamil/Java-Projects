package org.example;

import java.util.List;

/*Abstract Factory for creating Opponents for fight and getting ring for fight*/
public interface FightFactory {

    // get opponents for fight
    List<? extends Opponent> getOpponents();
    // get ring
    Ring getRing();
}
