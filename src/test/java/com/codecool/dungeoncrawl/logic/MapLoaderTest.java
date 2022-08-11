package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.items.Key;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class MapLoaderTest {

    @Test
    void emptyFile(){
        assertThrows(NoSuchElementException.class,() -> MapLoader.loadMap("/empty.txt"));
    }

    @Test
    void meatOnTheMap(){
        GameMap map = MapLoader.loadMap("/everyCharOnMap.txt");
        assertEquals("meat", map.getCell(8,0).getItem().getTileName());
    }

    @Test
    void keyOnTheMap(){
        GameMap map = MapLoader.loadMap("/everyCharOnMap.txt");
        assertEquals("key", map.getCell(6,0).getItem().getTileName());
    }

    @Test
    void wallOnTheMap(){
        GameMap map = MapLoader.loadMap("/everyCharOnMap.txt");
        assertEquals("wall", map.getCell(0,0).getType().getTileName());
    }

    @Test
    void floorOnTheMap(){
        GameMap map = MapLoader.loadMap("/everyCharOnMap.txt");
        assertEquals("floor", map.getCell(1,0).getType().getTileName());
    }

    @Test
    void swordOnTheMap(){
        GameMap map = MapLoader.loadMap("/everyCharOnMap.txt");
        assertEquals("sword", map.getCell(5,0).getItem().getTileName());
    }

    @Test
    void skeletonOnTheMap(){
        GameMap map = MapLoader.loadMap("/everyCharOnMap.txt");
        assertEquals("skeleton", map.getCell(2,0).getActor().getTileName());
    }

    @Test
    void fireOnTheMap(){
        GameMap map = MapLoader.loadMap("/everyCharOnMap.txt");
        assertEquals("fire", map.getCell(9,0).getItem().getTileName());
    }

    @Test
     void doorOnMap(){
        GameMap map = MapLoader.loadMap("/everyCharOnMap.txt");
        assertEquals("closedDoor", map.getCell(7,0).getType().getTileName());
    }



}
