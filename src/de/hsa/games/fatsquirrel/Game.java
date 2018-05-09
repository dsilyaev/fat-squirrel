package de.hsa.games.fatsquirrel;

import java.util.Random;

public abstract class Game {
    private static final Random random = new Random();
    private State state;

    public Game(State state) {
        this.state = state;
    }

    public void run() {
        while (true) {
            render();
            processInput();
            update();
        }
    }

    public static Random getRandom() {
        return random;
    }

    protected abstract void render();
    protected abstract void processInput();
    protected abstract void update();

}
