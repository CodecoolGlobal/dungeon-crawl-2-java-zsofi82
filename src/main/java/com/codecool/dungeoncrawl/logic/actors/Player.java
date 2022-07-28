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
    //Cell playerCell = getCell();
    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbour(dx, dy);
        if (playerCanMove(nextCell)) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
            if (cell.getItem() != null) {
                addInventory(cell);
                cell.setItem(null);
            }
        } else {
            cell.setActor(this);
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
        System.out.println(items);
    }


    public void setAttack() {
        this.attack += 10;
    }

    public int getAttack() {
        return this.attack;
    }

}
