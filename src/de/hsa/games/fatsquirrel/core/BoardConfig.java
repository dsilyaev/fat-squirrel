package de.hsa.games.fatsquirrel.core;

public class BoardConfig {
    public static final XY BOARD_SIZE = new XY(10, 10);
    public static final int SQUIRREL_COUNT = 1;
    public static final int GOOD_BEAST_COUNT = 3;
    public static final int BAD_BEAST_COUNT = 3;
    public static final int GOOD_PLANT_COUNT = 10;
    public static final int BAD_PLANT_COUNT = 10;
    public static final int WALL_COUNT = 20;
    // X - rows, Y - columns
    private final XY size;
    private final int squirrelCount;
    private final int goodBeastCount;
    private final int badBeastCount;
    private final int goodPlantCount;
    private final int badPlantCount;
    private final int wallCount;

    public BoardConfig() {
        size = BOARD_SIZE;
        squirrelCount = SQUIRREL_COUNT;
        goodBeastCount = GOOD_BEAST_COUNT;
        badBeastCount = BAD_BEAST_COUNT;
        goodPlantCount = GOOD_PLANT_COUNT;
        badPlantCount = BAD_PLANT_COUNT;
        wallCount = WALL_COUNT;
    }

    public BoardConfig(XY size, int squirrelCount,
                       int goodBeastCount, int badBeastCount,
                       int goodPlantCount, int badPlantCount, int wallCount) {
        if (size.getX() <= 0 || size.getY() <= 0) {
            throw new IllegalArgumentException();
        }
        int entityCount = squirrelCount + goodBeastCount + badBeastCount
                + goodPlantCount + badPlantCount + wallCount;
        if (entityCount < (size.getX() * size.getY())) {
            throw new IllegalArgumentException();
        }
        this.size = size;
        this.squirrelCount = squirrelCount;
        this.goodBeastCount = goodBeastCount;
        this.badBeastCount = badBeastCount;
        this.goodPlantCount = goodPlantCount;
        this.badPlantCount = badPlantCount;
        this.wallCount = wallCount;
    }

    public XY getSize() {
        return size;
    }

    public int getSquirrelCount() {
        return squirrelCount;
    }

    public int getGoodBeastCount() {
        return goodBeastCount;
    }

    public int getBadBeastCount() {
        return badBeastCount;
    }

    public int getGoodPlantCount() {
        return goodPlantCount;
    }

    public int getBadPlantCount() {
        return badPlantCount;
    }

    public int getWallCount() {
        return wallCount;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Size:        %s\n", size));
        builder.append(String.format("Squirrels:   %d\n", squirrelCount));
        builder.append(String.format("Good Beasts: %d\n", goodBeastCount));
        builder.append(String.format("Bad Beasts:  %d\n", badBeastCount));
        builder.append(String.format("Good Plants: %d\n", goodPlantCount));
        builder.append(String.format("Bad Plants:  %d\n", badPlantCount));
        builder.append(String.format("Walls:       %d\n", wallCount));
        return builder.toString();
    }
}
