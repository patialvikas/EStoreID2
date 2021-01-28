package com.idestore.ui.home.productDetails;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import com.idestore.R;
import com.idestore.baseclass.BaseClass;
import com.idestore.services.RetrofitClientInstance;
import com.idestore.ui.home.AppExecutors;
import com.idestore.ui.home.Favorite.MyFavrouitActivity;
import com.idestore.ui.home.cartActivity.CartActivity;
import com.idestore.ui.home.myfavrouitStore.MyFavrouitStoreActivity;
import com.idestore.ui.home.productDetails.Entities.DesCatEntityLocal;
import com.idestore.ui.home.productDetails.Entities.GlobalCartEntity;
import com.idestore.ui.home.productDetails.Entities.WishListEntity;
import com.idestore.ui.home.productDetails.GlobalDatabase.GlobalCartDatabase;
import com.idestore.ui.home.productDetails.GlobalDatabase.WishListDataBase;
import com.idestore.ui.home.productDetails.LocalDatabase.LocalSlectCatDatabase;
import com.idestore.ui.home.productDetails.adapter.ImageSlidingAdapter;
import com.idestore.ui.home.productDetails.adapter.ProductCategoryAdapter;
import com.idestore.ui.home.productDetails.adapter.ProductColorAdapter;
import com.idestore.ui.home.productDetails.adapter.ProductSizeAdapter;
import com.idestore.ui.home.productDetails.responseModel.ImageModel;
import com.idestore.ui.model.ProductDetailModel;
import com.idestore.utils.UtilsFontFamily;
import com.viewpagerindicator.CirclePageIndicator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.nikartm.support.BadgePosition;
import ru.nikartm.support.ImageBadgeView;

public class ProductDetailsActivity extends BaseClass implements ProductCategoryAdapter.DataTransferInterface,
        ProductSizeAdapter.DataSizeInterface {

    @BindView(R.id.imgBack)
    ImageView imgBack;

    @BindView(R.id.tvTittle)
    TextView tvTittle;

    @BindView(R.id.imgCart)
    ImageBadgeView imgCart;

    @BindView(R.id.toolbar)
    RelativeLayout toolbar;

    @BindView(R.id.indicator)
    CirclePageIndicator indicator;

    @BindView(R.id.tvProducName)
    TextView tvProducName;

    @BindView(R.id.price_layout)
    RelativeLayout pricelayout;

    @BindView(R.id.tvProductPriceDiscount)
    TextView tvProductPriceDiscount;

    @BindView(R.id.tvProductPrice)
    TextView tvProductPrice;





    @BindView(R.id.btnProductRating)
    Button btnProductRating;

    @BindView(R.id.tvProductOverallreview)
    TextView tvProductOverallreview;

    @BindView(R.id.tvProductReviews)
    TextView tvProductReviews;

    @BindView(R.id.rating_layout)
    RelativeLayout ratingLayout;

    @BindView(R.id.tvDec)
    TextView tvDec;

    @BindView(R.id.tvDescShort)
    TextView tvDescShort;


   /* @BindView(R.id.tvSelectSize)
    TextView tvSelectSize;

    @BindView(R.id.tvSelectColour)
    TextView tvSelectColour;*/

    @BindView(R.id.imgFavrouit)
    ImageView imgFavrouit;


    @BindView(R.id.recyclerColor)
    RecyclerView recyclerColor;

    @BindView(R.id.colorlayout)
    LinearLayout colorlayout;

    @BindView(R.id.recyclerSize)
    RecyclerView recyclerSize;

    @BindView(R.id.size_layout)
    LinearLayout size_layout;

    @BindView(R.id.scrollView)
    NestedScrollView scrollView;

    @BindView(R.id.product_add_to_cart)
    Button productAddToCart;

    @BindView(R.id.product_add_to_wishlist)
    Button productAddToWishlist;

    @BindView(R.id.layoutBottom)
    LinearLayout layoutBottom;
    @BindView(R.id.cat_recycler)
    RecyclerView cat_recycler;

    @BindView(R.id.catdata)
    TextView catdata;
    LocalSlectCatDatabase dbi;

    ArrayList<DesCatEntityLocal>desCatEntityLocalArrayList;

    ProductDetailModel productDetailModel;
    ProductCategoryAdapter productCategoryAdapter;

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbi.close();
    }

    @Override
    protected void onPause() {
        super.onPause();

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                //Log.e("common","incat");
                 dbi = Room.databaseBuilder(getApplicationContext(),
                        LocalSlectCatDatabase.class, "catlocal_db").build();

                dbi.localCatDio().nukeTable();

            }
        });
    }

    String product_id;
    private ArrayList<ProductDetailModel.Data.Gallery> imageModelArrayList;
    private ArrayList<ProductDetailModel.Data.Rating>ratingArrayList;
    public ArrayList<ProductDetailModel.Data.Category>categoryArrayList;
    double rating=0.0;

    private int[] myImageList = new int[]{R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background, R.drawable.ic_launcher_background
            , R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};

    Context context;
    ProductColorAdapter productColorAdapter;
    ProductSizeAdapter productSizeAdapter;

    boolean isFavrouit = false;
    Double p_price=0.0;
    Double cd = 0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);
        Intent i=getIntent();
        product_id=i.getStringExtra("product_id");

        context = ProductDetailsActivity.this;


        productDetailModel=new ProductDetailModel();
        imageModelArrayList = new ArrayList<ProductDetailModel.Data.Gallery>();
        ratingArrayList=new ArrayList<ProductDetailModel.Data.Rating>();
        categoryArrayList=new ArrayList<ProductDetailModel.Data.Category>();
        float rating;
        //imageModelArrayList = populateList();


        viewInitialization();
        intFonts();

        checkForBadge();
    }

    private void checkForBadge() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                Log.e("common","incat");
                GlobalCartDatabase db = Room.databaseBuilder(getApplicationContext(),
                        GlobalCartDatabase.class, "catglobal_db").build();
                /*GlobalCartEntity globalCartEntity=new GlobalCartEntity(productDetailModel.getData().getProduct().getId(),
                        productDetailModel.getData().getProduct().getName(),tvProductPriceDiscount.getText().toString(),
                        productDetailModel.getData().getProduct().getPreviousPrice().toString(),productDetailModel.getData().getProduct().getPhoto(),
                        productDetailModel.getData().getProduct().getStock().toString(),desCatEntityLocalArrayList);*/

                //db.globalCartDio().insertData(globalCartEntity);
                int length=0;
                length=db.globalCartDio().getAll().size();

                int finalLength = length;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("work in ui","gg");
                        if(finalLength >0) {
                            imgCart.setBadgeValue(finalLength)
                                    .setBadgeOvalAfterFirst(true)
                                    .setBadgeTextSize(16)
                                    .setMaxBadgeValue(999)
                                    //.setBadgeTextFont(typeface)
                                    .setBadgeBackground(getResources().getDrawable(R.drawable.badge_background))
                                    .setBadgePosition(BadgePosition.BOTTOM_RIGHT)
                                    .setBadgeTextStyle(Typeface.NORMAL)
                                    .setShowCounter(true)
                                    .setBadgePadding(4);
                        }

                    }
                });

            }
        });
    }

    private void intFonts() {
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));
        tvProductReviews.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvDec.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvProducName.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));
        tvProductPriceDiscount.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));

    }

    private void viewInitialization() {

        colorlayout.setVisibility(View.GONE);
        size_layout.setVisibility(View.VISIBLE);
        //tvSelectSize.setTextColor(getResources().getColor(R.color.colorBlack));
        //tvSelectColour.setTextColor(getResources().getColor(R.color.colorTextGrey));


        mPager = (ViewPager) findViewById(R.id.pager);
Log.e("pid",product_id);
        // now call product detail api from here
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("product_id", product_id);
       // requestBody.put("password", editPassword.getText().toString());

        RetrofitClientInstance.getRetrofitInstance().vendorProductsDetail(requestBody
        ).enqueue(new Callback<ProductDetailModel>() {

            @Override
            public void onResponse(Call<ProductDetailModel> call, Response<ProductDetailModel> response) {
                if(response.body().getStatus()){
                    productDetailModel=response.body();
imageModelArrayList= (ArrayList<ProductDetailModel.Data.Gallery>) productDetailModel.getData().getGalleries();
                    tvProducName.setText(productDetailModel.getData().getProduct().getName());

                    tvProductPriceDiscount.setText(productDetailModel.getData().getPrice().toString());
                    tvProductPrice.setText(productDetailModel.getData().getPreviousPrice().toString());
                    p_price=Double.parseDouble(tvProductPriceDiscount.getText().toString());
                    cd=Double.parseDouble(tvProductPriceDiscount.getText().toString());

ratingArrayList= (ArrayList<ProductDetailModel.Data.Rating>) productDetailModel.getData().getRatings();
categoryArrayList= (ArrayList<ProductDetailModel.Data.Category>) productDetailModel.getData().getCategory();

for(int i=0; i<ratingArrayList.size();i++){
    rating=rating+ratingArrayList.get(i).getRating();
}
double actual_rating=rating/ratingArrayList.size();
                    btnProductRating.setText(String.valueOf(new DecimalFormat("##.##").format(actual_rating)));
if(actual_rating>4.2){
    tvProductOverallreview.setText("Very Good");
}
else{
    tvProductOverallreview.setText("Good");
}

                    tvProductReviews.setText(productDetailModel.getData().getProduct().getViews()+" Reviews");


                    tvDescShort.setText(productDetailModel.getData().getProduct().getMetaDescription());

                    //tvProductOverallreview.setText();
                    mPager.setAdapter(new ImageSlidingAdapter(ProductDetailsActivity.this, imageModelArrayList));
                    //populateList();

                    CirclePageIndicator indicator = (CirclePageIndicator)
                            findViewById(R.id.indicator);
                    indicator.setViewPager(mPager);
                    final float density = getResources().getDisplayMetrics().density;
                    //Set circle indicator radius
                    indicator.setRadius(5 * density);
                    NUM_PAGES = imageModelArrayList.size();
                    // Auto start of viewpager
                    final Handler handler = new Handler();
                    final Runnable Update = new Runnable() {
                        public void run() {
                            if (currentPage == NUM_PAGES) {
                                currentPage = 0;
                            }
                            mPager.setCurrentItem(currentPage++, true);
                        }
                    };
                    Timer swipeTimer = new Timer();
                    swipeTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            handler.post(Update);
                        }
                    }, 3000, 3000);
                    // Pager listener over indicator
                    indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                        @Override
                        public void onPageSelected(int position) {
                            currentPage = position;
                        }

                        @Override
                        public void onPageScrolled(int pos, float arg1, int arg2) {
                        }

                        @Override
                        public void onPageScrollStateChanged(int pos) {

                        }
                    });


getCat();
                   // setDataSize();

                }

            }

            @Override
            public void onFailure(Call<ProductDetailModel> call, Throwable t) {

            }



        });



        // product api  close






       // setDataColor();
        //setDataSize();
    }

    @SuppressLint("WrongConstant")
    private void getCat() {

        LinearLayoutManager linearLayout = new LinearLayoutManager(getApplicationContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        cat_recycler.setHasFixedSize(true);
        cat_recycler.setLayoutManager(linearLayout);
        productCategoryAdapter = new ProductCategoryAdapter(getApplicationContext(),categoryArrayList,this);
        cat_recycler.setAdapter(productCategoryAdapter);
    }

    @SuppressLint("WrongConstant")
    private void setDataSize(int postion) {

        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        recyclerSize.setHasFixedSize(true);
        recyclerSize.setLayoutManager(linearLayout);
        productSizeAdapter = new ProductSizeAdapter(this,categoryArrayList,postion,this::onSend);
        recyclerSize.setAdapter(productSizeAdapter);
        productSizeAdapter.notifyDataSetChanged();
    }

    @SuppressLint("WrongConstant")
    private void setDataColor() {


        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        recyclerColor.setHasFixedSize(true);
        recyclerColor.setLayoutManager(linearLayout);
        productColorAdapter = new ProductColorAdapter(this);
        recyclerColor.setAdapter(productColorAdapter);
        productColorAdapter.notifyDataSetChanged();
    }




   /* private ArrayList<ImageModel> populateList() {

        ArrayList<ImageModel> list = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            ImageModel imageModel = new ImageModel();
            imageModel.setImage_drawable(imageModelArrayList.get(i));
            list.add(imageModel);
        }

        return list;
    }*/

    @OnClick({R.id.imgBack, R.id.btnProductRating,R.id.imgCart, R.id.tvProductReviews,R.id.product_add_to_cart, R.id.product_add_to_wishlist, R.id.imgFavrouit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.btnProductRating:
                break;
            case R.id.tvProductReviews:
                break;

            case R.id.imgCart:
                Intent i=new Intent(getApplicationContext(), CartActivity.class);
                startActivity(i);
                break;
           /* case R.id.tvSelectSize:
                colorlayout.setVisibility(View.GONE);
                size_layout.setVisibility(View.VISIBLE);
                tvSelectSize.setTextColor(getResources().getColor(R.color.colorBlack));
                tvSelectColour.setTextColor(getResources().getColor(R.color.colorTextGrey));
                break;
            case R.id.tvSelectColour:
                size_layout.setVisibility(View.GONE);
                colorlayout.setVisibility(View.VISIBLE);
                tvSelectSize.setTextColor(getResources().getColor(R.color.colorTextGrey));
                tvSelectColour.setTextColor(getResources().getColor(R.color.colorBlack));
                break;*/
            case R.id.product_add_to_cart:
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("common","incat");
                        GlobalCartDatabase db = Room.databaseBuilder(getApplicationContext(),
                                GlobalCartDatabase.class, "catglobal_db").build();
                        GlobalCartEntity globalCartEntity=new GlobalCartEntity(productDetailModel.getData().getProduct().getId(),
                                productDetailModel.getData().getProduct().getName(),
                                tvProductPriceDiscount.getText().toString(),
                                tvProductPriceDiscount.getText().toString(),
                                productDetailModel.getData().getProduct().getPreviousPrice().toString(),1,
                                productDetailModel.getData().getProduct().getPhoto(),
                                productDetailModel.getData().getProduct().getStock().toString(),desCatEntityLocalArrayList);

                        Boolean check= db.globalCartDio().isExist(productDetailModel.getData().getProduct().getId());
                        Log.e("bnbnb",check.toString());
                        if(check){
                            // db.localCatDio().updateSheetData(desCatEntityLocal);
                            db.globalCartDio().update(tvProductPriceDiscount.getText().toString(),
                                    productDetailModel.getData().getProduct().getId(), desCatEntityLocalArrayList);

                        }else {
                            db.globalCartDio().insertData(globalCartEntity);
                        }


                        int length=db.globalCartDio().getAll().size();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("work in ui","gg");
                                imgCart.setBadgeValue(length)
                                        .setBadgeOvalAfterFirst(true)
                                        .setBadgeTextSize(16)
                                        .setMaxBadgeValue(999)
                                        //.setBadgeTextFont(typeface)
                                        .setBadgeBackground(getResources().getDrawable(R.drawable.badge_background))
                                        .setBadgePosition(BadgePosition.BOTTOM_RIGHT)
                                        .setBadgeTextStyle(Typeface.NORMAL)
                                        .setShowCounter(true)
                                        .setBadgePadding(4);


                            }
                        });

                    }
                });


                break;
            case R.id.product_add_to_wishlist:
                // add to wishlist
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("common","incat");
                        WishListDataBase db = Room.databaseBuilder(getApplicationContext(),
                                WishListDataBase.class, "wishlist_db").build();
                        WishListEntity wishListEntity=new WishListEntity(productDetailModel.getData().getProduct().getId(),
                                productDetailModel.getData().getProduct().getName(),
                                tvProductPriceDiscount.getText().toString(),
                                tvProductPriceDiscount.getText().toString(),
                                productDetailModel.getData().getProduct().getPreviousPrice().toString(),
                                1,
                                productDetailModel.getData().getProduct().getPhoto(),
                                productDetailModel.getData().getProduct().getStock().toString(),desCatEntityLocalArrayList);

                        Boolean check= db.wishListDio().isExist(productDetailModel.getData().getProduct().getId());
                        Log.e("bnbnb",check.toString());
                        if(check){
                            // db.localCatDio().updateSheetData(desCatEntityLocal);
                            db.wishListDio().update(tvProductPriceDiscount.getText().toString(),
                                    productDetailModel.getData().getProduct().getId(), desCatEntityLocalArrayList);

                        }else {
                            db.wishListDio().insertData(wishListEntity);
                        }


                        int length=db.wishListDio().getAll().size();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("work in ui","gg");
                               // Toast.makeText(getApplicationContext(),"Product Added To WishList",Toast.LENGTH_LONG).show();

                                imgFavrouit.setImageDrawable(context.getResources().getDrawable(R.drawable.red_fav));

                                Animation animzoomin = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
                                Animation animzoomout = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out);
                                animzoomin.setAnimationListener(new Animation.AnimationListener() {
                                    @Override
                                    public void onAnimationStart(Animation animation) {
                                        imgFavrouit.startAnimation(animzoomin);
                                    }

                                    @Override
                                    public void onAnimationEnd(Animation animation) {
                                        imgFavrouit.startAnimation(animzoomout);
                                    }

                                    @Override
                                    public void onAnimationRepeat(Animation animation) {

                                    }
                                });

                                //imgFavrouit.startAnimation(animzoomin);
                               /* imgCart.setBadgeValue(length)
                                        .setBadgeOvalAfterFirst(true)
                                        .setBadgeTextSize(16)
                                        .setMaxBadgeValue(999)
                                        //.setBadgeTextFont(typeface)
                                        .setBadgeBackground(getResources().getDrawable(R.drawable.badge_background))
                                        .setBadgePosition(BadgePosition.BOTTOM_RIGHT)
                                        .setBadgeTextStyle(Typeface.NORMAL)
                                        .setShowCounter(true)
                                        .setBadgePadding(4);*/


                            }
                        });

                    }
                });


                // wishlist close
                break;
            case R.id.imgFavrouit:

                Intent o=new Intent(getApplicationContext(), MyFavrouitActivity.class);
                startActivity(o);



                if (isFavrouit == false) {
                    imgFavrouit.setImageResource(R.drawable.ic_fav);
                    isFavrouit = true;
                } else {
                    imgFavrouit.setImageResource(R.drawable.ic_unfavr);
                    isFavrouit = false;
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        colorlayout.setVisibility(View.GONE);
        size_layout.setVisibility(View.VISIBLE);
        //tvSelectSize.setTextColor(getResources().getColor(R.color.colorBlack));
       // tvSelectColour.setTextColor(getResources().getColor(R.color.colorTextGrey));

    }



    @Override
    public void onSetValues(int postion) {
       // Toast.makeText(getApplicationContext(),String.valueOf(postion),Toast.LENGTH_LONG).show();

        setDataSize(postion);
    }

    @Override
    public void onSend(int postion) {
        desCatEntityLocalArrayList=new ArrayList<DesCatEntityLocal>();


        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                Log.e("common","incat");
                LocalSlectCatDatabase db = Room.databaseBuilder(getApplicationContext(),
                        LocalSlectCatDatabase.class, "catlocal_db").build();
                desCatEntityLocalArrayList= (ArrayList<DesCatEntityLocal>) db.localCatDio().getAll();
               // adapter=new MyRecyclerViewAdapter(getApplicationContext(),sheetDatabaseModelList);
      // p_price=Double.parseDouble(tvProductPriceDiscount.getText().toString());

                StringBuilder sb=new StringBuilder();
                //recyclerView.setAdapter(adapter);
                for(int i=0;i<desCatEntityLocalArrayList.size();i++){
                 sb.append(desCatEntityLocalArrayList.get(i).getValue()+":"+desCatEntityLocalArrayList.get(i).getPrice()+"   ");

                    p_price=p_price+Double.parseDouble(desCatEntityLocalArrayList.get(i).getPrice());
                    Log.e("oo",cd+p_price.toString());
                }


                 //Double finalP_price = cd;
                Double finalCd = p_price;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("work in ui","gg");
                        catdata.setVisibility(View.VISIBLE);
                        catdata.setText(sb.toString());
                        tvProductPriceDiscount.setText(String.valueOf(finalCd));
                        p_price=cd;
                       // z_price=0.0;
                    }
                });
                // Person person = mDb.personDao().loadPersonById(mPersonId);
                //populateUI(person);
            }
        });


    }
}
