package com.idestore.ui.home.productDetails.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "WishListTable",indices = @Index(value = {"Pid"}, unique = true))
public class WishListEntity {

    @PrimaryKey(autoGenerate = true)
    public int auto_id;

    @ColumnInfo(name = "Pid") // column name will be "note_content" instead of "content" in table
    public int pid;

    @ColumnInfo(name = "Pname") // column name will be "note_content" instead of "content" in table
    public String pname;

    @ColumnInfo(name = "ActualPrice") // column name will be "note_content" instead of "content" in table
    public String actual_price;

    @ColumnInfo(name = "Pprice") // column name will be "note_content" instead of "content" in table
    public String p_price;

    @ColumnInfo(name = "oldprice") // column name will be "note_content" instead of "content" in table
    public String oldprice;

    @ColumnInfo(name = "quantity") // column name will be "note_content" instead of "content" in table
    public int quantity;
    @ColumnInfo(name = "photo") // column name will be "note_content" instead of "content" in table
    public String photo;

    @ColumnInfo(name = "stock") // column name will be "note_content" instead of "content" in table
    public String stock;

    @ColumnInfo(name = "catArray")
    public ArrayList<DesCatEntityLocal> catEntityLocalArrayList;
    @Ignore
    public WishListEntity(int pid, String pname,String actual_price, String p_price, String oldprice, int quantity,String photo, String stock,
                            ArrayList<DesCatEntityLocal> catEntityLocalArrayList){
        this.pid=pid;this.pname=pname;this.p_price=p_price;this.oldprice=oldprice;this.photo=photo;
        this.stock=stock;this.catEntityLocalArrayList=catEntityLocalArrayList;this.actual_price=actual_price;this.quantity=quantity;
    }

    public WishListEntity(int auto_id,int pid,String pname,String actual_price,String p_price,String oldprice,int quantity,String photo,String stock,
                            ArrayList<DesCatEntityLocal>catEntityLocalArrayList){
        this.auto_id=auto_id;
        this.pid=pid;this.pname=pname;this.p_price=p_price;this.oldprice=oldprice;this.photo=photo;
        this.stock=stock;this.catEntityLocalArrayList=catEntityLocalArrayList;this.actual_price=actual_price;this.quantity=quantity;
    }
    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPname() {
        return pname;
    }

    public ArrayList<DesCatEntityLocal> getCatEntityLocalArrayList() {
        return catEntityLocalArrayList;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStock() {
        return stock;
    }

    public void setOldprice(String oldprice) {
        this.oldprice = oldprice;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setP_price(String p_price) {
        this.p_price = p_price;
    }

    public void setCatEntityLocalArrayList(ArrayList<DesCatEntityLocal> catEntityLocalArrayList) {
        this.catEntityLocalArrayList = catEntityLocalArrayList;
    }

    public String getPhoto() {
        return photo;
    }

    public String getP_price() {
        return p_price;
    }

    public String getOldprice() {
        return oldprice;
    }

    public int getPid() {
        return pid;
    }

    public int getAuto_id() {
        return auto_id;
    }

    public void setAuto_id(int auto_id) {
        this.auto_id = auto_id;
    }
}
