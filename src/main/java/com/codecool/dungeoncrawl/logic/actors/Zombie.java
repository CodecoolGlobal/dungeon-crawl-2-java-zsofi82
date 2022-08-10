package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;


public class Zombie extends Actor{
    int dx;
    int dy;
    public Zombie(Cell cell){
        super(cell);
        this.dx = 1;
        this.dy = 0;
    }

    @Override
    public void act() {
        Cell destination = cell.getNeighbour(dx, dy);

        if (destination.getType() == CellType.WALL) {
            dx = dx * -1;
        }
        if (actorCanMove(cell.getNeighbour(dx, dy))) {
            move(dx, dy);
        }
    }

    @Override
    public String getTileName(){
        return "zombie";
    }
}
