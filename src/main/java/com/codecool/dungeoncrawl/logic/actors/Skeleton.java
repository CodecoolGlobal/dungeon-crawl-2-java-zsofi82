package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

import java.util.Random;

public class Skeleton extends Actor {
    Cell skeletonCell = getCell();
    public Skeleton(Cell cell) {
        super(cell);
    }

    public int[] generateRandomCoordinate() {
        Random random = new Random();
        int randomX = random.nextInt(3)-1 + getCell().getX();
        int randomY = random.nextInt(3)-1 + getCell().getY();
        return new int[] {randomX, randomY};
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = skeletonCell.getNeighbour(dx, dy);
        if (skeletonCanMove(nextCell)) {
            skeletonCell.setActor(null);
            nextCell.setActor(this);
            skeletonCell = nextCell;
        } else {
            skeletonCell.setActor(this);
        }
    }

    private boolean skeletonCanMove(Cell nextCell) {
        return nextCell.getType().equals(CellType.FLOOR)
                && !(nextCell.getActor() instanceof Player)
                && !(nextCell.getActor() instanceof Skeleton)
                && !(nextCell.getActor() instanceof Zombie);
    }
    @Override
    public String getTileName() {
        return "skeleton";
    }
}
