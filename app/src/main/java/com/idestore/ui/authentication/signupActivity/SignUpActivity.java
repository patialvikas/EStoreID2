package com.idestore.ui.authentication.signupActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import com.google.android.material.textfield.TextInputLayout;
import com.idestore.R;
import com.idestore.baseclass.BaseClass;
import com.idestore.services.RetrofitClientInstance;
import com.idestore.ui.authentication.loginActivity.LoginActivity;
import com.idestore.ui.authentication.verifiy.VerifiyActivity;
import com.idestore.ui.model.SignUpModel;
import com.idestore.ui.model.UserLoginModel;
import com.idestore.utils.UtilsFontFamily;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends BaseClass {

    @BindView(R.id.imagelogo)
    ImageView imagelogo;
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
    @BindView(R.id.editMobileNumber)
    EditText editMobileNumber;
    @BindView(R.id.inputLayoutMobileNumber)
    TextInputLayout inputLayoutMobileNumber;
    @BindView(R.id.editPassword)
    EditText editPassword;
    @BindView(R.id.inputLayoutPassword)
    TextInputLayout inputLayoutPassword;
    @BindView(R.id.editConfirmPassword)
    EditText editConfirmPassword;
    @BindView(R.id.inputLayoutConfirmPassword)
    TextInputLayout inputLayoutConfirmPassword;
    @BindView(R.id.btnRadio)
    RadioButton btnRadio;
    @BindView(R.id.tvRead)
    TextView tvRead;
    @BindView(R.id.tvTermsCondition)
    TextView tvTermsCondition;
    @BindView(R.id.tvand)
    TextView tvand;
    @BindView(R.id.tvPrivacyPolicy)
    TextView tvPrivacyPolicy;
    @BindView(R.id.layoutTearms)
    LinearLayout layoutTearms;
    @BindView(R.id.btnSignup)
    Button btnSignup;
    @BindView(R.id.tvAccount)
    TextView tvAccount;
    @BindView(R.id.tvLogin)
    TextView tvLogin;
    @BindView(R.id.layoutSignUp)
    LinearLayout layoutSignUp;
    Context context;
    Boolean is_checked=false;
    SignUpModel signUpModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        context = SignUpActivity.this;
        ButterKnife.bind(this);
        viewInitialization();
    }




    @OnClick({R.id.btnRadio, R.id.tvTermsCondition, R.id.tvPrivacyPolicy, R.id.btnSignup, R.id.tvLogin})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btnRadio:
                is_checked=true;
                break;
            case R.id.tvTermsCondition:

                break;
            case R.id.tvPrivacyPolicy:

                break;
            case R.id.btnSignup:
                if(editFirstName.getText().toString().isEmpty()||editLastName.getText().toString().isEmpty()
                ||editEmail.getText().toString().isEmpty()||editMobileNumber.getText().toString().isEmpty()||
                        editPassword.getText().toString().isEmpty()||editConfirmPassword.getText().toString().isEmpty()||!is_checked

                ) {
                    Toast.makeText(getApplicationContext(), "Pls Fill All Fields \n And Agree On Our Terms.", Toast.LENGTH_LONG).show();
                }else {
                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("first", editFirstName.getText().toString());
                    requestBody.put("last", editLastName.getText().toString());
                    requestBody.put("email", editEmail.getText().toString());
                    requestBody.put("phone", editMobileNumber.getText().toString());
                    requestBody.put("password", editPassword.getText().toString());
                    requestBody.put("password_confirmation", editConfirmPassword.getText().toString());
                    requestBody.put("agree", "true");

                    RetrofitClientInstance.getRetrofitInstance().registeruser(requestBody
                    ).enqueue(new Callback<SignUpModel>() {
                        @Override
                        public void onResponse(Call<SignUpModel> call, Response<SignUpModel> response) {
                            if (response.body().getStatus()) {
                                signUpModel = response.body();

                                //Log.e("chooooo", tvGmail.getText().toString());
                                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                                //progressDialog.dismiss();

                                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                            }


                        }

                        @Override
                        public void onFailure(Call<SignUpModel> call, Throwable t) {

                        }


                    });
                }
                break;
            case R.id.tvLogin:
                intent = new Intent(SignUpActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void viewInitialization() {
        tvLogin.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvPrivacyPolicy.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvTermsCondition.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));

    }
}
