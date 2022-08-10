package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;


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
        if (getX() == 23) {
            dx = -1;
        } else if (getX() == 18) {
            dx = 1;
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
