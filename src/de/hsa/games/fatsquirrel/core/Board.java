package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.Game;
import de.hsa.games.fatsquirrel.collections.EntitySet;
import de.hsa.games.fatsquirrel.entities.*;

public class Board {
    private final BoardConfig config;
    private final EntitySet entitySet;
    // X - height, Y - width
    private final XY size;
    private int nextID = 0;


    public Board(BoardConfig config) {
        this.config = config;
        entitySet = new EntitySet();
        // board dimensions are increased to add walled borders
        size = new XY(config.getSize().getX() + 2, config.getSize().getY() + 2);
        populateBoard();
    }

    public BoardConfig getConfig() {
        return config;
    }

    public XY getSize() {
        return size;
    }

    public EntitySet getEntitySet() {
        return entitySet;
    }

    public FlattenedBoard flatten() {
        return new FlattenedBoard(this);
    }

    @Override
    public String toString() {
        return entitySet.toString();
    }

    private void populateBoard() {
        boolean[][] isOccupied = new boolean[config.getSize().getX()][config.getSize().getY()];
        // adding game entities
        // squirrels
        for (int i = 0; i < config.getSquirrelCount(); i++) {
            XY position = getRandomPosition(isOccupied);
            entitySet.add(new MasterSquirrel(nextID++, MasterSquirrel.STARTING_ENERGY, position));
        }
        // good beasts
        for (int i = 0; i < config.getGoodBeastCount(); i++) {
            XY position = getRandomPosition(isOccupied);
            entitySet.add(new GoodBeast(nextID++, GoodBeast.ENERGY, position));
        }
        // bad beasts
        for (int i = 0; i < config.getBadBeastCount(); i++) {
            XY position = getRandomPosition(isOccupied);
            entitySet.add(new BadBeast(nextID++, BadBeast.ENERGY, position));
        }
        // good plants
        for (int i = 0; i < config.getGoodPlantCount(); i++) {
            XY position = getRandomPosition(isOccupied);
            entitySet.add(new GoodPlant(nextID++, GoodPlant.ENERGY, position));
        }
        // bad plants
        for (int i = 0; i < config.getBadPlantCount(); i++) {
            XY position = getRandomPosition(isOccupied);
            entitySet.add(new BadPlant(nextID++, BadPlant.ENERGY, position));
        }
        // walls
        for (int i = 0; i < config.getWallCount(); i++) {
            XY position = getRandomPosition(isOccupied);
            entitySet.add(new Wall(nextID++, Wall.ENERGY, position));
        }
        // borders
        addBorders();
    }

    private void addBorders() {
        // horizontal borders
        for (int col = 0; col < size.getY(); col++) {
            entitySet.add(new Wall(nextID++, Wall.ENERGY, new XY(0, col)));
            entitySet.add(new Wall(nextID++, Wall.ENERGY, new XY(size.getX() - 1, col)));
        }
        // vertical borders
        for (int row = 1; row < size.getX() - 1; row++) {
            entitySet.add(new Wall(nextID++, Wall.ENERGY, new XY(row, 0)));
            entitySet.add(new Wall(nextID++, Wall.ENERGY, new XY(row, size.getY() - 1)));
        }
    }

    private XY getRandomPosition(boolean[][] isOccupied) {
        int row = Game.getRandom().nextInt(config.getSize().getX());
        int col = Game.getRandom().nextInt(config.getSize().getY());
        while (isOccupied[row][col]) {
            if ((col = (col + 1) % config.getSize().getY()) == 0) {
                row = (row + 1) % config.getSize().getX();
            }
        }
        isOccupied[row][col] = true;
        return new XY(row + 1, col + 1);
    }

    /*
    // Using a list of free positions to randomly choose the next one

    private List<XY> getPositionList() {
        List<XY> positionList = new ArrayList<>();
        for (int i = 1; i <= config.getSize().getX(); i++) {
            for (int j = 1; j <= config.getSize().getY(); j++) {
                positionList.add(new XY(i, j));
            }
        }
        return positionList;
    }

    private XY getRandomElement(List<XY> positionList) {
        int randomIndex = Game.getRandom().nextInt(positionList.size());
        XY position = positionList.get(randomIndex);
        Collections.swap(positionList, randomIndex, positionList.size() - 1);
        positionList.remove(positionList.size() - 1);
        return position;
    }
*/
}
