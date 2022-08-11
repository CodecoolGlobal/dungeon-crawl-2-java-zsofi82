package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import java.io.*;

public class Transfer {
    Gson gson = new Gson();

    public void toJson(GameMap map, String filename) throws JsonIOException, IOException {
        gson.toJson(map, new FileWriter(filename));
    }

    public GameMap fromJson(String filename) throws FileNotFoundException {
        return gson.fromJson(new FileReader(filename), GameMap.class);
    }
}
