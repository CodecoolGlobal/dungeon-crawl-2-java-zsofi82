package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.actors.Zombie;
import com.google.gson.*;

import java.io.*;
import java.lang.reflect.Type;

public class Transfer implements JsonDeserializer<Actor>, JsonSerializer<Actor> {
    Gson gson;

    public Transfer() {
        GsonBuilder builder = new GsonBuilder();
        gson = builder.registerTypeAdapter(Actor.class, this).create();
    }

    public void toJson(GameMap map, String filename) throws JsonIOException, IOException {
        gson.toJson(map, new FileWriter(filename));
    }

    public GameMap fromJson(String filename) throws FileNotFoundException {
        return gson.fromJson(new FileReader(filename), GameMap.class);
    }

    @Override
    public JsonElement serialize(Actor actor, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        result.add("health", new JsonPrimitive(actor.getHealth()));
        result.add("tilename", new JsonPrimitive(actor.getTileName()));
        return result;
    }

    @Override
    public Actor deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject actor = jsonElement.getAsJsonObject();
        switch (actor.get("tilename").getAsString()) {
            case "player" -> {
                return new Player(null);
            }
            case "skeleton" -> {
                return new Skeleton(null);
            }
            case "zombie" -> {
                return new Zombie(null);
            }
        }
        return null;
    }
}
