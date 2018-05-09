package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.entities.*;

public interface EntityContext {
    XY getSize();
    EntityType getEntityType(XY position);
    void tryMove(MasterSquirrel masterSquirrel, XY moveDirection);
    void tryMove(MiniSquirrel miniSquirrel, XY moveDirection);
    void tryMove(GoodBeast goodBeast, XY moveDirection);
    void tryMove(BadBeast badBeast, XY moveDirection);
    PlayerEntity nearestPlayerEntity(XY position);
    void kill(Entity entity);
    void killAndReplace(Entity entity);
}
