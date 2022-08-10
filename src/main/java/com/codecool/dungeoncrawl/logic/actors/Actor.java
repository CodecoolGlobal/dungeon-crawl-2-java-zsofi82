package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

import java.io.Serializable;

public abstract class Actor implements Drawable{

    protected Cell cell;
    private int health = 5;


    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbour(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health){
        this.health = health;
        System.out.println("hit: "   + this.health);
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }


    public int getY() {
        return cell.getY();
    }

    public abstract void act();

    boolean actorCanMove(Cell nextCell) {
        return nextCell.getType().equals(CellType.FLOOR)
                && !(nextCell.getActor() instanceof Player)
                && !(nextCell.getActor() instanceof Skeleton)
                && !(nextCell.getActor() instanceof Zombie);
    }
}
