package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

import java.util.ArrayList;
import java.util.List;


public class Player extends Actor {
    public List<String> items = new ArrayList<>();
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
        if (nextCell.getType().equals(CellType.FLOOR) && !(nextCell.getActor() instanceof Skeleton)) {
            PlayerCell.setActor(null);
            nextCell.setActor(this);
            PlayerCell = nextCell;
            if (PlayerCell.getItem() != null) {
                addInventory();
                PlayerCell.setItem(null);
            }
        } else {
            PlayerCell.setActor(this);
        }
    }

    public void addInventory() {
        if (PlayerCell.getItem() != null) {
            items.add(PlayerCell.getItem().getTileName());
        }
        System.out.println(items);
    }
}
