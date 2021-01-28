package com.idestore.services;

import com.idestore.ui.home.mainActivity.AddressModel;
import com.idestore.ui.home.mainActivity.UpdateAddressModel;
import com.idestore.ui.home.paymentActivity.PayOrderModel;
import com.idestore.ui.model.ForgetPassModel;
import com.idestore.ui.model.ProductDetailModel;
import com.idestore.ui.model.SignUpModel;
import com.idestore.ui.model.UserLoginModel;
import com.idestore.ui.model.VendorListModel;
import com.idestore.ui.model.VendorProductsModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GetDataService {
   // public static final int USER_REGISTER_SUCCESS = 1;
   // public static final int USER_REGISTER_FAILED = 2;

    //@FormUrlEncoded // annotation used in POST type requests
    @POST("/api/user-login")
        // API's endpoints
    Call<UserLoginModel> loginuser(@Body Map<String, String> body);

    @POST("/api/update_location")
        // API's endpoints
    Call<UpdateAddressModel> updateaddress(@Body Map<String, String> body);

    @POST("/api/current_location")
        // API's endpoints
    Call<AddressModel> fetchAddress(@Body Map<String, String> body);

    @POST("/api/order_detail")
        // API's endpoints
    Call<PayOrderModel> payordersend(@Body Map<String, String> body);

    @POST("/api/user-register")
        // API's endpoints
    Call<SignUpModel> registeruser(@Body Map<String, String> body);

    @POST("/api/forgot-password")
        // API's endpoints
    Call<ForgetPassModel> forgetpass(@Body Map<String, String> body);

    @POST("/api/vendorlist")
        // API's endpoints
    Call<VendorListModel> vendorList();

    @POST("/api/vendorProducts")
        // API's endpoints
    Call<VendorProductsModel> vendorProductsList(@Body Map<String, String> body);

    @POST("/api/productdetails")
        // API's endpoints
    Call<ProductDetailModel> vendorProductsDetail(@Body Map<String, String> body);

}
