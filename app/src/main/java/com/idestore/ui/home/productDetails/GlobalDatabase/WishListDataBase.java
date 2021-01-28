package com.idestore.ui.home.productDetails.GlobalDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.idestore.ui.home.productDetails.DAOs.WishListDio;
import com.idestore.ui.home.productDetails.Entities.Converters;
import com.idestore.ui.home.productDetails.Entities.WishListEntity;

@Database(entities = { WishListEntity.class }, version = 1,exportSchema = false)
@TypeConverters({Converters.class})
public abstract class WishListDataBase extends RoomDatabase {

    private static final String DB_NAME="wishlist_db";
    private static WishListDataBase instance;

    public static synchronized WishListDataBase getInstance(Context context){

        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    WishListDataBase.class,DB_NAME).fallbackToDestructiveMigration().build();
        }
        return instance;
    }
    public abstract WishListDio wishListDio();
}