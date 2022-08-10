package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.items.Key;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    GameMap gameMap;
    Player player;
    Skeleton enemy;
    Key key;

    @BeforeEach
    void initGame() {
        this.gameMap = new GameMap(3, 3, CellType.FLOOR);
        this.player = new Player(gameMap.getCell(1, 1));
        this.enemy = new Skeleton(gameMap.getCell(0,1));
        this.key = new Key(gameMap.getCell(1, 2));
        gameMap.getCell(1, 2).setItem(key);
    }

    @Test
    void moveUpdatesCellAndPlayer() {
        player.move(0, 1);

        assertEquals(1, player.getX());
        assertEquals(2, player.getY());
        assertNull(gameMap.getCell(1, 1).getActor());
        assertEquals(player, gameMap.getCell(1, 2).getActor());
    }

    @Test
    void moveLeavesPlayerUnchangedWhenMovingToWall() {
        gameMap.getCell(2, 1).setType(CellType.WALL);
        player.move(1, 0);

        assertEquals(1, player.getX());
        assertEquals(1, player.getY());
    }

    @Test
    @Disabled
    void moveLeavesPlayerUnchangedWhenMovingOutOfMap() {
        player.move(1, 0);
        player.move(1, 0);

        assertEquals(2, player.getX());
        assertEquals(1, player.getY());
    }

    @Test
    void moveLeavesPlayerUnchangedWhenMovingIntoActor() {
        player.move(-1, 0);

        assertEquals(1, player.getX());
        assertEquals(1, player.getY());
    }

    @Test
    void moveAttacksActorWhenMovingIntoActor() {
        player.setAttack();
        player.move(-1, 0);

        assertEquals(-5, enemy.getHealth());
    }

    @Test
    void movePicksUpKey() {
        player.move(0, 1);

        assertNull(gameMap.getCell(1, 2).getItem());
        assertTrue(player.items.contains("key"));
    }

    @Test
    void addInventoryOnCellWithNoItemThrowsNullpointer() {
        assertThrows(
            NullPointerException.class,
            () -> player.addInventory(gameMap.getCell(1, 1))
        );
    }
}
