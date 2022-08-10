package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.actors.Zombie;
import com.codecool.dungeoncrawl.logic.items.Key;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ZombieTest {
    GameMap gameMap;
    Zombie zombie;
    Player player;
    Skeleton skeleton;
    Key key;

    @BeforeEach
    void initGame() {
        this.gameMap = new GameMap(25, 20, CellType.FLOOR);
        this.player = new Player(gameMap.getCell(1, 1));
        this.zombie = new Zombie(gameMap.getCell(20, 7));
        this.skeleton = new Skeleton(gameMap.getCell(0,1));
        this.key = new Key(gameMap.getCell(1, 2));
        gameMap.getCell(1, 2).setItem(key);
    }


    @Test
    void actChangeDirectionOfZombieWhenMovingIntoWall() {
        gameMap.getCell(24, 7).setType(CellType.WALL);
        zombie.move(3, 0);
        zombie.act();
        System.out.println(zombie.getCell().getType());
        System.out.println(zombie.getX());

        int expected = 22;
        int result = zombie.getX();
        assertEquals(expected, result);
    }

}
