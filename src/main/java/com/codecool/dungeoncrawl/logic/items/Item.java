package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Item implements Drawable {
    Cell cell;
    public Item(Cell cell) {
        this.cell = cell;
    }
    public Cell getCell() {
        return cell;
    }

}
