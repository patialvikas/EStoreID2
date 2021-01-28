package com.idestore.ui.home.myOrderDetails;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.idestore.R;
import com.idestore.baseclass.BaseClass;
import com.idestore.ui.home.cancelOrder.CancelOrderActivity;
import com.idestore.ui.home.myOrderDetails.adapter.OrderDiscountAdapter;
import com.idestore.utils.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyOrderDetailsActivity extends BaseClass {

    @BindView(R.id.imgBack)
    ImageView imgBack;

    @BindView(R.id.layoutBar)
    RelativeLayout layoutBar;

    @BindView(R.id.tv_OrderID)
    TextView tv_OrderID;

    @BindView(R.id.tv_orderStatus)
    TextView tv_orderStatus;

    @BindView(R.id.tv_orderTiming)
    TextView tv_orderTiming;

    @BindView(R.id.recycler_order)
    RecyclerView recycler_order;


    @BindView(R.id.tv_OrderName)
    TextView tv_OrderName;

    @BindView(R.id.tv_SubPrice)
    TextView tv_SubPrice;

    @BindView(R.id.tv_tax)
    TextView tv_tax;

    @BindView(R.id.tv_taxPrice)
    TextView tv_taxPrice;

    @BindView(R.id.tv_devlerStatus)
    TextView tv_devlerStatus;

    @BindView(R.id.tv_deliveryPrice)
    TextView tv_deliveryPrice;

    @BindView(R.id.tv_total)
    TextView tv_total;

    @BindView(R.id.tv_totalPrice)
    TextView tv_totalPrice;


    @BindView(R.id.cancel_order)
    Button cancel_order;

    @BindView(R.id.tvTittle)
    TextView tvTittle;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_details);
        context = MyOrderDetailsActivity.this;
        ButterKnife.bind(this);

        viewInitialization();
        initFonts();

    }


    private void viewInitialization() {


        OrderDiscountAdapter orderDiscountAdapter = new OrderDiscountAdapter(this);
        recycler_order.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycler_order.setAdapter(orderDiscountAdapter);

    }



    @OnClick({R.id.imgBack, R.id.cancel_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.cancel_order:
                Intent intent = new Intent(MyOrderDetailsActivity.this, CancelOrderActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void initFonts() {
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));
        tv_OrderID.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));
        tv_orderTiming.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));
        tv_OrderName.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));

        tv_totalPrice.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tv_total.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tv_deliveryPrice.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tv_devlerStatus.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tv_taxPrice.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tv_tax.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tv_SubPrice.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tv_OrderName.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));

    }
}
