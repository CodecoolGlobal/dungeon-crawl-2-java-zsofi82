package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Meat extends Item{
    public Meat(Cell cell) {
        super(cell);
    }
    @Override
    public String getTileName() {
        return "meat";
    }
}
