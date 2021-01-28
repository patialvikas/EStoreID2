package com.idestore.ui.settings.changePassword;

import android.content.Context;
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

import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputLayout;
import com.idestore.R;
import com.idestore.baseclass.BaseClass;
import com.idestore.utils.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChnagePasswordActivity extends BaseClass {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.editOldPassword)
    EditText editOldPassword;
    @BindView(R.id.inputLayoutOldPassword)
    TextInputLayout inputLayoutOldPassword;
    @BindView(R.id.editNewPassword)
    EditText editNewPassword;
    @BindView(R.id.inputLayoutNewPassword)
    TextInputLayout inputLayoutNewPassword;
    @BindView(R.id.editConfrimNewPassword)
    EditText editConfrimNewPassword;
    @BindView(R.id.inputLayoutConfrimNewPassword)
    TextInputLayout inputLayoutConfrimNewPassword;
    @BindView(R.id.btnResetPassword)
    Button btnResetPassword;
    @BindView(R.id.tvTittle)
    TextView tvTittle;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chnage_password);
        context=ChnagePasswordActivity.this;
        ButterKnife.bind(this);
        viewInitialization();
    }

    private void viewInitialization() {
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));
    }



    @OnClick({R.id.imgBack, R.id.btnResetPassword})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.btnResetPassword:
                break;
        }
    }
}
