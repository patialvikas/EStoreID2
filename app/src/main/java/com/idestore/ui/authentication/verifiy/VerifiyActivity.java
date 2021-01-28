package com.idestore.ui.authentication.verifiy;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.chaos.view.PinView;
import com.idestore.R;
import com.idestore.baseclass.BaseClass;
import com.idestore.ui.home.mainActivity.MainActivity;
import com.idestore.utils.UtilsFontFamily;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VerifiyActivity extends BaseClass {

    @BindView(R.id.img_applog)
    ImageView imgApplog;
    @BindView(R.id.tvCodeSebt)
    TextView tvCodeSebt;
    @BindView(R.id.otpView)
    PinView otpView;
    @BindView(R.id.tvResedCode)
    TextView tvResedCode;
    @BindView(R.id.tvCountDown)
    TextView tvCountDown;
    @BindView(R.id.layout_timer)
    LinearLayout layoutTimer;
    @BindView(R.id.tvCodeDidntArrive)
    TextView tvCodeDidntArrive;
    @BindView(R.id.btnGetCall)
    Button btnGetCall;
    @BindView(R.id.layout_resend)
    RelativeLayout layoutResend;
    @BindView(R.id.btnVerify)
    Button btnVerify;

    Context context;
    Dialog progressDialog;
    public static boolean isViewClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifiy);
        context = VerifiyActivity.this;
        ButterKnife.bind(this);
        viewInitialization();
    }

    private void viewInitialization() {
        tvCodeSebt.setTypeface(UtilsFontFamily.typeFaceForRobotoMedium(context));
    }


    @OnClick({R.id.tvResedCode, R.id.btnGetCall, R.id.btnVerify})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvResedCode:
                tvCountDown.setVisibility(View.VISIBLE);
                btnVerify.setVisibility(View.INVISIBLE);
                tvResedCode.setTextColor(getResources().getColor(R.color.colorWhite));
                tvCountDown.setTextColor(getResources().getColor(R.color.colorWhite));

                if (!isViewClicked) {
                    isViewClicked = true;
                    startTimer();
                } else {
                    return;
                }
                break;

            case R.id.btnGetCall:
                break;
            case R.id.btnVerify:
                Intent intent=new Intent(VerifiyActivity.this, MainActivity.class);
                startActivity(intent);
                break;

        }
    }

    private void startTimer() {
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                tvCountDown.setText("( " + "00 : " + " " + millisUntilFinished / 1000 + " ) ");
                tvResedCode.setVisibility(View.VISIBLE);
                tvCountDown.setVisibility(View.VISIBLE);
                tvCodeDidntArrive.setVisibility(View.GONE);
                btnGetCall.setVisibility(View.GONE);
                btnVerify.setVisibility(View.INVISIBLE);
            }

            public void onFinish() {
                tvResedCode.setVisibility(View.GONE);
                tvCountDown.setVisibility(View.GONE);
                tvCodeDidntArrive.setVisibility(View.VISIBLE);
                btnGetCall.setVisibility(View.VISIBLE);
                btnVerify.setVisibility(View.INVISIBLE);
                tvCodeDidntArrive.setText("The SMS with the code didn't arrive?");
            }

        }.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
