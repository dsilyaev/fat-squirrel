package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.collections.EntitySet;
import de.hsa.games.fatsquirrel.core.XY;

import java.util.Objects;

public abstract class Entity {
    private final int id;
    private int energy;
    private XY position;

    protected Entity(int id, int energy, XY position) {
        this.id = id;
        this.energy = energy;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public int getEnergy() {
        return energy;
    }

    public XY getPosition() {
        return position;
    }

    public void setPosition(XY position) {
        this.position = position;
    }

    public void updateEnergy(int delta) {
        energy += delta;
    }

    @Override
    public String toString() {
        return String.format("%s [id=%d, energy=%d, position=%s]",
                getClass().getSimpleName(), id, energy, position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return id == entity.id &&
                energy == entity.energy &&
                Objects.equals(position, entity.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, energy, position);
    }

    public abstract void nextStep(EntitySet entities);
}
