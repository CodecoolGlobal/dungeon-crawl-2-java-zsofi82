package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.ItemModel;

public interface ItemsStateDao {
    void add(ItemModel itemModel, int game_state_id);
}
