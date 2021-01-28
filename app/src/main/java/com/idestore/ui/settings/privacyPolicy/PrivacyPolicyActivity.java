package com.idestore.ui.settings.privacyPolicy;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.codesgood.views.JustifiedTextView;
import com.idestore.R;
import com.idestore.baseclass.BaseClass;
import com.idestore.utils.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrivacyPolicyActivity extends BaseClass {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.layout_actionbar)
    RelativeLayout layoutActionbar;
    @BindView(R.id.tv_privacy_policy)
    JustifiedTextView tvPrivacyPolicy;
    @BindView(R.id.tvTittle)
    TextView tvTittle;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        context=PrivacyPolicyActivity.this;
        ButterKnife.bind(this);

        viewInitialization();
    }

    private void viewInitialization() {
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }



}
