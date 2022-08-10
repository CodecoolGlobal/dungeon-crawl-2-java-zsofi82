package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.EnemyModel;

public interface EnemyStateDao {

    void add(EnemyModel enemyModel, int game_state_id);

    EnemyModel get(int id);
}
