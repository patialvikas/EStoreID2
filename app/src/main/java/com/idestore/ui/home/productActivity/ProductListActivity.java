package com.idestore.ui.home.productActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.idestore.R;
import com.idestore.baseclass.BaseClass;
import com.idestore.ui.home.AppExecutors;
import com.idestore.ui.home.cartActivity.CartActivity;
import com.idestore.ui.home.productActivity.adapter.ProductAdapter;
import com.idestore.ui.home.productDetails.GlobalDatabase.GlobalCartDatabase;
import com.idestore.ui.model.VendorProductsModel;
import com.idestore.utils.UtilsFontFamily;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.nikartm.support.BadgePosition;
import ru.nikartm.support.ImageBadgeView;

public class ProductListActivity extends BaseClass implements SearchView.OnQueryTextListener {

    @BindView(R.id.imgBack)
    ImageView imgBack;

    @BindView(R.id.tvTittle)
    TextView tvTittle;

    @BindView(R.id.imgCart)
    ImageBadgeView imgCart;

    @BindView(R.id.toolbar)
    RelativeLayout toolbar;

    @BindView(R.id.totalProducts)
    TextView totalProducts;

    @BindView(R.id.productListview)
    ImageButton productListview;

    @BindView(R.id.productGrid)
    ImageButton productGrid;

    @BindView(R.id.productSort)
    ImageButton productSort;

    @BindView(R.id.recyclerviewProduct)
    RecyclerView recyclerviewProduct;

    @BindView(R.id.searchView)
    SearchView searchView;

    Context context;
    String type = "layout_list";
    ProductAdapter adapter;
    VendorProductsModel vendorProductsModel;
    ArrayList<VendorProductsModel.Data.Product>list;

    GlobalCartDatabase dbi;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbi.close();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //dbi.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        list=new ArrayList<VendorProductsModel.Data.Product>();
        Intent i = getIntent();
        list = (ArrayList<VendorProductsModel.Data.Product>) i.getSerializableExtra("list");

        //Log.e("length",list.get(0).getName());
        //totalProducts.setText();

        context = ProductListActivity.this;
        ButterKnife.bind(this);

        viewInitialization();
        checkForBadge();
    }

    private void checkForBadge() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                Log.e("common","incat");
                dbi = Room.databaseBuilder(getApplicationContext(),
                        GlobalCartDatabase.class, "catglobal_db").build();
                /*GlobalCartEntity globalCartEntity=new GlobalCartEntity(productDetailModel.getData().getProduct().getId(),
                        productDetailModel.getData().getProduct().getName(),tvProductPriceDiscount.getText().toString(),
                        productDetailModel.getData().getProduct().getPreviousPrice().toString(),productDetailModel.getData().getProduct().getPhoto(),
                        productDetailModel.getData().getProduct().getStock().toString(),desCatEntityLocalArrayList);*/

                //db.globalCartDio().insertData(globalCartEntity);
                int length=0;
                length=dbi.globalCartDio().getAll().size();

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

    private void viewInitialization() {
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));
        setLayoutData(type);

        totalProducts.setText(String.valueOf(list.size()));
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




    @OnClick({R.id.imgBack,  R.id.imgCart, R.id.productListview, R.id.productGrid, R.id.productSort})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;

            case R.id.imgCart:
                //Toast.makeText(context, "click", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(), CartActivity.class);
                startActivity(i);
                break;
            case R.id.productListview:
                type = "layout_list";
                setLayoutData(type);
                break;
            case R.id.productGrid:
                type = "layout_grid";
                setLayoutData(type);
                break;
            case R.id.productSort:

                break;
        }
    }

    @SuppressLint("WrongConstant")
    private void setLayoutData(String type) {
        if (type.equals("layout_list")) {
            LinearLayoutManager linearLayout = new LinearLayoutManager(this);
            productListview.setImageDrawable(getResources().getDrawable(R.drawable.ic_list_active));
            productGrid.setImageDrawable(getResources().getDrawable(R.drawable.ic_horizonatal_disable));

            linearLayout.setOrientation(LinearLayout.VERTICAL);
            recyclerviewProduct.setHasFixedSize(true);
            recyclerviewProduct.setLayoutManager(linearLayout);
            adapter = new ProductAdapter(this, type,list);
            recyclerviewProduct.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else if (type.equals("layout_grid")) {
            productListview.setImageDrawable(getResources().getDrawable(R.drawable.ic_vertical));
            productGrid.setImageDrawable(getResources().getDrawable(R.drawable.ic_horizontal));

            GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
            recyclerviewProduct.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
            adapter = new ProductAdapter(this, type,list);
            recyclerviewProduct.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {

        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
