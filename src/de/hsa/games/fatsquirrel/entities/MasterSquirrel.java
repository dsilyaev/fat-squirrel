package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.core.XY;

public class MasterSquirrel extends PlayerEntity {
    public static final int STARTING_ENERGY = 1000;

    public MasterSquirrel(int id, int energy, XY position) {
        super(id, energy, position);
    }

    public boolean isOwned(Entity entity) {
        return (entity instanceof MiniSquirrel)
                && (((MiniSquirrel) entity).getPatron() == this);
    }

    public MiniSquirrel spawnMiniSquirrel(int id, int energy) {
        if (energy < 0) {
            energy = 0;
        }
        if (energy > getEnergy()) {
            energy = getEnergy();
        }
        updateEnergy(-energy);
        return new MiniSquirrel(id, energy, getPosition(), this);
    }
}
