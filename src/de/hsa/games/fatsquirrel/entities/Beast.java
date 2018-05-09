package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.collections.EntitySet;
import de.hsa.games.fatsquirrel.core.XY;

public abstract class Beast extends Entity{
    private XY previousPosition; // added this change
    private int moveCounter = 4;

    public Beast(int id, int energy, XY position) {
        super(id, energy, position);
    }

    public void nextStep(XY vektor) {

        previousPosition = getPosition();
        if(moveCounter == 0){
            setPosition(getPosition().move(vektor));
            moveCounter = 4;
        }
        else{
            moveCounter--;
        }

    }

    public XY getPreviousPosition() {
        return previousPosition;
    }

    public int getMoveCounter() {
        return moveCounter;
    }

    public void setMoveCounter(int moveCounter) {
        this.moveCounter = moveCounter;
    }
}
