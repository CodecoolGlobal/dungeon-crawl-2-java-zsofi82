package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SkeletonTest {
    GameMap gameMap;
    Skeleton skeleton;

    @BeforeEach
    public void initGame() {
        this.gameMap = new GameMap(5, 5, CellType.FLOOR);
        this.skeleton = new Skeleton(gameMap.getCell(2, 2));
    }

    @Test
    public void moveSkeletonIfCanMove() {
        int starterX = skeleton.getX();
        int starterY = skeleton.getY();
        skeleton.act();

        boolean expected = true;
        boolean result = starterX != skeleton.getX() || starterY != skeleton.getY();

        assertEquals(expected, result);
    }

    @Test
    public void moveSkeletonOneStepIfCanMove() {
        int starterX = skeleton.getX();
        int starterY = skeleton.getY();
        skeleton.act();

        boolean expected = true;
        boolean result = starterX == skeleton.getX()+1 ||
                starterY == skeleton.getY()+1 ||
                starterX == skeleton.getX()-1 ||
                starterY == skeleton.getY()-1;

        assertEquals(expected, result);
    }
}
