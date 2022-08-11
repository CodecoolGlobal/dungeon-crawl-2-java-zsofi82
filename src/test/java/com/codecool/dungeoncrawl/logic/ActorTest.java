package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActorTest {
    GameMap gameMap;
    Player player;
    Skeleton skeleton;

    @BeforeEach
    void initGameState() {
        this.gameMap = new GameMap(3, 3, CellType.FLOOR);
        this.player = new Player(gameMap.getCell(1, 1));
        this.skeleton = new Skeleton(gameMap.getCell(1, 2));
    }

    @Test
    void moveUpdatesCells() {
        player.move(1, 0);

        assertEquals(2, player.getX());
        assertEquals(1, player.getY());
        assertNull(gameMap.getCell(1, 1).getActor());
        assertEquals(player, gameMap.getCell(2, 1).getActor());
    }

    @Test
    void cannotMoveIntoWall() {
        gameMap.getCell(2, 1).setType(CellType.WALL);
        player.move(1, 0);

        assertEquals(1, player.getX());
        assertEquals(1, player.getY());
    }

    @Test
    @Disabled
    void cannotMoveOutOfMap() {
        player.move(1, 0);
        player.move(1, 0);

        assertEquals(2, player.getX());
        assertEquals(1, player.getY());
    }

    @Test
    void cannotMoveIntoAnotherActor() {
        player.move(0, 1);

        assertEquals(1, player.getX());
        assertEquals(1, player.getY());
        assertEquals(1, skeleton.getX());
        assertEquals(2, skeleton.getY());
        assertEquals(skeleton, gameMap.getCell(1, 2).getActor());
    }
}