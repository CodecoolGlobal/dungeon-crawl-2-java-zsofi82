package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;


public class Zombie extends Actor{

    Cell zombieCell = getCell();
    public Zombie(Cell cell){
        super(cell);
    }

//    @Override
//    public void move(int dx, int dy) {
//        Cell nextCell = zombieCell.getNeighbour(dx, dy);
//        if (zombieCanMove(nextCell)) {
//            zombieCell.setActor(null);
//            nextCell.setActor(this);
//            zombieCell = nextCell;
//        } else {
//            zombieCell.setActor(this);
//        }
//        System.out.println(getX());
//    }

    private boolean zombieCanMove(Cell nextCell) {
        return nextCell.getType().equals(CellType.FLOOR) && !(nextCell.getActor() instanceof Skeleton);
    }
    @Override
    public String getTileName(){
        return "zombie";
    }
}
