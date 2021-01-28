package com.idestore.ui.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProductDetailModel implements Serializable {

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

        @SerializedName("price")
        @Expose
        private Integer price;
        @SerializedName("previous_price")
        @Expose
        private Integer previousPrice;
        @SerializedName("product")
        @Expose
        private Product product;
        @SerializedName("galleries")
        @Expose
        private List<Gallery> galleries = null;
        @SerializedName("ratings")
        @Expose
        private List<Rating> ratings = null;
        @SerializedName("category")
        @Expose
        private List<Category> category = null;

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public Integer getPreviousPrice() {
            return previousPrice;
        }

        public void setPreviousPrice(Integer previousPrice) {
            this.previousPrice = previousPrice;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public List<Gallery> getGalleries() {
            return galleries;
        }

        public void setGalleries(List<Gallery> galleries) {
            this.galleries = galleries;
        }

        public List<Rating> getRatings() {
            return ratings;
        }

        public void setRatings(List<Rating> ratings) {
            this.ratings = ratings;
        }

        public List<Category> getCategory() {
            return category;
        }

        public void setCategory(List<Category> category) {
            this.category = category;
        }

        public class Category {

            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("value")
            @Expose
            private List<String> value = null;
            @SerializedName("price")
            @Expose
            private List<String> price = null;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<String> getValue() {
                return value;
            }

            public void setValue(List<String> value) {
                this.value = value;
            }

            public List<String> getPrice() {
                return price;
            }

            public void setPrice(List<String> price) {
                this.price = price;
            }

        }





        public class Gallery implements Serializable{

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("product_id")
            @Expose
            private Integer productId;
            @SerializedName("photo")
            @Expose
            private String photo;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Integer getProductId() {
                return productId;
            }

            public void setProductId(Integer productId) {
                this.productId = productId;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

        }





        public class Rating implements Serializable {

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





        public class Product implements Serializable{

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("sku")
            @Expose
            private String sku;
            @SerializedName("product_type")
            @Expose
            private String productType;
            @SerializedName("affiliate_link")
            @Expose
            private Object affiliateLink;
            @SerializedName("user_id")
            @Expose
            private Integer userId;
            @SerializedName("category_id")
            @Expose
            private Integer categoryId;
            @SerializedName("subcategory_id")
            @Expose
            private Integer subcategoryId;
            @SerializedName("childcategory_id")
            @Expose
            private Integer childcategoryId;
            @SerializedName("attributes")
            @Expose
            private String attributes;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("slug")
            @Expose
            private String slug;
            @SerializedName("photo")
            @Expose
            private String photo;
            @SerializedName("thumbnail")
            @Expose
            private String thumbnail;
            @SerializedName("file")
            @Expose
            private Object file;
            @SerializedName("size")
            @Expose
            private List<String> size = null;
            @SerializedName("size_qty")
            @Expose
            private List<String> sizeQty = null;
            @SerializedName("size_price")
            @Expose
            private List<String> sizePrice = null;
            @SerializedName("color")
            @Expose
            private List<String> color = null;
            @SerializedName("price")
            @Expose
            private Integer price;
            @SerializedName("previous_price")
            @Expose
            private Integer previousPrice;
            @SerializedName("details")
            @Expose
            private String details;
            @SerializedName("stock")
            @Expose
            private Integer stock;
            @SerializedName("policy")
            @Expose
            private String policy;
            @SerializedName("status")
            @Expose
            private Integer status;
            @SerializedName("views")
            @Expose
            private Integer views;
            @SerializedName("tags")
            @Expose
            private List<String> tags = null;
            @SerializedName("features")
            @Expose
            private List<String> features = null;
            @SerializedName("colors")
            @Expose
            private List<String> colors = null;
            @SerializedName("product_condition")
            @Expose
            private Integer productCondition;
            @SerializedName("ship")
            @Expose
            private String ship;
            @SerializedName("is_meta")
            @Expose
            private Integer isMeta;
            @SerializedName("meta_tag")
            @Expose
            private List<String> metaTag = null;
            @SerializedName("meta_description")
            @Expose
            private String metaDescription;
            @SerializedName("youtube")
            @Expose
            private String youtube;
            @SerializedName("type")
            @Expose
            private String type;
            @SerializedName("license")
            @Expose
            private String license;
            @SerializedName("license_qty")
            @Expose
            private String licenseQty;
            @SerializedName("link")
            @Expose
            private Object link;
            @SerializedName("platform")
            @Expose
            private Object platform;
            @SerializedName("region")
            @Expose
            private Object region;
            @SerializedName("licence_type")
            @Expose
            private Object licenceType;
            @SerializedName("measure")
            @Expose
            private Object measure;
            @SerializedName("featured")
            @Expose
            private Integer featured;
            @SerializedName("best")
            @Expose
            private Integer best;
            @SerializedName("top")
            @Expose
            private Integer top;
            @SerializedName("hot")
            @Expose
            private Integer hot;
            @SerializedName("latest")
            @Expose
            private Integer latest;
            @SerializedName("big")
            @Expose
            private Integer big;
            @SerializedName("trending")
            @Expose
            private Integer trending;
            @SerializedName("sale")
            @Expose
            private Integer sale;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;
            @SerializedName("is_discount")
            @Expose
            private Integer isDiscount;
            @SerializedName("discount_date")
            @Expose
            private Object discountDate;
            @SerializedName("whole_sell_qty")
            @Expose
            private List<String> wholeSellQty = null;
            @SerializedName("whole_sell_discount")
            @Expose
            private List<String> wholeSellDiscount = null;
            @SerializedName("is_catalog")
            @Expose
            private Integer isCatalog;
            @SerializedName("catalog_id")
            @Expose
            private Integer catalogId;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getSku() {
                return sku;
            }

            public void setSku(String sku) {
                this.sku = sku;
            }

            public String getProductType() {
                return productType;
            }

            public void setProductType(String productType) {
                this.productType = productType;
            }

            public Object getAffiliateLink() {
                return affiliateLink;
            }

            public void setAffiliateLink(Object affiliateLink) {
                this.affiliateLink = affiliateLink;
            }

            public Integer getUserId() {
                return userId;
            }

            public void setUserId(Integer userId) {
                this.userId = userId;
            }

            public Integer getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(Integer categoryId) {
                this.categoryId = categoryId;
            }

            public Integer getSubcategoryId() {
                return subcategoryId;
            }

            public void setSubcategoryId(Integer subcategoryId) {
                this.subcategoryId = subcategoryId;
            }

            public Integer getChildcategoryId() {
                return childcategoryId;
            }

            public void setChildcategoryId(Integer childcategoryId) {
                this.childcategoryId = childcategoryId;
            }

            public String getAttributes() {
                return attributes;
            }

            public void setAttributes(String attributes) {
                this.attributes = attributes;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSlug() {
                return slug;
            }

            public void setSlug(String slug) {
                this.slug = slug;
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

            public Object getFile() {
                return file;
            }

            public void setFile(Object file) {
                this.file = file;
            }

            public List<String> getSize() {
                return size;
            }

            public void setSize(List<String> size) {
                this.size = size;
            }

            public List<String> getSizeQty() {
                return sizeQty;
            }

            public void setSizeQty(List<String> sizeQty) {
                this.sizeQty = sizeQty;
            }

            public List<String> getSizePrice() {
                return sizePrice;
            }

            public void setSizePrice(List<String> sizePrice) {
                this.sizePrice = sizePrice;
            }

            public List<String> getColor() {
                return color;
            }

            public void setColor(List<String> color) {
                this.color = color;
            }

            public Integer getPrice() {
                return price;
            }

            public void setPrice(Integer price) {
                this.price = price;
            }

            public Integer getPreviousPrice() {
                return previousPrice;
            }

            public void setPreviousPrice(Integer previousPrice) {
                this.previousPrice = previousPrice;
            }

            public String getDetails() {
                return details;
            }

            public void setDetails(String details) {
                this.details = details;
            }

            public Integer getStock() {
                return stock;
            }

            public void setStock(Integer stock) {
                this.stock = stock;
            }

            public String getPolicy() {
                return policy;
            }

            public void setPolicy(String policy) {
                this.policy = policy;
            }

            public Integer getStatus() {
                return status;
            }

            public void setStatus(Integer status) {
                this.status = status;
            }

            public Integer getViews() {
                return views;
            }

            public void setViews(Integer views) {
                this.views = views;
            }

            public List<String> getTags() {
                return tags;
            }

            public void setTags(List<String> tags) {
                this.tags = tags;
            }

            public List<String> getFeatures() {
                return features;
            }

            public void setFeatures(List<String> features) {
                this.features = features;
            }

            public List<String> getColors() {
                return colors;
            }

            public void setColors(List<String> colors) {
                this.colors = colors;
            }

            public Integer getProductCondition() {
                return productCondition;
            }

            public void setProductCondition(Integer productCondition) {
                this.productCondition = productCondition;
            }

            public String getShip() {
                return ship;
            }

            public void setShip(String ship) {
                this.ship = ship;
            }

            public Integer getIsMeta() {
                return isMeta;
            }

            public void setIsMeta(Integer isMeta) {
                this.isMeta = isMeta;
            }

            public List<String> getMetaTag() {
                return metaTag;
            }

            public void setMetaTag(List<String> metaTag) {
                this.metaTag = metaTag;
            }

            public String getMetaDescription() {
                return metaDescription;
            }

            public void setMetaDescription(String metaDescription) {
                this.metaDescription = metaDescription;
            }

            public String getYoutube() {
                return youtube;
            }

            public void setYoutube(String youtube) {
                this.youtube = youtube;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getLicense() {
                return license;
            }

            public void setLicense(String license) {
                this.license = license;
            }

            public String getLicenseQty() {
                return licenseQty;
            }

            public void setLicenseQty(String licenseQty) {
                this.licenseQty = licenseQty;
            }

            public Object getLink() {
                return link;
            }

            public void setLink(Object link) {
                this.link = link;
            }

            public Object getPlatform() {
                return platform;
            }

            public void setPlatform(Object platform) {
                this.platform = platform;
            }

            public Object getRegion() {
                return region;
            }

            public void setRegion(Object region) {
                this.region = region;
            }

            public Object getLicenceType() {
                return licenceType;
            }

            public void setLicenceType(Object licenceType) {
                this.licenceType = licenceType;
            }

            public Object getMeasure() {
                return measure;
            }

            public void setMeasure(Object measure) {
                this.measure = measure;
            }

            public Integer getFeatured() {
                return featured;
            }

            public void setFeatured(Integer featured) {
                this.featured = featured;
            }

            public Integer getBest() {
                return best;
            }

            public void setBest(Integer best) {
                this.best = best;
            }

            public Integer getTop() {
                return top;
            }

            public void setTop(Integer top) {
                this.top = top;
            }

            public Integer getHot() {
                return hot;
            }

            public void setHot(Integer hot) {
                this.hot = hot;
            }

            public Integer getLatest() {
                return latest;
            }

            public void setLatest(Integer latest) {
                this.latest = latest;
            }

            public Integer getBig() {
                return big;
            }

            public void setBig(Integer big) {
                this.big = big;
            }

            public Integer getTrending() {
                return trending;
            }

            public void setTrending(Integer trending) {
                this.trending = trending;
            }

            public Integer getSale() {
                return sale;
            }

            public void setSale(Integer sale) {
                this.sale = sale;
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

            public Integer getIsDiscount() {
                return isDiscount;
            }

            public void setIsDiscount(Integer isDiscount) {
                this.isDiscount = isDiscount;
            }

            public Object getDiscountDate() {
                return discountDate;
            }

            public void setDiscountDate(Object discountDate) {
                this.discountDate = discountDate;
            }

            public List<String> getWholeSellQty() {
                return wholeSellQty;
            }

            public void setWholeSellQty(List<String> wholeSellQty) {
                this.wholeSellQty = wholeSellQty;
            }

            public List<String> getWholeSellDiscount() {
                return wholeSellDiscount;
            }

            public void setWholeSellDiscount(List<String> wholeSellDiscount) {
                this.wholeSellDiscount = wholeSellDiscount;
            }

            public Integer getIsCatalog() {
                return isCatalog;
            }

            public void setIsCatalog(Integer isCatalog) {
                this.isCatalog = isCatalog;
            }

            public Integer getCatalogId() {
                return catalogId;
            }

            public void setCatalogId(Integer catalogId) {
                this.catalogId = catalogId;
            }

        }




    }



}


