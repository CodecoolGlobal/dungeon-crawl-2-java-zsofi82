package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

import java.util.ArrayList;
import java.util.List;


public class Player extends Actor {
    public List<String> items = new ArrayList<>();

    private int playerHealth = 10;

    public int attack = 0;

    private String name;
    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }
    //Cell playerCell = getCell();
    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbour(dx, dy);
        final boolean isNextCellSkeleton = nextCell.getActor() instanceof Skeleton;
        final boolean isNextCellZombie = nextCell.getActor() instanceof Zombie;
        final boolean isNextCellFloor = nextCell.getType().equals(CellType.FLOOR);
        final boolean isNextCellOpenDoor = nextCell.getType().equals(CellType.OPENDOOR);
        final boolean isMeat = nextCell.getType().equals(CellType.FLOOR);
        final boolean isFire = nextCell.getType().equals(CellType.FLOOR);

        if (isNextCellFloor || isNextCellOpenDoor || isMeat || isFire) {
            if (attack > 0 && (isNextCellSkeleton || isNextCellZombie)) {
                nextCell.getActor().setHealth(nextCell.getActor().getHealth() - attack);
                attack -= 5;
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
                if (cell.getItem() != null) {
                    addInventory(cell);
                    cell.setItem(null);
                }
            } else if (!(isNextCellSkeleton || isNextCellZombie)) {
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
                if (cell.getItem() != null) {
                    addInventory(cell);
                    cell.setItem(null);
                }

            }
        } else {
            cell.setActor(this);
        }
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addInventory(Cell cell) {
        if(cell.getItem().getTileName() == "sword"){
            setAttack();
        } else if (cell.getItem().getTileName() == "meat") {
            increaseHealth();
        } else if (cell.getItem().getTileName() == "fire") {
            decreaseHealth();
        }
        else {
            items.add(cell.getItem().getTileName());
        }
    }


    public void increaseHealth(){
        this.playerHealth += 1;
    }

    public int getPlayerHealth(){
        return playerHealth;
    }

    public void decreaseHealth(){
        this.playerHealth -= 1;
    }

    public void setAttack() {
        this.attack += 10;
    }


    public int getAttack() {
        return this.attack;
    }

    public int getHealth(){
        return this.playerHealth;
    }

    @Override
    public void act() {}
}
