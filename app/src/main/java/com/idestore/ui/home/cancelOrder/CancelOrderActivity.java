package com.idestore.ui.home.cancelOrder;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.idestore.R;
import com.idestore.baseclass.BaseClass;
import com.idestore.ui.home.sendFeedback.SendFeedbackActivity;
import com.idestore.utils.UtilsFontFamily;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CancelOrderActivity extends BaseClass {

    @BindView(R.id.imgBack)
    ImageView imgBack;

    @BindView(R.id.toolbar)
    RelativeLayout toolbar;

    @BindView(R.id.img_question)
    ImageView imgQuestion;

    @BindView(R.id.tv_cancel_Order)
    TextView tv_cancel_Order;

    @BindView(R.id.tv_alertMsg)
    TextView tvAlertMsg;

    @BindView(R.id.tvYesCancel)
    TextView tvYesCancel;

    @BindView(R.id.tvNoCancel)
    TextView tvNoCancel;

    @BindView(R.id.tvTittle)
    TextView tvTittle;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_order);
        context = CancelOrderActivity.this;
        ButterKnife.bind(this);
        viewInitialization();
    }

    private void viewInitialization() {
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));
        tvYesCancel.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tv_cancel_Order.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));
    }



    @OnClick({R.id.imgBack, R.id.tvYesCancel, R.id.tvNoCancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.tvYesCancel:
                break;
            case R.id.tvNoCancel:
                Intent intent = new Intent(CancelOrderActivity.this, SendFeedbackActivity.class);
                startActivity(intent);
                break;
        }
    }
}
