package com.idestore.ui.authentication.forgotPasswordActivity;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import com.google.android.material.textfield.TextInputLayout;
import com.idestore.R;
import com.idestore.baseclass.BaseClass;
import com.idestore.services.RetrofitClientInstance;
import com.idestore.ui.authentication.loginActivity.LoginActivity;
import com.idestore.ui.authentication.verifiy.VerifiyActivity;
import com.idestore.ui.model.ForgetPassModel;
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

public class ForgotPasswordActivity extends BaseClass {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.layout_actionbar)
    RelativeLayout layoutActionbar;
    @BindView(R.id.img_applog)
    ImageView imgApplog;
    @BindView(R.id.tv_restinst)
    TextView tv_restinst;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.editEmailNumber)
    EditText editEmailNumber;
    @BindView(R.id.inputEmailNumber)
    TextInputLayout inputEmailNumber;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    Context context;
    ForgetPassModel forgetPassModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        context=ForgotPasswordActivity.this;
        ButterKnife.bind(this);
        viewInitialization();
    }

    private void viewInitialization() {
        tv_restinst.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvTittle.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));

    }





    @OnClick({R.id.img_back, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_submit:

                if(editEmailNumber.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Pls Fill Your Email Address", Toast.LENGTH_LONG).show();
                }else {
                    Map<String, String> requestBody = new HashMap<>();
                    requestBody.put("email", editEmailNumber.getText().toString());
                    //requestBody.put("password", editPassword.getText().toString());

                    RetrofitClientInstance.getRetrofitInstance().forgetpass(requestBody
                    ).enqueue(new Callback<ForgetPassModel>() {
                        @Override
                        public void onResponse(Call<ForgetPassModel> call, Response<ForgetPassModel> response) {
                            if(response.body().getStatus()) {
                                forgetPassModel = response.body();

                                //Log.e("chooooo", tvGmail.getText().toString());
                                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                                //progressDialog.dismiss();
                                 finish();
                                //Intent i = new Intent(ForgotPasswordActivity.this, VerifiyActivity.class);
                                //i.putExtra("email",editEmailNumber.getText().toString());
                                //startActivity(i);
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Invalid  Detail.", Toast.LENGTH_LONG).show();
                            }


                        }

                        @Override
                        public void onFailure(Call<ForgetPassModel> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Invalid Email.", Toast.LENGTH_LONG).show();
                        }


                    });

                }


              // Intent intent = new Intent(ForgotPasswordActivity.this, VerifiyActivity.class);
               // startActivity(intent);
                break;
        }
    }
}
