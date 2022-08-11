package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameMapTest {
    GameMap gameMap;
    int width;
    int height;
    Cell[][] cells;
    Player player;

    @BeforeEach
    void initGameState() {
        this.gameMap = new GameMap(3, 3, CellType.FLOOR);

    }

    @Test
    public void returnNullIfCellCoordinatesAreNotInTheMap() {
        width = -1;

        Cell expected = null;
        Cell result = gameMap.getCell(width, height);

        assertEquals(expected, result);
    }

    @Test
    public void listItemsReturnCorrectString() {
        player = new Player(gameMap.getCell(2, 2));
        gameMap.setPlayer(player);
        player.items.add("sword");
        player.items.add("meat");

        String expected = "sword, meat";
        String result = gameMap.listItems();

        assertEquals(expected, result);
    }

}
