package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.items.Item;

public class ItemModel extends BaseModel{


    private String itemName;

    private int x;

    private int y;


    public ItemModel(Item item){
        this.itemName = item.getTileName();
        this.x = item.getX();
        this.y = item.getY();
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getItemName() {
        return itemName;
    }
}
