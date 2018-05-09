package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.core.Board;
import de.hsa.games.fatsquirrel.core.BoardConfig;

public class Launcher {
    public static void main(String[] args) {
        BoardConfig config = new BoardConfig();
        Board board = new Board(config);
        State state = new State(board);

        System.out.println(config);
        System.out.println();
        System.out.println(board);
        System.out.println();
        System.out.println(state.getFlattenedBoard());
    }
}
