package de.hsa.games.fatsquirrel.collections;

import de.hsa.games.fatsquirrel.entities.Entity;

public class EntitySet {
    public static final int MAX_SIZE = 256;
    private final Entity[] array = new Entity[MAX_SIZE];
    private int size;

    public int getSize() {
        return size;
    }

    public Entity get(int index) {
        if (index >= array.length) {
            return null;
        }
        return array[index];
    }

    public boolean add(Entity entity) {
        if (size == array.length) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (array[i].equals(entity)) {
                return false;
            }
        }
        array[size] = entity;
        size++;
        return true;
    }

    public boolean remove(Entity entity) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(entity)) {
                return remove(i);
            }
        }
        return false;
    }

    public boolean remove(int index) {
        if (array[index] == null) {
            return false;
        }
        System.arraycopy(array, index + 1, array, index, size - (index + 1));
        array[size - 1] = null;
        size--;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        for (int i = 0; i < size; i++) {
            builder.append("\t");
            builder.append(array[i]);
            if (i < size - 1) {
                builder.append(",\n");
            }
        }
        builder.append("\n}");
        return builder.toString();
    }

    public void nextStep() {
        for (int i = 0; i < size; i++) {
            array[i].nextStep(this);
        }
    }
}
