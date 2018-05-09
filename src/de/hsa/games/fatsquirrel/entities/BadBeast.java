package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.core.XY;

public class BadBeast extends Beast {
    public static final int ENERGY = -150;

    public BadBeast(int id, int energy, XY position) {
        super(id, energy, position);
    }
}
