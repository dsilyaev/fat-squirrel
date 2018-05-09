package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.core.XY;

public class BadPlant extends Plant {
    public static final int ENERGY = -100;

    public BadPlant(int id, int energy, XY position) {
        super(id, energy, position);
    }
}
