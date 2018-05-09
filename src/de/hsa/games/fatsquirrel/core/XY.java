package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.Game;

import java.util.Objects;
import java.util.Random;

public final class XY {
    private final int x;
    private final int y;

    public static XY getRandomVector() {
        // generate a random number from the interval (-1, 0, 1)
        // dx and dy are allowed to both be equal 0 (entity stays in place)
        // should be rewritten with the consideration of grid borders
        int dx = Game.getRandom().nextInt(3) - 1;
        int dy = Game.getRandom().nextInt(3) - 1;
        return new XY(dx, dy);
    }

    public XY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XY xy = (XY) o;
        return x == xy.getX() &&
                y == xy.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public XY move(XY vector) {
        return new XY(x + vector.getX(), y + vector.getY());
    }
}
