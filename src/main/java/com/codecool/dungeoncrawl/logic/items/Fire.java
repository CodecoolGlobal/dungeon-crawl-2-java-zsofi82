package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Fire extends Item{

    public Fire(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "fire";
    }
}
