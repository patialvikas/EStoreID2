package com.idestore.ui.home.paymentActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.idestore.R;
import com.idestore.services.RetrofitClientInstance;
import com.idestore.ui.authentication.loginActivity.LoginActivity;
import com.idestore.ui.home.AppExecutors;
import com.idestore.ui.home.mainActivity.MainActivity;
import com.idestore.ui.home.productDetails.Entities.GlobalCartEntity;
import com.idestore.ui.home.productDetails.GlobalDatabase.GlobalCartDatabase;
import com.idestore.ui.model.UserLoginModel;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.intellij.lang.annotations.RegExp;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity  implements PaymentResultListener {
    private static final String TAG = PaymentActivity.class.getSimpleName();
    String mob;String tot_price;
    GlobalCartDatabase db;
    ArrayList<GlobalCartEntity>cartArrayList;
    int totalMoney;
    //GlobalCartDatabase db;
    PayOrderModel payOrderModel;
    @Override
    protected void onDestroy() {
        super.onDestroy();
       // db.close();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_payment);


        Intent i=getIntent();
        mob=i.getStringExtra("mob");
        tot_price=i.getStringExtra("price");

        //RegExp regex = RegExp(r"([.]*0)(?!.*\d)");
        double money = Double.parseDouble(tot_price);
        double tMoney = money*100;
         totalMoney = (int) Math.round(tMoney);
        //String stringMoney = String.valueOf(tMoney);
        // totalMoney = Integer.parseInt(stringMoney);
        Log.e("ttpp",String.valueOf(totalMoney));
       // Checkout.setKeyID("<YOUR_KEY_ID>");
        /*
         To ensure faster loading of the Checkout form,
          call this method as early as possible in your checkout flow.
         */
        Checkout.preload(getApplicationContext());
        //Checkout.setKe

        cartArrayList=new ArrayList<GlobalCartEntity>();

        // Payment button created by you in XML layout
        Button button = (Button) findViewById(R.id.btn_pay);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });

        TextView privacyPolicy = (TextView) findViewById(R.id.txt_privacy_policy);

        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://razorpay.com/sample-application/"));
                startActivity(httpIntent);
            }
        });
    }

    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */

        final Activity activity = this;


        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Razorpay Gateway");
            options.put("description", "Payable Charges");
            options.put("key", "rzp_test_FKuwdrZUr3UJML");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", totalMoney);

            JSONObject preFill = new JSONObject();
            preFill.put("email", "Storesector01@gmail.com");
            preFill.put("contact", mob);

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    /**
     * The name of the function has to be
     * onPaymentSuccess
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @SuppressWarnings("unused")
    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {

            Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();


            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {

                     db = Room.databaseBuilder(getApplicationContext(),
                            GlobalCartDatabase.class, "catglobal_db").build();
                    int o=db.globalCartDio().getAll().size();
                    Log.e("size",String.valueOf(o));
                    cartArrayList= (ArrayList<GlobalCartEntity>) db.globalCartDio().getAll();

                    Gson gson = new Gson();
                    String json = gson.toJson(cartArrayList);
                    Log.e("jsonarray", json);

                    SharedPreferences mylogin =getSharedPreferences("MyLogin", Context.MODE_PRIVATE);
                    String u_id=mylogin.getString("userid",null);


                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("order_detail",razorpayPaymentID);
                    requestBody.put("cart_detail", json);
                    requestBody.put("user_id", u_id);

                    RetrofitClientInstance.getRetrofitInstance().payordersend(requestBody
                    ).enqueue(new Callback<PayOrderModel>() {
                        @Override
                        public void onResponse(Call<PayOrderModel> call, Response<PayOrderModel> response) {
                            if(response.body().getStatus()) {
                                payOrderModel = response.body();

                                //Log.e("chooooo", tvGmail.getText().toString());
                                Toast.makeText(getApplicationContext(), "Order Placed..", Toast.LENGTH_LONG).show();

                                 db = Room.databaseBuilder(getApplicationContext(),
                                        GlobalCartDatabase.class, "catglobal_db").build();

                                db.globalCartDio().nukeTable();
                                db.close();

                                //finish();

                                /*Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();*/
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Invalid Login Detail.", Toast.LENGTH_LONG).show();
                            }


                        }

                        @Override
                        public void onFailure(Call<PayOrderModel> call, Throwable t) {
                            // t.fillInStackTrace();
                            Toast.makeText(getApplicationContext(), "Invalid Login Detail.\n Or Something Went Wrong.", Toast.LENGTH_LONG).show();
                        }
                    });







                   runOnUiThread(new Runnable() {
                       @Override
                       public void run() {

                       }
                   });
                }

            });


        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    /**
     * The name of the function has to be
     * onPaymentError
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @SuppressWarnings("unused")
    @Override
    public void onPaymentError(int code, String response) {
        try {
            Toast.makeText(this, "Payment failed: " + code + " " + response, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }
}