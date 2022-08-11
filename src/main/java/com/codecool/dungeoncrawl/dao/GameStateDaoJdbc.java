package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameStateDaoJdbc implements GameStateDao {

    public GameStateDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private DataSource dataSource;
    @Override
    public void add(GameState state) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO game_state(current_map, saved_at) VALUES(?,?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, state.getCurrentMap());
            statement.setDate(2,state.getSavedAt());
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            state.setId(resultSet.getInt(1));
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(GameState state) {

    }

    @Override
    public GameState get(int id) {
        return null;
    }

    @Override
    public List<GameState> getAll() {
        return null;
    }
}
