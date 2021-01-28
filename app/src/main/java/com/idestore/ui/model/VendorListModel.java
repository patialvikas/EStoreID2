package com.idestore.ui.model;



import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;






public class VendorListModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Dataa data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Dataa getData() {
        return data;
    }

    public void setData(Dataa data) {
        this.data = data;
    }


   public class Dataa {

        @SerializedName("vendor")
        @Expose
        private List<Vendor> vendor = null;

        public List<Vendor> getVendor() {
            return vendor;
        }

        public void setVendor(List<Vendor> vendor) {
            this.vendor = vendor;
        }


      public class Vendor {

           @SerializedName("id")
           @Expose
           private Integer id;
           @SerializedName("name")
           @Expose
           private String name;
           @SerializedName("email")
           @Expose
           private String email;
           @SerializedName("phone")
           @Expose
           private String phone;
           @SerializedName("shop_name")
           @Expose
           private String shopName;
           @SerializedName("owner_name")
           @Expose
           private String ownerName;
           @SerializedName("shop_number")
           @Expose
           private String shopNumber;
           @SerializedName("shop_address")
           @Expose
           private String shopAddress;
           @SerializedName("reg_number")
           @Expose
           private String regNumber;
           @SerializedName("shop_message")
           @Expose
           private String shopMessage;
           @SerializedName("shop_details")
           @Expose
           private Object shopDetails;
           @SerializedName("shop_image")
           @Expose
           private String shopImage;
           @SerializedName("open_Time")
           @Expose
           private Object openTime;
           @SerializedName("close_Time")
           @Expose
           private Object closeTime;
           @SerializedName("gst_No")
           @Expose
           private Object gstNo;

           public Integer getId() {
               return id;
           }

           public void setId(Integer id) {
               this.id = id;
           }

           public String getName() {
               return name;
           }

           public void setName(String name) {
               this.name = name;
           }

           public String getEmail() {
               return email;
           }

           public void setEmail(String email) {
               this.email = email;
           }

           public String getPhone() {
               return phone;
           }

           public void setPhone(String phone) {
               this.phone = phone;
           }

           public String getShopName() {
               return shopName;
           }

           public void setShopName(String shopName) {
               this.shopName = shopName;
           }

           public String getOwnerName() {
               return ownerName;
           }

           public void setOwnerName(String ownerName) {
               this.ownerName = ownerName;
           }

           public String getShopNumber() {
               return shopNumber;
           }

           public void setShopNumber(String shopNumber) {
               this.shopNumber = shopNumber;
           }

           public String getShopAddress() {
               return shopAddress;
           }

           public void setShopAddress(String shopAddress) {
               this.shopAddress = shopAddress;
           }

           public String getRegNumber() {
               return regNumber;
           }

           public void setRegNumber(String regNumber) {
               this.regNumber = regNumber;
           }

           public String getShopMessage() {
               return shopMessage;
           }

           public void setShopMessage(String shopMessage) {
               this.shopMessage = shopMessage;
           }

           public Object getShopDetails() {
               return shopDetails;
           }

           public void setShopDetails(Object shopDetails) {
               this.shopDetails = shopDetails;
           }

           public String getShopImage() {
               return shopImage;
           }

           public void setShopImage(String shopImage) {
               this.shopImage = shopImage;
           }

           public Object getOpenTime() {
               return openTime;
           }

           public void setOpenTime(Object openTime) {
               this.openTime = openTime;
           }

           public Object getCloseTime() {
               return closeTime;
           }

           public void setCloseTime(Object closeTime) {
               this.closeTime = closeTime;
           }

           public Object getGstNo() {
               return gstNo;
           }

           public void setGstNo(Object gstNo) {
               this.gstNo = gstNo;
           }

       }

    }
}


