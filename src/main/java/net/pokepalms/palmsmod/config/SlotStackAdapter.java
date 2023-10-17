package net.pokepalms.palmsmod.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class SlotStackAdapter extends TypeAdapter<String[][]> {

    public static final Gson GSON = new GsonBuilder().setLenient().create();

    @Override
    public void write(JsonWriter out, String[][] value) throws IOException {

        out.jsonValue(GSON.toJson(value).replaceAll("\n", "")
                .replaceAll("\\[\\[", "[\n        [")
                .replaceAll("],", "],\n        ")
                .replaceAll("]]", "]\n      ]"));
    }

    @Override
    public String[][] read(JsonReader in) {
        return GSON.fromJson(in, String[][].class);
    }
}
