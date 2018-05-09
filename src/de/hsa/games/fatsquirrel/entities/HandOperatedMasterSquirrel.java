package de.hsa.games.fatsquirrel.entities;

import de.hsa.games.fatsquirrel.GameLogic.GameLogic;
import de.hsa.games.fatsquirrel.collections.EntitySet;
import de.hsa.games.fatsquirrel.core.FlattenedBoard;
import de.hsa.games.fatsquirrel.core.XY;

import java.util.Scanner;

public class HandOperatedMasterSquirrel extends MasterSquirrel {
    private static final char UP = 'w';
    private static final char DOWN = 's';
    private static final char LEFT = 'a';
    private static final char RIGHT = 'd';
    private XY previousPosition;
    public HandOperatedMasterSquirrel(int id, int energy, XY position) {
        super(id, energy, position);
    }

    @Override
    public void nextStep(EntitySet entities) {
        previousPosition = getPosition(); //added this to help out with collision Wall
       if(checkAwake(this)) {
           System.out.println("You are able to move in: " + getAwake());
           setAwake(getAwake() - 1);
       }
        else {
           System.out.print("> ");
           Scanner scanner = new Scanner(System.in);
           String input = scanner.nextLine();
           XY vector = new XY(0, 0);
           if (input.length() == 1) {
               char ch = input.charAt(0);
               switch (ch) {
                   case UP:
                       vector = new XY(-1, 0);
                       break;
                   case DOWN:
                       vector = new XY(1, 0);
                       break;
                   case LEFT:
                       vector = new XY(0, -1);
                       break;
                   case RIGHT:
                       vector = new XY(0, 1);
                       break;
                   default:
                       vector = new XY(0, 0);
                       break;
               }
           }
           setPosition(getPosition().move(vector));
       }
    }

    public XY getPreviousPosition() {
        return previousPosition;
    }

    public void setPreviousPosition(XY previousPosition) {
        this.previousPosition = previousPosition;
    }
}
