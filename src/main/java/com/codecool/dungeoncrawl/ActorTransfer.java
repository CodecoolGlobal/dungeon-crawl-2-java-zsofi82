package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.actors.Zombie;
import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

public class ActorTransfer implements JsonDeserializer<Actor>, JsonSerializer<Actor> {
    Gson gson;
    GameMap map;

    public ActorTransfer(GameMap map) {
        GsonBuilder builder = new GsonBuilder()
            .registerTypeAdapter(Actor.class, this);

        this.map = map;
        gson = builder.create();
    }

    public void toJson(Actor actor, FileWriter writer) throws JsonIOException, IOException {
        gson.toJson(actor, writer);
    }

    public GameMap fromJson(FileReader reader) throws FileNotFoundException {
        return gson.fromJson(reader, GameMap.class);
    }

    @Override
    public JsonElement serialize(Actor actor, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        result.add("health", new JsonPrimitive(actor.getHealth()));
        result.add("tilename", new JsonPrimitive(actor.getTileName()));
        result.add("x", new JsonPrimitive(actor.getX()));
        result.add("y", new JsonPrimitive(actor.getY()));
        return result;
    }

    @Override
    public Actor deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject actor = jsonElement.getAsJsonObject();
        Cell cell = map.getCell(actor.get("x").getAsInt(), actor.get("y").getAsInt());
        switch (actor.get("tilename").getAsString()) {
            case "player" -> {
                Player player = new Player(cell);
                map.setPlayer(player);
                return player;
            }
            case "skeleton" -> {
                Skeleton skeleton = new Skeleton(cell);
                map.addMonster(skeleton);
                return skeleton;
            }
            case "zombie" -> {
                Zombie zombie = new Zombie(cell);
                map.addMonster(zombie);
                return zombie;
            }
        }
        return null;
    }
}
