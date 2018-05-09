package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.console.MoveCommand;
import de.hsa.games.fatsquirrel.core.BoardView;

public interface UI {

    public MoveCommand getCommand();

    public void render(BoardView view);
}
