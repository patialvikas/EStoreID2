package com.idestore.ui.settings.editProfileActivity;

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

public class EditProfileActivity extends BaseClass {

    @BindView(R.id.imgBack)
    ImageView imgBack;

    @BindView(R.id.ImagProfileImage)
    CircleImageView ImagProfileImage;
    @BindView(R.id.imgMediaPicker)
    ImageView imgMediaPicker;
    @BindView(R.id.layout_profile)
    RelativeLayout layoutProfile;
    @BindView(R.id.editFirstName)
    EditText editFirstName;
    @BindView(R.id.inputLayoutFirstName)
    TextInputLayout inputLayoutFirstName;
    @BindView(R.id.editLastName)
    EditText editLastName;
    @BindView(R.id.inputLayoutLastName)
    TextInputLayout inputLayoutLastName;
    @BindView(R.id.layoutUserName)
    LinearLayout layoutUserName;
    @BindView(R.id.editEmail)
    EditText editEmail;
    @BindView(R.id.inputLayoutEmail)
    TextInputLayout inputLayoutEmail;
    @BindView(R.id.editAddress)
    EditText editAddress;
    @BindView(R.id.inputLayoutAddress)
    TextInputLayout inputLayoutAddress;
    @BindView(R.id.editPassword)
    EditText editPassword;
    @BindView(R.id.inputLayoutGender)
    TextInputLayout inputLayoutGender;
    @BindView(R.id.editDateofbirth)
    EditText editDateofbirth;
    @BindView(R.id.inputLayoutDateofbirth)
    TextInputLayout inputLayoutDateofbirth;
    @BindView(R.id.layout_updateprofile)
    RelativeLayout layoutUpdateprofile;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.tvTittle)
    TextView tvTittle;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        context=EditProfileActivity.this;
        ButterKnife.bind(this);

        viewInitialization();
    }

    private void viewInitialization() {
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));
    }



    @OnClick({R.id.imgBack, R.id.imgMediaPicker, R.id.btnLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.imgMediaPicker:
                break;
            case R.id.btnLogin:
                break;
        }
    }
}
