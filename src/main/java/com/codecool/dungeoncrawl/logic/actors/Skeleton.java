package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.Random;

public class Skeleton extends Actor {
    public Skeleton(Cell cell) {
        super(cell);
    }

    public int[] generateRandomDirection() {
        Random random = new Random();
        int randomX = random.nextInt(3)-1;
        int randomY = random.nextInt(3)-1;
        return new int[] {randomX, randomY};
    }

    @Override
    public void act() {
        int[] relativeCoordinate = generateRandomDirection();
        Cell destination = cell.getNeighbour(relativeCoordinate[0], relativeCoordinate[1]);
        if (actorCanMove(destination)) {
            move(relativeCoordinate[0], relativeCoordinate[1]);
        }
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
