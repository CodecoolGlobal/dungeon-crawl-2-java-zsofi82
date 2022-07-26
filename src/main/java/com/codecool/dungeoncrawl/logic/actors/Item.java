package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Item implements Drawable {

    static Cell cell;

    public Item(Cell cell) {
        Item.cell = cell;
    }

    public static Cell getCell() {
        return cell;
    }

}
