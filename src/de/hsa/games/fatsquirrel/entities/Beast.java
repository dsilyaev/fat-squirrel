package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.collections.EntitySet;
import de.hsa.games.fatsquirrel.core.XY;

public abstract class Beast extends Entity{

    public Beast(int id, int energy, XY position) {
        super(id, energy, position);
    }

    @Override
    public void nextStep(EntitySet entities) {
        setPosition(getPosition().move(XY.getRandomVector()));
    }
}
