package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.model.EnemyModel;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.ItemModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class GameDatabaseManager {
    private static final String ENVIRONMENT_DBNAME = "PSQL_DBNAME";
    private static final String ENVIRONMENT_HOSTNAME = "PSQL_HOSTNAME";
    private static final String ENVIRONMENT_PORT = "PSQL_PORT";
    private static final String ENVIRONMENT_USERNAME = "PSQL_USERNAME";
    private static final String ENVIRONMENT_PASSWORD = "PSQL_PASSWORD";

    public static final String DEFAULT_DBNAME = "dungeon_db";
    public static final String DEFAULT_HOSTNAME = "localhost";
    public static final String DEFAULT_PORT = "5432";

    private PlayerDao playerDao;

    private ItemsStateDao itemsStateDao;

    private EnemyStateDao enemyStateDao;

    private GameStateDao gameStateDao;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        gameStateDao = new GameStateDaoJdbc(dataSource);
        playerDao = new PlayerDaoJdbc(dataSource);
        itemsStateDao = new ItemStateJbdc(dataSource);
        enemyStateDao = new EnemyDaoJdbc(dataSource);
    }

    public void saveGameState(GameState state) {
        gameStateDao.add(state);
    }

    public void savePlayer(Player player, int game_state_id) {
        PlayerModel model = new PlayerModel(player);
        playerDao.add(model, game_state_id);
    }

    public void saveItems(Item item, int game_state_id){
        ItemModel model = new ItemModel(item);
        itemsStateDao.add(model, game_state_id);
    }

    public void saveMonsters(Actor actor, int game_state_id){
        EnemyModel enemyModel = new EnemyModel(actor);
        enemyStateDao.add(enemyModel, game_state_id);
    }


    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        if (!System.getenv().containsKey(ENVIRONMENT_USERNAME)) {
            throw new IllegalArgumentException(ENVIRONMENT_USERNAME + " environment variable should be set");
        }

        if (!System.getenv().containsKey(ENVIRONMENT_PASSWORD)) {
            throw new IllegalArgumentException(ENVIRONMENT_PASSWORD + " environment variable should be set");
        }

        dataSource.setDatabaseName(System.getenv().getOrDefault(
            ENVIRONMENT_DBNAME,
            DEFAULT_DBNAME
        ));
        dataSource.setServerName(System.getenv().getOrDefault(
            ENVIRONMENT_HOSTNAME,
            DEFAULT_HOSTNAME
        ));
        dataSource.setPortNumber(Integer.parseInt(System.getenv().getOrDefault(
            ENVIRONMENT_PORT,
            DEFAULT_PORT
        )));
        dataSource.setUser(System.getenv(ENVIRONMENT_USERNAME));
        dataSource.setPassword(System.getenv(ENVIRONMENT_PASSWORD));

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
