package com.idestore.ui.home.sendFeedback;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputLayout;
import com.idestore.R;
import com.idestore.baseclass.BaseClass;
import com.idestore.utils.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendFeedbackActivity extends BaseClass {

    @BindView(R.id.imgBack)
    ImageView imgBack;

    @BindView(R.id.layoutBar)
    RelativeLayout layoutBar;

    @BindView(R.id.tvfeedback)
    TextView tvfeedback;

    @BindView(R.id.tv_alertMsg)
    TextView tv_alertMsg;

    @BindView(R.id.editFeedback)
    EditText editFeedback;

    @BindView(R.id.inputFeedback)
    TextInputLayout inputFeedback;

    @BindView(R.id.tvSubmit)
    TextView tvSubmit;

    @BindView(R.id.tvTittle)
    TextView tvTittle;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_feedback);
        context = SendFeedbackActivity.this;
        ButterKnife.bind(this);
        viewInitialization();

    }

    private void viewInitialization() {
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));
        tvfeedback.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));

    }



    @OnClick({R.id.imgBack, R.id.tvSubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.tvSubmit:
                break;
        }
    }
}
