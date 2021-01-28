package com.idestore.ui.home.paymentActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.idestore.R;
import com.idestore.baseclass.BaseClass;
import com.idestore.ui.home.orderPlaced.OrderPlacedActivity;
import com.idestore.ui.home.paymentActivity.adapters.DeleviryAddressAdapter;
import com.idestore.ui.home.paymentActivity.adapters.PaymentMethodAdapter;
import com.idestore.utils.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheckOutActivity extends BaseClass {


    Context context;

    @BindView(R.id.imgBack)
    ImageView imgBack;

    @BindView(R.id.tvTittle)
    TextView tvTittle;

    @BindView(R.id.toolbar)
    RelativeLayout toolbar;

    @BindView(R.id.tvDeleveryAddress)
    TextView tvDeleveryAddress;

    @BindView(R.id.recyclerDeleveryAddress)
    RecyclerView recyclerDeleveryAddress;
    @BindView(R.id.tvmob)
    TextView tvmob;
    @BindView(R.id.mobedit)
    EditText mob;
String tot_price;


    @BindView(R.id.btn_payment)
    Button btn_payment;

    DeleviryAddressAdapter deleviryAddressAdapter;
    PaymentMethodAdapter paymentMethodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        ButterKnife.bind(this);
        context = CheckOutActivity.this;

        viewInitialization();
    }

    private void viewInitialization() {
Intent i=getIntent();
tot_price=i.getStringExtra("price");
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));
        btn_payment.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));

        deleviryAddressAdapter = new DeleviryAddressAdapter(this);
        recyclerDeleveryAddress.setLayoutManager(new LinearLayoutManager(this));
        recyclerDeleveryAddress.setAdapter(deleviryAddressAdapter);

        paymentMethodAdapter = new PaymentMethodAdapter(this);


       // recyclerViewPaymentMethod.setLayoutManager(new LinearLayoutManager(this));
        //recyclerViewPaymentMethod.setAdapter(paymentMethodAdapter);

    }



    @OnClick({R.id.imgBack, R.id.btn_payment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.btn_payment:
               // Intent intent = new Intent(CheckOutActivity.this, OrderPlacedActivity.class);
                if(!mob.getText().toString().isEmpty()) {
                    Intent intent = new Intent(CheckOutActivity.this, PaymentActivity.class);
                    intent.putExtra("mob", mob.getText().toString());
                    intent.putExtra("price",tot_price);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Mobile No. is required need to get otp durng payment.",
                            Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
