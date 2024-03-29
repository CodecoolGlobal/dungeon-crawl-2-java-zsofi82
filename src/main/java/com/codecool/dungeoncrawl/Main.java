package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.actors.Zombie;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.ui.ErrorPopup;
import com.codecool.dungeoncrawl.ui.InputPopup;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.google.gson.JsonIOException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

public class Main extends Application {
    GameMap map = MapLoader.loadMap("/map.txt");
    Canvas canvas = new Canvas(
            DISPLAY_SIZE * Tiles.TILE_WIDTH * TILE_ZOOM,
            DISPLAY_SIZE * Tiles.TILE_WIDTH * TILE_ZOOM);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();

    Label itemLabel = new Label();
    Label attackLabel = new Label();
    ErrorPopup errorPopup;
    InputPopup inputPopup;
    GameDatabaseManager dbManager;
    final static int DISPLAY_SIZE = 11;
    final static int TILE_ZOOM = 2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setupDbManager();

        errorPopup = new ErrorPopup(primaryStage);
        inputPopup = new InputPopup(primaryStage);

        GridPane ui = new GridPane();


        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(new Label("Inventory: \n"), 0, 1);
        ui.add(itemLabel, 0, 2);
        ui.add(new Label("Attack Power: "), 0, 3);
        ui.add(attackLabel, 1, 3);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(canvas);
        borderPane.setRight(ui);


        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        scene.setOnKeyReleased(this::onKeyReleased);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();

    }

    private void onKeyReleased(KeyEvent keyEvent) {
        KeyCombination exitCombinationMac = new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN);
        KeyCombination exitCombinationWin = new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN);
        if (exitCombinationMac.match(keyEvent)
                || exitCombinationWin.match(keyEvent)
                || keyEvent.getCode() == KeyCode.ESCAPE) {
            exit();
        }
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1, 0);
                refresh();
                break;
            case S:
                if (keyEvent.isControlDown() || keyEvent.isShortcutDown()) {
                    inputPopup.show(
                        "Save game",
                        "Save",
                        "Cancel",
                        "User name:"
                    );
                    if (!inputPopup.isCanceled()) {
                        saveGame(inputPopup.getInput());
                    }
                }
                break;
            case E:
                if (keyEvent.isControlDown() || keyEvent.isShortcutDown()) {
                    inputPopup.show(
                        "Export game",
                        "Export",
                        "Cancel",
                        "Filename:"
                    );
                    if (!inputPopup.isCanceled()) {
                        try {
                            new Transfer().toJson(map, inputPopup.getInput());
                        } catch (IOException | JsonIOException e) {
                            e.printStackTrace();
                            errorPopup.show(e.getMessage());
                        }
                    }
                }
                break;
            case I:
                if (keyEvent.isControlDown() || keyEvent.isShortcutDown()) {
                    inputPopup.show(
                        "Import game",
                        "Import",
                        "Cancel",
                        "Filename:"
                    );
                    if (!inputPopup.isCanceled()) {
                        try {
                            map = new Transfer().fromJson(inputPopup.getInput());
                        } catch (IOException e) {
                            e.printStackTrace();
                            errorPopup.show(e.getMessage());
                        }
                    }
                }
        }
        map.getMonsters().forEach(Actor::act);
    }

    private void saveGame(String playerName) {
        map.getPlayer().setName(playerName);
        PlayerModel player = new PlayerModel(map.getPlayer());
        GameState gameState = new GameState(
            "map.txt",
            new Date(LocalDate.now().toEpochDay()),
            player
        );
        dbManager.saveGameState(gameState);
        dbManager.savePlayer(map.getPlayer(), gameState.getId());
        map.getMonsters().forEach(monster -> dbManager.saveMonsters(monster, gameState.getId()));
        map.getItems()
            .filter(Objects::nonNull)
            .forEach(item -> dbManager.saveItems(item, gameState.getId()));
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < DISPLAY_SIZE; x++) {
            for (int y = 0; y < DISPLAY_SIZE; y++) {
                int drawX = Math.min(Math.max(map.getPlayer().getX() - 5, 0), map.getWidth() - DISPLAY_SIZE) + x;
                int drawY = Math.min(Math.max(map.getPlayer().getY() - 5, 0), map.getHeight() - DISPLAY_SIZE) + y;
                Cell cell = map.getCell(drawX, drawY);
                if (cell.getActor() != null) {

                    Tiles.drawTile(context, cell.getActor(), x, y, TILE_ZOOM);

                    if (cell.getActor().getHealth() <= 0 && (cell.getActor() instanceof Skeleton || cell.getActor() instanceof Zombie)) {
                        map.getMonsters().remove(cell.getActor());
                        cell.setActor(null);
                    } else {
                        Tiles.drawTile(context, cell.getActor(), x, y, TILE_ZOOM);
                    }

                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y, TILE_ZOOM);
                } else {
                    Tiles.drawTile(context, cell, x, y, TILE_ZOOM);
                    if (map.getPlayer() != null && map.getPlayer().items.contains("key") && cell.getType().equals(CellType.CLOSEDDOOR)) {
                        cell.setType(CellType.OPENDOOR);
                    }
                }
            }
            healthLabel.setText("" + map.getPlayer().getPlayerHealth());
            itemLabel.setText("" + map.listItems());
            attackLabel.setText("" + map.getPlayer().getAttack());
        }
    }

        private void setupDbManager() {
            dbManager = new GameDatabaseManager();
            try {
                dbManager.setup();
            } catch (SQLException ex) {
                System.out.println("Cannot connect to database.");
            }
        }

        private void exit() {
            try {
                stop();
            } catch (Exception e) {
                System.exit(1);
            }
            System.exit(0);
        }
}