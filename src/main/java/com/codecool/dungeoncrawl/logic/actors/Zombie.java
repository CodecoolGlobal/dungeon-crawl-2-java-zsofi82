package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;


public class Zombie extends Actor{

    Cell zombieCell = getCell();
    public Zombie(Cell cell){
        super(cell);
    }

    @Override
    public String getTileName(){
        return "zombie";
    }
}
