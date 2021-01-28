package com.idestore.ui.home.myfavrouitStore;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.idestore.R;
import com.idestore.baseclass.BaseClass;
import com.idestore.ui.home.myfavrouitStore.adapter.FavrouitStoreAdapter;
import com.idestore.utils.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyFavrouitStoreActivity extends BaseClass {

    @BindView(R.id.imgBack)
    ImageView imgBack;

    @BindView(R.id.recyclerFavoriteStore)
    RecyclerView recyclerFavoriteStore;

    @BindView(R.id.tvTittle)
    TextView tvTittle;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favrouit_store);
        context= MyFavrouitStoreActivity.this;
        ButterKnife.bind(this);

        viewInitialization();

    }

    private void viewInitialization() {
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));
        FavrouitStoreAdapter favoriteStoreAdapter = new FavrouitStoreAdapter(this);
        recyclerFavoriteStore.setLayoutManager(new LinearLayoutManager(this));
        recyclerFavoriteStore.setAdapter(favoriteStoreAdapter);

    }



    @OnClick(R.id.imgBack)
    public void onViewClicked() {
        finish();

    }
}
