package com.idestore.ui.home.productDetails.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.idestore.ui.home.productDetails.Entities.DesCatEntityLocal;
import com.idestore.ui.home.productDetails.Entities.GlobalCartEntity;
import com.idestore.ui.home.productDetails.Entities.WishListEntity;

import java.util.ArrayList;
import java.util.List;
@Dao
public interface WishListDio  {

    @Query("Select * from WishListTable")
    List<WishListEntity> getAll();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(WishListEntity wishListEntity);

    @Delete
    void deleteData(WishListEntity wishListEntity);

    @Update
    void updateSheetData(WishListEntity wishListEntity);

    @Query("SELECT * FROM WishListTable WHERE Pid == :pid")
    public boolean isExist(int pid);

    @Query("UPDATE WishListTable SET Pprice = :price, catArray = :catArray WHERE Pid =:pid")
    void update(String price, int pid, ArrayList<DesCatEntityLocal> catArray);

    @Query("DELETE FROM WishListTable WHERE Pid =:pid")
    void deleteRecord(int pid);

    // @Query("UPDATE GlobalCartTable SET Value = :value, price = :price WHERE Pname =:pname")
    // void update(String value, String price,String pname);

    @Query("DELETE FROM WishListTable")
    public void nukeTable();
}
