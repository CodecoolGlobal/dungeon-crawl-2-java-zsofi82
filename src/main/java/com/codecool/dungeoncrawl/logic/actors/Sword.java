package com.codecool.dungeoncrawl.logic.actors;


import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Sword extends Item{


    public Sword(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "sword";
    }
}
