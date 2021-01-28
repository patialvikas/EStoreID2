package com.idestore.ui.home.productDetails.Entities;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {
    @TypeConverter
    public static ArrayList<DesCatEntityLocal> fromString(String value) {
        Type listType = new TypeToken<ArrayList<DesCatEntityLocal>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<DesCatEntityLocal> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
