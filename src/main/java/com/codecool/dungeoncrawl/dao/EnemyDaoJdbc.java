package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.EnemyModel;

import javax.sql.DataSource;
import java.sql.*;

public class EnemyDaoJdbc implements EnemyStateDao{

    private DataSource dataSource;



    public EnemyDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void add(EnemyModel enemyModel, int game_state_id) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO enemy(monster_name, hp, x, y, game_state_id) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,enemyModel.getMonsterName());
            statement.setInt(2,enemyModel.getHp());
            statement.setInt(3,enemyModel.getX());
            statement.setInt(4,enemyModel.getY());
            statement.setInt(5,game_state_id);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            enemyModel.setId(resultSet.getInt(1));
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public EnemyModel get(int id) {
        return null;
    }
}
