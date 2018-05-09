package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.core.Board;
import de.hsa.games.fatsquirrel.core.FlattenedBoard;

public class State {
    private int highscore;
    private final Board board;
    private final FlattenedBoard flattenedBoard;

    public State(Board board) {
        this.board = board;
        flattenedBoard = board.flatten();
    }

    public void update() {}

    public FlattenedBoard getFlattenedBoard() {
        return flattenedBoard;
    }
}
