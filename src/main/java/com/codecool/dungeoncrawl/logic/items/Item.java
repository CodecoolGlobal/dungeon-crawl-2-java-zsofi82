package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Item implements Drawable {
    Cell cell;

    private int x;

    private int y;
    public Item(Cell cell) {
        this.cell = cell;
    }
    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
