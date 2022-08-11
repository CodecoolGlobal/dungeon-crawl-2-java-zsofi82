package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameMapTest {
    GameMap gameMap;
    int width;
    int height;
    Cell[][] cells;

    @BeforeEach
    void initGameState() {
        this.gameMap = new GameMap(3, 3, CellType.FLOOR);
    }

    @Test
    public void returnNullIfCellCoordinatesAreNotInTheMap() {
        cells = new Cell[width][height];
        this.width = -1;

        Cell expected = null;
        Cell result = gameMap.getCell(width, height);

        assertEquals(expected, result);
    }
}
