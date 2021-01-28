package com.idestore.ui.home.myAccount;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import de.hdodenhof.circleimageview.CircleImageView;

public class MyAccountActivity extends BaseClass {

    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.ImagProfileImage)
    CircleImageView ImagProfileImage;
    @BindView(R.id.tvUsername)
    TextView tvUsername;
    @BindView(R.id.userLocation)
    TextView userLocation;
    @BindView(R.id.layout_profile)
    RelativeLayout layoutProfile;
    @BindView(R.id.btnFollowing)
    Button btnFollowing;
    @BindView(R.id.btnRewardspoints)
    Button btnRewardspoints;
    @BindView(R.id.layout_one)
    LinearLayout layoutOne;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.editUserName)
    EditText editUserName;
    @BindView(R.id.inputLayoutUserName)
    TextInputLayout inputLayoutUserName;
    @BindView(R.id.editEmail)
    EditText editEmail;
    @BindView(R.id.inputLayoutEmail)
    TextInputLayout inputLayoutEmail;
    @BindView(R.id.editMobileNumber)
    EditText editMobileNumber;
    @BindView(R.id.inputLayoutMobileNumber)
    TextInputLayout inputLayoutMobileNumber;
    @BindView(R.id.editPassword)
    EditText editPassword;
    @BindView(R.id.inputLayoutGender)
    TextInputLayout inputLayoutGender;
    @BindView(R.id.editDateofbirth)
    EditText editDateofbirth;
    @BindView(R.id.inputLayoutDateofbirth)
    TextInputLayout inputLayoutDateofbirth;
    @BindView(R.id.tvTittle)
    TextView tvTittle;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        context=MyAccountActivity.this;
        ButterKnife.bind(this);
        viewInitialization();

    }

    private void viewInitialization() {
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));

    }



    @OnClick({R.id.imgBack, R.id.btnFollowing, R.id.btnRewardspoints})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.btnFollowing:
                break;
            case R.id.btnRewardspoints:
                break;
        }
    }
}
