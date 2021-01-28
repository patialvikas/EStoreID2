package com.idestore.ui.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;




public class VendorProductsModel  implements Serializable{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }




   public class Data implements Serializable{

        @SerializedName("total")
        @Expose
        private Integer total;
        @SerializedName("products")
        @Expose
        private List<Product> products = null;

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
        }


       public class Product implements Serializable{

           @SerializedName("id")
           @Expose
           private Integer id;
           @SerializedName("name")
           @Expose
           private String name;
           @SerializedName("price")
           @Expose
           private Double price;
           @SerializedName("previous_price")
           @Expose
           private Double previousPrice;
           @SerializedName("photo")
           @Expose
           private String photo;
           @SerializedName("thumbnail")
           @Expose
           private String thumbnail;
           @SerializedName("ratings")
           @Expose
           public List<Rating> ratings = null;

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

           public Double getPrice() {
               return price;
           }

           public void setPrice(Double price) {
               this.price = price;
           }

           public Double getPreviousPrice() {
               return previousPrice;
           }

           public void setPreviousPrice(Double previousPrice) {
               this.previousPrice = previousPrice;
           }

           public String getPhoto() {
               return photo;
           }

           public void setPhoto(String photo) {
               this.photo = photo;
           }

           public String getThumbnail() {
               return thumbnail;
           }

           public void setThumbnail(String thumbnail) {
               this.thumbnail = thumbnail;
           }

           public List<Rating> getRatings() {
               return ratings;
           }

           public void setRatings(List<Rating> ratings) {
               this.ratings = ratings;
           }


           public class Rating implements Serializable{

               @SerializedName("id")
               @Expose
               private Integer id;
               @SerializedName("user_id")
               @Expose
               private Integer userId;
               @SerializedName("product_id")
               @Expose
               private Integer productId;
               @SerializedName("review")
               @Expose
               private String review;
               @SerializedName("rating")
               @Expose
               private Integer rating;
               @SerializedName("review_date")
               @Expose
               private String reviewDate;

               public Integer getId() {
                   return id;
               }

               public void setId(Integer id) {
                   this.id = id;
               }

               public Integer getUserId() {
                   return userId;
               }

               public void setUserId(Integer userId) {
                   this.userId = userId;
               }

               public Integer getProductId() {
                   return productId;
               }

               public void setProductId(Integer productId) {
                   this.productId = productId;
               }

               public String getReview() {
                   return review;
               }

               public void setReview(String review) {
                   this.review = review;
               }

               public Integer getRating() {
                   return rating;
               }

               public void setRating(Integer rating) {
                   this.rating = rating;
               }

               public String getReviewDate() {
                   return reviewDate;
               }

               public void setReviewDate(String reviewDate) {
                   this.reviewDate = reviewDate;
               }

           }

       }




    }








}