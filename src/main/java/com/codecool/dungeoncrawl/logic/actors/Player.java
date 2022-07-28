package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

import java.util.ArrayList;
import java.util.List;


public class Player extends Actor {
    public List<String> items = new ArrayList<>();

    public int attack = 0;
    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }
    Cell playerCell = getCell();
    @Override
    public void move(int dx, int dy) {
        Cell nextCell = playerCell.getNeighbour(dx, dy);
        final boolean isNextCellSkeleton = nextCell.getActor() instanceof Skeleton;
        final boolean isNextCellZombie = nextCell.getActor() instanceof Zombie;
        final boolean isNextCellFloor = nextCell.getType().equals(CellType.FLOOR);
        final boolean isNextCellOpenDoor = nextCell.getType().equals(CellType.OPENDOOR);

        if (isNextCellFloor || isNextCellOpenDoor) {
            if (attack > 0 && (isNextCellSkeleton || isNextCellZombie)) {
                nextCell.getActor().setHealth(nextCell.getActor().getHealth() - attack);
                attack -= 5;
                playerCell.setActor(null);
                nextCell.setActor(this);
                playerCell = nextCell;
                if (playerCell.getItem() != null) {
                    addInventory(playerCell);
                    playerCell.setItem(null);
                }
            } else if (!(isNextCellSkeleton || isNextCellZombie)) {
                playerCell.setActor(null);
                nextCell.setActor(this);
                playerCell = nextCell;
                if (playerCell.getItem() != null) {
                    addInventory(playerCell);
                    playerCell.setItem(null);
                }
            }
        } else {
            playerCell.setActor(this);
        }
    }

    private boolean playerCanMove(Cell nextCell) {
        return (nextCell.getType().equals(CellType.FLOOR)
                || nextCell.getType().equals(CellType.OPENDOOR))
                && !(nextCell.getActor() instanceof Skeleton)
                && !(nextCell.getActor() instanceof Zombie);
    }

    public void addInventory(Cell playerCell) {
        if(playerCell.getItem().getTileName() == "sword"){
            setAttack();
        } else {
            items.add(playerCell.getItem().getTileName());
        }
        //System.out.println(items);
    }

    public void setAttack() {
        this.attack += 10;
    }

    public int getAttack() {
        return this.attack;
    }
}
