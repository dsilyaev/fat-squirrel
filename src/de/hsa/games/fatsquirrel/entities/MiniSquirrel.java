package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.core.XY;

public class MiniSquirrel extends PlayerEntity {
    private MasterSquirrel patron;

    public MiniSquirrel(int id, int energy, XY position, MasterSquirrel patron) {
        super(id, energy, position);
        this.patron = patron;
    }

    public MasterSquirrel getPatron() {
        return patron;
    }
}
