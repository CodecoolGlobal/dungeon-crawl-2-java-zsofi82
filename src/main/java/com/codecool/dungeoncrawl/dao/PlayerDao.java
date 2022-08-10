package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.PlayerModel;

import java.util.List;

public interface PlayerDao {

    void add(PlayerModel playerModel);

    void update(PlayerModel player);
    PlayerModel get(int id);
    List<PlayerModel> getAll();
}
