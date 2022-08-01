package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;
    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static final Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("sword", new Tile(0, 30));
        tileMap.put("key", new Tile(16, 23));
        tileMap.put("openDoor", new Tile(2,9));
        tileMap.put("closedDoor", new Tile(1,9));
        tileMap.put("zombie", new Tile(25,2));
        tileMap.put("meat", new Tile(16,28));
        tileMap.put("fire", new Tile(15,10));

    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y, int zoom) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH*zoom, y * TILE_WIDTH*zoom, TILE_WIDTH*zoom, TILE_WIDTH*zoom);
    }
}
