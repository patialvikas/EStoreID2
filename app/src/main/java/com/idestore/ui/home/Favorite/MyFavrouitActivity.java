package com.idestore.ui.home.Favorite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.idestore.R;
import com.idestore.baseclass.BaseClass;
import com.idestore.ui.home.AppExecutors;
import com.idestore.ui.home.cartActivity.CartActivity;
import com.idestore.ui.home.cartActivity.adapter.CartItemAdapter;
import com.idestore.ui.home.cartActivity.adapter.RelatedProductsAdapter;
import com.idestore.ui.home.paymentActivity.CheckOutActivity;
import com.idestore.ui.home.productDetails.Entities.GlobalCartEntity;
import com.idestore.ui.home.productDetails.Entities.WishListEntity;
import com.idestore.ui.home.productDetails.GlobalDatabase.GlobalCartDatabase;
import com.idestore.ui.home.productDetails.GlobalDatabase.WishListDataBase;
import com.idestore.utils.UtilsFontFamily;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyFavrouitActivity extends BaseClass implements SearchView.OnQueryTextListener , WishItemAdapter.Afterclick{

    @BindView(R.id.imgBack)
    ImageView imgBack;

    @BindView(R.id.tvTittle)
    TextView tvTittle;

    @BindView(R.id.tvCartIitemsCount)
    TextView tvCartIitemsCount;

    @BindView(R.id.recyclterMyOrderList)
    RecyclerView recyclterMyOrderList;










    @BindView(R.id.tvRelatedproduct)
    TextView tvRelatedproduct;

    @BindView(R.id.recyclerviewRelatedItem)
    RecyclerView recyclerviewRelatedItem;

    @BindView(R.id.cardview)
    CardView cardview;

    @BindView(R.id.safetext)
    TextView safetext;



    @BindView(R.id.searchView)
    SearchView searchView;
   // @BindView(R.id.layout_cart_price)
    //RelativeLayout layout_cart_price;


    Context context;
    WishItemAdapter wishItemAdapter;
    RelatedProductsAdapter relatedProductsAdapter;
    ArrayList<WishListEntity> wishListEntities;
    double total_price=0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favrouit);
        ButterKnife.bind(this);


        context = MyFavrouitActivity.this;
        //ButterKnife.bind(this);
        wishListEntities=new ArrayList<WishListEntity>();

        viewInitialization();
        intitFonts();
    }
    private void viewInitialization() {

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                //Log.e("common","incat");
                WishListDataBase db = Room.databaseBuilder(getApplicationContext(),
                        WishListDataBase.class, "wishlist_db").build();
                int o=db.wishListDio().getAll().size();
                Log.e("sizemm",String.valueOf(o));
                wishListEntities= (ArrayList<WishListEntity>) db.wishListDio().getAll();

                // db.localCatDio().nukeTable();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if(o>0){
                            if(o==1){
                                tvCartIitemsCount.setText("Only 1 Item Available");}
                            else
                            { tvCartIitemsCount.setText(o+"Items Available");}
                            callMyMethod();
                        }
                        else{
                            //Toast.makeText(getApplicationContext(),"No Item To Display",Toast.LENGTH_LONG).show();
                            tvCartIitemsCount.setText("No Item To Display");
                        }   // cartArrayList= (ArrayList<GlobalCartEntity>) db.globalCartDio().getAll();

                    }
                });

            }
        });




        relatedProductsAdapter = new RelatedProductsAdapter(this);
        recyclerviewRelatedItem.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recyclerviewRelatedItem.setAdapter(relatedProductsAdapter);


        searchView.setOnQueryTextListener(this);
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTittle.setVisibility(View.GONE);
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                tvTittle.setVisibility(View.VISIBLE);
                return false;
            }
        });


    }

    private void callMyMethod() {
        wishItemAdapter = new WishItemAdapter(getApplicationContext(),wishListEntities, this);
        recyclterMyOrderList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclterMyOrderList.setAdapter(wishItemAdapter);

        settotalaccording();
    }
    private void ioi() {
        wishItemAdapter = new WishItemAdapter(getApplicationContext(),wishListEntities,this);
        recyclterMyOrderList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclterMyOrderList.setAdapter(wishItemAdapter);

        //settotalaccording();
    }

    private void settotalaccording() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                //Log.e("common","incat");
                WishListDataBase db = Room.databaseBuilder(getApplicationContext(),
                        WishListDataBase.class, "wishlist_db").build();
                int o=db.wishListDio().getAll().size();
                Log.e("size",String.valueOf(o));
                wishListEntities= (ArrayList<WishListEntity>) db.wishListDio().getAll();

                // db.localCatDio().nukeTable();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if(o>0){
                            if(o==1) {
                                tvCartIitemsCount.setText("Only 1 Item Available");
                            }
                            else{
                                tvCartIitemsCount.setText(o+"Items Available");}

                            /*total_price=0.0;
                            for(int i=0;i<wishListEntities.size();i++){
                                total_price=total_price+ Double.parseDouble(cartArrayList.get(i).p_price);

                            }
                            tvSubTotalPrice.setText("₹"+String.valueOf(total_price));
                            tvTotalPrice.setText(String.valueOf(total_price+10.0));*/

                            againrefresh();

                        }

                        // cartArrayList= (ArrayList<GlobalCartEntity>) db.globalCartDio().getAll();
                        //callMyMethod();
                    }
                });

            }
        });

    }

    private void againrefresh() {
        wishItemAdapter = new WishItemAdapter(getApplicationContext(),wishListEntities,this);
        recyclterMyOrderList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclterMyOrderList.setAdapter(wishItemAdapter);
    }


    @OnClick({R.id.imgBack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;

        }
    }
    private void intitFonts() {
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));
        tvCartIitemsCount.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
       // tvSubTotal.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        // tvTex.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        //devliverycharge_tv.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
       // tvTotal.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
       // tvSubTotalPrice.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        //tvTexPrice.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        //tvDevliverycharge.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
       // tvTotalPrice.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvRelatedproduct.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void interMethod() {

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                Log.e("inner work","incat");
                WishListDataBase db = Room.databaseBuilder(getApplicationContext(),
                        WishListDataBase.class, "wishlist_db").build();
                int o=db.wishListDio().getAll().size();
                Log.e("size",String.valueOf(o));
                wishListEntities= (ArrayList<WishListEntity>) db.wishListDio().getAll();

                // db.localCatDio().nukeTable();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if(o>0){
                            if(o==1){
                                tvCartIitemsCount.setText("Only 1 Item Available");}
                            else{
                                tvCartIitemsCount.setText(o+"Items Available");}
                            callMyMethod();
                        }
                        else {
                            ioi();
                            // Toast.makeText(getApplicationContext(),"No Item To Display",Toast.LENGTH_LONG).show();
                            offSite();
                        }

                        // cartArrayList= (ArrayList<GlobalCartEntity>) db.globalCartDio().getAll();

                    }
                });

            }
        });
    }

    private void offSite() {
        tvCartIitemsCount.setText("No Item Available");
        //layout_cart_price.setVisibility(View.GONE);
    }

   /* public void refreshActivtiy() {
        cartItemAdapter = new CartItemAdapter(getApplicationContext(),cartArrayList,this);
        recyclterMyOrderList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclterMyOrderList.setAdapter(cartItemAdapter);
        recyclterMyOrderList.notify();
    }*/
}
