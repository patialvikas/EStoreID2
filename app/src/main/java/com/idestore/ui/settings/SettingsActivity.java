package com.idestore.ui.settings;

import android.content.Context;
import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import com.idestore.R;
import com.idestore.baseclass.BaseClass;
import com.idestore.ui.authentication.loginActivity.LoginActivity;
import com.idestore.ui.settings.changePassword.ChnagePasswordActivity;
import com.idestore.ui.settings.editProfileActivity.EditProfileActivity;
import com.idestore.ui.settings.updatePhoneNumber.UpdatePhoneActivity;
import com.idestore.utils.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends BaseClass {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.tvUpdateProfile)
    TextView tvUpdateProfile;
    @BindView(R.id.tvUpdatePhone)
    TextView tvUpdatePhone;
    @BindView(R.id.tvChangePassword)
    TextView tvChangePassword;
    @BindView(R.id.tvPrivacyPolicy)
    TextView tvPrivacyPolicy;
    @BindView(R.id.tvTermsCondition)
    TextView tvTermsCondition;
    @BindView(R.id.btnLogout)
    Button btnLogout;
     @BindView(R.id.tvTittle)
    TextView tvTittle;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        context=SettingsActivity.this;
        ButterKnife.bind(this);
        viewInitialization();
    }

    private void viewInitialization() {
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));
    }



    @OnClick({R.id.imgBack, R.id.tvUpdateProfile, R.id.tvUpdatePhone, R.id.tvChangePassword, R.id.tvPrivacyPolicy, R.id.tvTermsCondition, R.id.btnLogout})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.tvUpdateProfile:
                intent = new Intent(SettingsActivity.this, EditProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.tvUpdatePhone:
                intent = new Intent(SettingsActivity.this, UpdatePhoneActivity.class);
                startActivity(intent);
                break;
            case R.id.tvChangePassword:
                intent = new Intent(SettingsActivity.this, ChnagePasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.tvPrivacyPolicy:

                break;
            case R.id.tvTermsCondition:
                break;
            case R.id.btnLogout:
                logoutAccount();
                break;
        }
    }

    private void logoutAccount() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.DialogeTheme);
        builder.setCancelable(true);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish(); // if the activity running has it's own context
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
