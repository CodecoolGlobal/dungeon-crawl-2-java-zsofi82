package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.ItemModel;

import javax.sql.DataSource;
import java.sql.*;


public class ItemStateJbdc implements ItemsStateDao{

    private DataSource dataSource;

    public ItemStateJbdc(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void add(ItemModel itemModel, int game_state_id) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO item(item_name, x, y, game_state_id) VALUES (?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, itemModel.getItemName());
            statement.setInt(2,itemModel.getX());
            statement.setInt(3,itemModel.getY());
            statement.setInt(4,game_state_id);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            itemModel.setId(resultSet.getInt(1));
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
