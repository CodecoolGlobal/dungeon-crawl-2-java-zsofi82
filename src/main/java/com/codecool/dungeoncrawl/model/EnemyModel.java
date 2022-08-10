package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.actors.Actor;

public class EnemyModel extends BaseModel{

    private String monsterName;

    private int x;

    private int y;

    private int hp;


    public EnemyModel(Actor actor) {
        this.monsterName = actor.getTileName();
        this.x = actor.getX();
        this.y = actor.getY();
        this.hp = actor.getHealth();
    }

    public String getMonsterName(){
        return monsterName;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHp() {
        return hp;
    }

}
