package com.idestore.ui.home.productDetails.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "LocalSelectCat")
public class DesCatEntityLocal {

    @PrimaryKey(autoGenerate = true)
    public int auto_id;

    @ColumnInfo(name = "Pname") // column name will be "note_content" instead of "content" in table
    public String pname;

    @ColumnInfo(name = "Value") // column name will be "note_content" instead of "content" in table
    public String value;

    @ColumnInfo(name = "Price") // column name will be "note_content" instead of "content" in table
    public String price;
    @Ignore
    public DesCatEntityLocal(String pname, String value, String price){
        this.pname=pname;this.value=value;this.price=price;
    }
    public DesCatEntityLocal(int auto_id,String pname,String value,String price){
       this.auto_id=auto_id; this.pname=pname;this.value=value;this.price=price;
    }

    public void setAuto_id(int auto_id) {
        this.auto_id = auto_id;
    }

    public int getAuto_id() {
        return auto_id;
    }

    public String getPname() {
        return pname;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPrice() {
        return price;
    }

    public String getValue() {
        return value;
    }
}
