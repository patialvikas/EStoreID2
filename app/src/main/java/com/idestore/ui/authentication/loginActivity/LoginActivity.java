package com.idestore.ui.authentication.loginActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import com.google.android.material.textfield.TextInputLayout;
import com.idestore.R;
import com.idestore.baseclass.BaseClass;

import com.idestore.services.RetrofitClientInstance;
import com.idestore.ui.authentication.forgotPasswordActivity.ForgotPasswordActivity;
import com.idestore.ui.authentication.signupActivity.SignUpActivity;
import com.idestore.ui.authentication.verifiy.VerifiyActivity;
import com.idestore.ui.home.mainActivity.MainActivity;
import com.idestore.ui.model.UserLoginModel;
import com.idestore.ui.settings.SettingsActivity;
import com.idestore.utils.UtilsFontFamily;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;

public class LoginActivity extends BaseClass  {

    @BindView(R.id.imagelogo)
    ImageView imagelogo;
    @BindView(R.id.editEmailNumber)
    EditText editEmailNumber;
    @BindView(R.id.inputLayoutName)
    TextInputLayout inputLayoutName;
    @BindView(R.id.editPassword)
    EditText editPassword;
    @BindView(R.id.inputLayoutPassword)
    TextInputLayout inputLayoutPassword;
    @BindView(R.id.tvForgotPassword)
    TextView tvForgotPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.tvNew)
    TextView tvNew;
    @BindView(R.id.tvSignUp)
    TextView tvSignUp;
    @BindView(R.id.layoutSignUp)
    LinearLayout layoutSignUp;
    @BindView(R.id.tv_or)
    TextView tvOr;
    @BindView(R.id.realtive_or)
    RelativeLayout realtiveOr;
    @BindView(R.id.tvFacebook)
    TextView tvFacebook;
    @BindView(R.id.tvGmail)
    TextView tvGmail;
    @BindView(R.id.linear_sociallogin)
    LinearLayout linearSociallogin;
    @BindView(R.id.realtive_social_login)
    RelativeLayout realtiveSocialLogin;

    Context context;
    UserLoginModel userLoginModel;

        // latest code
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context=LoginActivity.this;
        ButterKnife.bind(this);
        viewInitialization();

        SharedPreferences mylogin =getSharedPreferences("MyLogin",Context.MODE_PRIVATE);
        try {
            String c = mylogin.getString("Logstatus", null);
            if (c != null) {
                if (c.matches("true")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }



    private void viewInitialization() {
        tvSignUp.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
        tvForgotPassword.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));

    }


    @OnClick({R.id.tvForgotPassword, R.id.btnLogin, R.id.tvFacebook, R.id.tvGmail, R.id.tvSignUp})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.tvForgotPassword:
                intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.btnLogin:
if(editEmailNumber.getText().toString().isEmpty()||editPassword.getText().toString().isEmpty()) {
    Toast.makeText(getApplicationContext(), "Pls Fill Both The Fields", Toast.LENGTH_LONG).show();
}else {
    Map<String, String> requestBody = new HashMap<>();
    requestBody.put("email", editEmailNumber.getText().toString());
    requestBody.put("password", editPassword.getText().toString());

    RetrofitClientInstance.getRetrofitInstance().loginuser(requestBody
    ).enqueue(new Callback<UserLoginModel>() {
        @Override
        public void onResponse(Call<UserLoginModel> call, Response<UserLoginModel> response) {
            if(response.body().isStatus()) {
                userLoginModel = response.body();

                //Log.e("chooooo", tvGmail.getText().toString());
                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                //progressDialog.dismiss();
                SharedPreferences mylogin =getSharedPreferences("MyLogin",Context.MODE_PRIVATE);
                SharedPreferences.Editor ed=mylogin.edit();
                ed.putString("Logstatus","true");
                ed.putString("userid",String.valueOf(userLoginModel.getData().getUser().getId()));
                ed.commit();

                Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            else{
                Toast.makeText(getApplicationContext(), "Invalid Login Detail.", Toast.LENGTH_LONG).show();
            }


        }

        @Override
        public void onFailure(Call<UserLoginModel> call, Throwable t) {
           // t.fillInStackTrace();
            Toast.makeText(getApplicationContext(), "Invalid Login Detail.\n Or Something Went Wrong.", Toast.LENGTH_LONG).show();
        }
    });

}
                break;
            case R.id.tvGmail:
                break;
            case R.id.tvFacebook:
                break;
            case R.id.tvSignUp:
                intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }
}
