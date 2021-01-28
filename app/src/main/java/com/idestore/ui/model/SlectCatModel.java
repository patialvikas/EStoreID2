package com.idestore.ui.model;

public class SlectCatModel {
    String topcat_name;
    String sub_cat;
    String productprice;

public SlectCatModel(String topcat_name, String sub_cat, String productprice){
    this.topcat_name=topcat_name;
    this.productprice=productprice;
    this.sub_cat=sub_cat;
}

    public String getTopcat_name() {
        return topcat_name;
    }

    public void setTopcat_name(String topcat_name) {
        this.topcat_name = topcat_name;
    }

    public String getSub_cat() {
        return sub_cat;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setSub_cat(String sub_cat) {
        this.sub_cat = sub_cat;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }
}
