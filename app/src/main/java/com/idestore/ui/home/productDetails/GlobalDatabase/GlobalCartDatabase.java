package com.idestore.ui.home.productDetails.GlobalDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

//import com.idestore.ui.home.productDetails.Entities.DesCatEntityLocal;
import com.idestore.ui.home.productDetails.DAOs.GlobalCartDio;
import com.idestore.ui.home.productDetails.DAOs.LocalCatDio;
import com.idestore.ui.home.productDetails.Entities.Converters;
import com.idestore.ui.home.productDetails.Entities.GlobalCartEntity;


@Database(entities = { GlobalCartEntity.class }, version = 1,exportSchema = false)
@TypeConverters({Converters.class})
public abstract class GlobalCartDatabase extends RoomDatabase {

    private static final String DB_NAME="catglobal_db";
    private static GlobalCartDatabase instance;

    public static synchronized GlobalCartDatabase getInstance(Context context){

        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    GlobalCartDatabase.class,DB_NAME).fallbackToDestructiveMigration().build();
        }
        return instance;
    }
    public abstract GlobalCartDio globalCartDio();
}
