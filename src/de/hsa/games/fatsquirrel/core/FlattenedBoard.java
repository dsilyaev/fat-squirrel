package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.collections.EntitySet;
import de.hsa.games.fatsquirrel.entities.*;

public class FlattenedBoard implements BoardView, EntityContext {
    private final Board board;
    private final Entity[][] gameField;
    private final XY size;

    public FlattenedBoard(Board board) {
        this.board = board;
        size = board.getSize();
        gameField = new Entity[size.getX()][size.getY()];
        EntitySet entities = board.getEntitySet();
        for (int i = 0; i < entities.getSize(); i++) {
            Entity entity = entities.get(i);
            gameField[entity.getPosition().getX()][entity.getPosition().getY()] = entity;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size.getX(); i++) {
            for (int j = 0; j < size.getY(); j++) {
                switch (getEntityType(new XY(i, j))) {
                    case MASTER_SQUIRREL:
                        builder.append('S');
                        break;
                    case MINI_SQUIRREL:
                        builder.append('s');
                        break;
                    case GOOD_BEAST:
                        builder.append('G');
                        break;
                    case BAD_BEAST:
                        builder.append('B');
                        break;
                    case GOOD_PLANT:
                        builder.append('+');
                        break;
                    case BAD_PLANT:
                        builder.append('-');
                        break;
                    case WALL:
                        builder.append('#');
                        break;
                    case NONE:
                    default:
                        builder.append(' ');
                        break;
                }
                if (j < size.getY() - 1) {
                    builder.append(' ');
                }
            }
            builder.append('\n');
        }
        return builder.toString();
    }

    @Override
    public XY getSize() {
        return size;
    }

    @Override
    public EntityType getEntityType(XY position) {
        if ((position.getX() < 0 || position.getX() >= size.getX())
                || (position.getY() < 0 || position.getX() >= size.getY())) {
            return EntityType.NONE;
        }
        Entity entity = gameField[position.getX()][position.getY()];
        if (entity instanceof MasterSquirrel) {
            return EntityType.MASTER_SQUIRREL;
        }
        else if (entity instanceof MiniSquirrel) {
            return EntityType.MINI_SQUIRREL;
        }
        else if (entity instanceof GoodBeast) {
            return EntityType.GOOD_BEAST;
        }
        else if (entity instanceof BadBeast) {
            return EntityType.BAD_BEAST;
        }
        else if (entity instanceof GoodPlant) {
            return EntityType.GOOD_PLANT;
        }
        else if (entity instanceof BadPlant) {
            return EntityType.BAD_PLANT;
        }
        else if (entity instanceof Wall) {
            return EntityType.WALL;
        }
        return EntityType.NONE;
    }

    @Override
    public void tryMove(MasterSquirrel masterSquirrel, XY moveDirection) {
        XY test = moveDirection.getRandomVector();
        masterSquirrel.setPosition(test);
        resolveActiveCollision(masterSquirrel);


    }

    @Override
    public void tryMove(MiniSquirrel miniSquirrel, XY moveDirection) {
        XY test = moveDirection.getRandomVector();
        miniSquirrel.setPosition(test);
        resolveActiveCollision(miniSquirrel);

    }

    @Override
    public void tryMove(GoodBeast goodBeast, XY moveDirection) {

            XY test = moveDirection.getRandomVector();
            goodBeast.setPosition(test);
            resolvePassiveCollision(goodBeast);
            if(goodBeast.getPosition() == goodBeast.getPreviousPosition()){

            }
            else{
                goodBeast.setPosition(goodBeast.getPreviousPosition());
                goodBeast.nextStep(test);
            }

    }

    @Override
    public void tryMove(BadBeast badBeast, XY moveDirection) {
        XY test = moveDirection.getRandomVector();
        badBeast.setPosition(test);
        resolvePassiveCollision(badBeast);
        if(badBeast.getPosition() == badBeast.getPreviousPosition()){

        }
        else{
            badBeast.setPosition(badBeast.getPreviousPosition());
            badBeast.nextStep(test);
        }

    }

    @Override
    public void kill(Entity entity) {
        for(int i = 0; i < board.getEntitySet().getSize(); i++){
            if(entity.getPosition().equals(board.getEntitySet().get(i).getPosition())){
                board.getEntitySet().remove(i);
                break;
            }
        }

    }

    @Override
    public void killAndReplace(Entity entity) {
        for(int i = 0; i < board.getEntitySet().getSize(); i++){
            if(entity.getPosition().equals(board.getEntitySet().get(i).getPosition())){
                board.getEntitySet().remove(i);
                int id = (int)entity.getId();
                boolean[][] isOccupied = new boolean[board.getConfig().getSize().getX()][board.getConfig().getSize().getY()];
                XY position = board.getRandomPosition(isOccupied);
             if(entity instanceof BadBeast){
                 entity = new BadBeast(id ,entity.getEnergy(), position);
                 break;
             }
             else if(entity instanceof BadPlant){
                 entity = new BadPlant(id ,entity.getEnergy(), position);
                 break;
             }
             else if(entity instanceof GoodPlant){
                 entity = new GoodPlant(id ,entity.getEnergy(), position);
                 break;
             }
             else if(entity instanceof GoodBeast){
                 entity = new GoodBeast(id ,entity.getEnergy(), position);
                 break;
             }



            }
        }


    }


    public void resolvePassiveCollision(Beast beast){

        for(int i = 0;i <board.getEntitySet().getSize(); i++) {

            if (beast.equals(board.getEntitySet().get(i))) {
                i++;
            }
            Entity entity = board.getEntitySet().get(i);
            if (beast.getPosition().equals(entity.getPosition()) && (entity instanceof Beast || entity instanceof Plant || entity instanceof Wall)) {
                beast.setPosition(beast.getPreviousPosition());
                break;
            }
        }


                /*lse if((entity instanceof GoodBeast || entity instanceof GoodPlant) && (entity1 instanceof BadBeast || entity1 instanceof BadPlant) && entity.getPosition().equals(entity1.getPosition())){
                    entity1.updateEnergy(-entity.getEnergy());
                    entitySet.remove(i);
                }else if((entity instanceof BadBeast || entity instanceof BadPlant) && (entity1 instanceof GoodBeast || entity1 instanceof GoodPlant) && entity.getPosition().equals(entity1.getPosition())){
                    entity.updateEnergy(-entity1.getEnergy());
                    entitySet.remove(j);
                }else if((entity instanceof Beast && entity1 instanceof Wall ) && entity.getPosition().equals(entity1.getPosition())){
                    entity.setPosition(((Beast) entity).getPreviousPosition());
                }*/


            }

    public void resolveActiveCollision(Squirrel squirrel) {
        for (int i = 0; i < board.getEntitySet().getSize(); i++) {
            Entity entity = board.getEntitySet().get(i);
            if (isSquirrelAlive(squirrel)) {
                if ((entity instanceof Plant || entity instanceof Beast) && squirrel.getPosition().equals(entity.getPosition())) {
                    squirrel.updateEnergy(entity.getEnergy());
                    if((entity.getBite() > 0) && (entity instanceof BadBeast) ){
                        int bite = entity.getBite();
                        entity.setBite(bite--);
                       break;
                    }
                    killAndReplace(entity);
                    break;
                } else if (entity instanceof Wall && squirrel.getPosition().equals(entity.getPosition())) {
                    // Maybe call a method to print out smth like : "Oh it is a wall, you are not ready to travel to another dimension"
                    squirrel.setPosition(squirrel.getPreviousPosition());
                    squirrel.setAwake(3);
                    break;
                }
            }
        }
    }

    private boolean isSquirrelAlive(Squirrel squirrel){
        if(squirrel.getEnergy() > 0)
            return true;
        else
            return false;
    }

    private boolean locateNearSquirrel(BadBeast badBeast){

        for(int i = 6; i >= -6; i--){
            int x = badBeast.getPosition().getX() + i;
            for(int j = 6; j >= -6; j--){
                int y = badBeast.getPosition().getY() + i;
                if(checkEntity(gameField, x,y)){
                 return true;

                }
            }


        }
        return false;
    }
    private boolean checkEntity(Entity[][] gameField, int x, int y){
        if(gameField[x][y] instanceof MasterSquirrel){
            return true;
        }

    }
    private boolean checkMonsterBites(BadBeast badbeast){
        if(badbeast.getBite() > 0 ){
            return false;
        }
        else{
            return true;
        }

    }
}
