package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.actors.Zombie;
import com.google.gson.*;

import java.io.*;
import java.lang.reflect.Type;

public class Transfer {
    Gson gson = new Gson();

    public void toJson(GameMap map, String filename) throws JsonIOException, IOException {
        FileWriter writer = new FileWriter(filename);
        ActorTransfer actorTransfer = new ActorTransfer(map);
        gson.toJson(map, writer);
        actorTransfer.toJson(map.getPlayer(), writer);
        map.getMonsters().forEach(monster -> {
            try {
                actorTransfer.toJson(monster, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public GameMap fromJson(String filename) throws IOException {
        FileReader reader = new FileReader(filename);
        GameMap result = gson.fromJson(reader, GameMap.class);
        ActorTransfer actorTransfer = new ActorTransfer(result);
        while (reader.ready()) {
            actorTransfer.fromJson(reader);
        }
        return result;
    }

}
