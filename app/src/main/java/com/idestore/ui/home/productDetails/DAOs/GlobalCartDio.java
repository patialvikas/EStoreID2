package com.idestore.ui.home.productDetails.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.idestore.ui.home.productDetails.Entities.DesCatEntityLocal;
import com.idestore.ui.home.productDetails.Entities.GlobalCartEntity;

import java.util.ArrayList;
import java.util.List;
@Dao
public interface GlobalCartDio {

    @Query("Select * from GlobalCartTable")
    List<GlobalCartEntity> getAll();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(GlobalCartEntity globalCartEntity);

    @Delete
    void deleteData(GlobalCartEntity globalCartEntity);

    @Update
    void updateSheetData(GlobalCartEntity globalCartEntity);

    @Query("SELECT * FROM GlobalCartTable WHERE Pid == :pid")
    public boolean isExist(int pid);

    @Query("UPDATE GlobalCartTable SET Pprice = :price, catArray = :catArray WHERE Pid =:pid")
    void update(String price,int pid,ArrayList<DesCatEntityLocal>catArray);

    @Query("UPDATE GlobalCartTable SET Pprice = :price, quantity = :quantity WHERE Pid =:pid")
    void updateQtyP(String price,int pid,int quantity);

    @Query("DELETE FROM GlobalCartTable WHERE Pid =:pid")
    void deleteRecord(int pid);

   // @Query("UPDATE GlobalCartTable SET Value = :value, price = :price WHERE Pname =:pname")
   // void update(String value, String price,String pname);







    @Query("DELETE FROM GlobalCartTable")
    public void nukeTable();
}
