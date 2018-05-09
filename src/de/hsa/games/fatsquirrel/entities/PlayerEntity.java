package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.collections.EntitySet;
import de.hsa.games.fatsquirrel.core.XY;

public class PlayerEntity extends Entity {

    public PlayerEntity(int id, int energy, XY position) {
        super(id, energy, position);
    }

    @Override
    public void nextStep(EntitySet entities) {}
}
