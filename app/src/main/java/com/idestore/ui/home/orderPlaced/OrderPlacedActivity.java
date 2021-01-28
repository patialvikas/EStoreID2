package com.idestore.ui.home.orderPlaced;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.idestore.R;
import com.idestore.utils.UtilsFontFamily;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderPlacedActivity extends AppCompatActivity {

    Context context;


    @BindView(R.id.order_placed_image)
    ImageView order_placed_image;

    @BindView(R.id.orderplace_text)
    TextView orderplace_text;

    @BindView(R.id.orderplace_decstext)
    TextView orderplace_decstext;

    @BindView(R.id.btnMyorders)
    Button btnMyorders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placed);
        ButterKnife.bind(this);

        context = OrderPlacedActivity.this;

        viewInitialization();
    }

    private void viewInitialization() {
        orderplace_text.setTypeface(UtilsFontFamily.typeFaceForRobotoBold(context));
    }



    @OnClick( R.id.btnMyorders)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnMyorders:
                break;
        }
    }
}
