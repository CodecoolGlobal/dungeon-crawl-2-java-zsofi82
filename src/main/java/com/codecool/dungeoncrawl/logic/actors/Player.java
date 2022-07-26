package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;


public class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }
    Cell PlayerCell = getCell();
    @Override
    public void move(int dx, int dy) {
        Cell nextCell = PlayerCell.getNeighbour(dx, dy);
        System.out.println(nextCell.getType());
        if (nextCell.getType().equals(CellType.FLOOR) && !(nextCell.getActor() instanceof Skeleton)) {
            PlayerCell.setActor(null);
            nextCell.setActor(this);
            PlayerCell = nextCell;
        } else {
            PlayerCell.setActor(this);
        }
    }

//    public void pickUp() {
//        if (PlayerCell.equals(Item.getCell())) {
//
//        }
//    }
}
