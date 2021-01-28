package com.idestore.ui.home.productDetails.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.idestore.ui.home.productDetails.Entities.DesCatEntityLocal;
import com.idestore.ui.model.SlectCatModel;

import java.util.List;
@Dao
public interface LocalCatDio {
    @Query("Select * from LocalSelectCat")
    List<DesCatEntityLocal> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSheetData(DesCatEntityLocal desCatEntityLocal);

    @Delete
    void deleteSheetData(DesCatEntityLocal desCatEntityLocal);

    @Update
    void updateSheetData(DesCatEntityLocal desCatEntityLocal);

    @Query("UPDATE LocalSelectCat SET Value = :value, price = :price WHERE Pname =:pname")
    void update(String value, String price,String pname);



    @Query("SELECT * FROM LocalSelectCat WHERE Pname == :pname")
     public boolean isExist(String pname);



    @Query("DELETE FROM LocalSelectCat")
    public void nukeTable();
}
