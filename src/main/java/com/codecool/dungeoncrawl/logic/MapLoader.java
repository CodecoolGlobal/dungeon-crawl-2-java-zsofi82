package com.codecool.dungeoncrawl.logic;


import com.codecool.dungeoncrawl.logic.actors.Zombie;
import com.codecool.dungeoncrawl.logic.items.Fire;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.items.Meat;
import com.codecool.dungeoncrawl.logic.items.Sword;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(String file) {
        InputStream is = MapLoader.class.getResourceAsStream(file);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();


        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            map.monsters.add(new Skeleton(cell));
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'z':
                            cell.setType(CellType.FLOOR);
                            map.monsters.add(new Zombie(cell));
                            break;
                        case 'S':
                            cell.setType(CellType.FLOOR);
                            cell.setItem(new Sword(cell));
                            break;
                        case 'K':
                            cell.setType(CellType.FLOOR);
                            cell.setItem(new Key(cell));
                            break;
                        case 'D':
                            cell.setType(CellType.CLOSEDDOOR);
                            break;
                        case 'M':
                            cell.setType(CellType.FLOOR);
                            cell.setItem(new Meat(cell));
                            break;
                        case 'F':
                            cell.setType(CellType.FLOOR);
                            cell.setItem(new Fire(cell));
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
