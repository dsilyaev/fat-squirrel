package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.entities.EntityType;

public interface BoardView {
    XY getSize();
    EntityType getEntityType(XY position);
}
