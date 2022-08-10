package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;

import java.io.Serializable;
import java.util.ArrayList;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;
    ArrayList<Actor> monsters = new ArrayList<>();

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        if (x < 0 || x >= cells.length || y < 0 || y >= cells[0].length) return null;
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Actor> getMonsters() {
        return monsters;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String listItems() {
        return String.join(", ", player.items);
    }
}
