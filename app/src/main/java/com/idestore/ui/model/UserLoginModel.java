package com.idestore.ui.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;









public class UserLoginModel {

    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

   public class Data {

        @SerializedName("user")
        @Expose
        private User user;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

    }

   public class User {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("type")
        @Expose
        private Object type;
        @SerializedName("vendor_id")
        @Expose
        private Object vendorId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("photo")
        @Expose
        private Object photo;
        @SerializedName("zip")
        @Expose
        private Object zip;
        @SerializedName("city")
        @Expose
        private Object city;
        @SerializedName("country")
        @Expose
        private Object country;
        @SerializedName("state")
        @Expose
        private Object state;
        @SerializedName("address")
        @Expose
        private Object address;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("fax")
        @Expose
        private Object fax;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("showPass")
        @Expose
        private Object showPass;
        @SerializedName("role")
        @Expose
        private Object role;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("is_provider")
        @Expose
        private int isProvider;
        @SerializedName("status")
        @Expose
        private int status;
        @SerializedName("verification_link")
        @Expose
        private String verificationLink;
        @SerializedName("email_verified")
        @Expose
        private String emailVerified;
        @SerializedName("affilate_code")
        @Expose
        private Object affilateCode;
        @SerializedName("affilate_income")
        @Expose
        private int affilateIncome;
        @SerializedName("shop_name")
        @Expose
        private Object shopName;
        @SerializedName("owner_name")
        @Expose
        private Object ownerName;
        @SerializedName("shop_number")
        @Expose
        private Object shopNumber;
        @SerializedName("shop_address")
        @Expose
        private Object shopAddress;
        @SerializedName("reg_number")
        @Expose
        private Object regNumber;
        @SerializedName("shop_message")
        @Expose
        private Object shopMessage;
        @SerializedName("shop_details")
        @Expose
        private Object shopDetails;
        @SerializedName("shop_image")
        @Expose
        private Object shopImage;
        @SerializedName("f_url")
        @Expose
        private Object fUrl;
        @SerializedName("g_url")
        @Expose
        private Object gUrl;
        @SerializedName("t_url")
        @Expose
        private Object tUrl;
        @SerializedName("l_url")
        @Expose
        private Object lUrl;
        @SerializedName("is_vendor")
        @Expose
        private int isVendor;
        @SerializedName("f_check")
        @Expose
        private int fCheck;
        @SerializedName("g_check")
        @Expose
        private int gCheck;
        @SerializedName("t_check")
        @Expose
        private int tCheck;
        @SerializedName("l_check")
        @Expose
        private int lCheck;
        @SerializedName("mail_sent")
        @Expose
        private int mailSent;
        @SerializedName("shipping_cost")
        @Expose
        private int shippingCost;
        @SerializedName("current_balance")
        @Expose
        private int currentBalance;
        @SerializedName("date")
        @Expose
        private Object date;
        @SerializedName("ban")
        @Expose
        private int ban;
        @SerializedName("balance")
        @Expose
        private String balance;
        @SerializedName("open_Time")
        @Expose
        private Object openTime;
        @SerializedName("close_Time")
        @Expose
        private Object closeTime;
        @SerializedName("gst_No")
        @Expose
        private Object gstNo;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public Object getVendorId() {
            return vendorId;
        }

        public void setVendorId(Object vendorId) {
            this.vendorId = vendorId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getPhoto() {
            return photo;
        }

        public void setPhoto(Object photo) {
            this.photo = photo;
        }

        public Object getZip() {
            return zip;
        }

        public void setZip(Object zip) {
            this.zip = zip;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public Object getCountry() {
            return country;
        }

        public void setCountry(Object country) {
            this.country = country;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Object getFax() {
            return fax;
        }

        public void setFax(Object fax) {
            this.fax = fax;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Object getShowPass() {
            return showPass;
        }

        public void setShowPass(Object showPass) {
            this.showPass = showPass;
        }

        public Object getRole() {
            return role;
        }

        public void setRole(Object role) {
            this.role = role;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public int getIsProvider() {
            return isProvider;
        }

        public void setIsProvider(int isProvider) {
            this.isProvider = isProvider;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getVerificationLink() {
            return verificationLink;
        }

        public void setVerificationLink(String verificationLink) {
            this.verificationLink = verificationLink;
        }

        public String getEmailVerified() {
            return emailVerified;
        }

        public void setEmailVerified(String emailVerified) {
            this.emailVerified = emailVerified;
        }

        public Object getAffilateCode() {
            return affilateCode;
        }

        public void setAffilateCode(Object affilateCode) {
            this.affilateCode = affilateCode;
        }

        public int getAffilateIncome() {
            return affilateIncome;
        }

        public void setAffilateIncome(int affilateIncome) {
            this.affilateIncome = affilateIncome;
        }

        public Object getShopName() {
            return shopName;
        }

        public void setShopName(Object shopName) {
            this.shopName = shopName;
        }

        public Object getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(Object ownerName) {
            this.ownerName = ownerName;
        }

        public Object getShopNumber() {
            return shopNumber;
        }

        public void setShopNumber(Object shopNumber) {
            this.shopNumber = shopNumber;
        }

        public Object getShopAddress() {
            return shopAddress;
        }

        public void setShopAddress(Object shopAddress) {
            this.shopAddress = shopAddress;
        }

        public Object getRegNumber() {
            return regNumber;
        }

        public void setRegNumber(Object regNumber) {
            this.regNumber = regNumber;
        }

        public Object getShopMessage() {
            return shopMessage;
        }

        public void setShopMessage(Object shopMessage) {
            this.shopMessage = shopMessage;
        }

        public Object getShopDetails() {
            return shopDetails;
        }

        public void setShopDetails(Object shopDetails) {
            this.shopDetails = shopDetails;
        }

        public Object getShopImage() {
            return shopImage;
        }

        public void setShopImage(Object shopImage) {
            this.shopImage = shopImage;
        }

        public Object getFUrl() {
            return fUrl;
        }

        public void setFUrl(Object fUrl) {
            this.fUrl = fUrl;
        }

        public Object getGUrl() {
            return gUrl;
        }

        public void setGUrl(Object gUrl) {
            this.gUrl = gUrl;
        }

        public Object getTUrl() {
            return tUrl;
        }

        public void setTUrl(Object tUrl) {
            this.tUrl = tUrl;
        }

        public Object getLUrl() {
            return lUrl;
        }

        public void setLUrl(Object lUrl) {
            this.lUrl = lUrl;
        }

        public int getIsVendor() {
            return isVendor;
        }

        public void setIsVendor(int isVendor) {
            this.isVendor = isVendor;
        }

        public int getFCheck() {
            return fCheck;
        }

        public void setFCheck(int fCheck) {
            this.fCheck = fCheck;
        }

        public int getGCheck() {
            return gCheck;
        }

        public void setGCheck(int gCheck) {
            this.gCheck = gCheck;
        }

        public int getTCheck() {
            return tCheck;
        }

        public void setTCheck(int tCheck) {
            this.tCheck = tCheck;
        }

        public int getLCheck() {
            return lCheck;
        }

        public void setLCheck(int lCheck) {
            this.lCheck = lCheck;
        }

        public int getMailSent() {
            return mailSent;
        }

        public void setMailSent(int mailSent) {
            this.mailSent = mailSent;
        }

        public int getShippingCost() {
            return shippingCost;
        }

        public void setShippingCost(int shippingCost) {
            this.shippingCost = shippingCost;
        }

        public int getCurrentBalance() {
            return currentBalance;
        }

        public void setCurrentBalance(int currentBalance) {
            this.currentBalance = currentBalance;
        }

        public Object getDate() {
            return date;
        }

        public void setDate(Object date) {
            this.date = date;
        }

        public int getBan() {
            return ban;
        }

        public void setBan(int ban) {
            this.ban = ban;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
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
