package com.idestore.ui.home.productDetails.LocalDatabase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.idestore.ui.home.productDetails.DAOs.LocalCatDio;
import com.idestore.ui.home.productDetails.Entities.DesCatEntityLocal;


@Database(entities = { DesCatEntityLocal.class }, version = 1,exportSchema = false)
public abstract  class LocalSlectCatDatabase extends RoomDatabase {
    private static final String DB_NAME="catlocal_db";
    private static LocalSlectCatDatabase instance;

    public static synchronized LocalSlectCatDatabase getInstance(Context context){

        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    LocalSlectCatDatabase.class,DB_NAME).fallbackToDestructiveMigration().build();
        }
        return instance;
    }
    public abstract LocalCatDio localCatDio();
}
