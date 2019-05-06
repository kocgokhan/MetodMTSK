package com.metodmtsk.Activitiy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;

import com.metodmtsk.R;
import com.metodmtsk.Util.MyApplication;

public class SignActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView btn1;
    ImageView btn2;
    ImageView btn3;
    ImageView btn4;
    ImageView btn5;
    ImageView btn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);


        btn1 = (ImageView) findViewById(R.id.btn1);
        btn2 = (ImageView) findViewById(R.id.btn2);
        btn3 = (ImageView) findViewById(R.id.btn3);
        btn4 = (ImageView) findViewById(R.id.btn4);
        btn5 = (ImageView) findViewById(R.id.btn5);
        btn6 = (ImageView) findViewById(R.id.btn6);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);


        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#273D52\" >" + "Trafik Levhaları" + "</font>")));


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_toolbar_arrow);



    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }

    @Override
    public void onClick(View v) {


        Intent intocan = new Intent(SignActivity.this, SignDetailActivity.class);
        Bundle extras = new Bundle();



        switch (v.getId()) {

            case R.id.btn1:

                intocan.putExtra("title","Bilgi İşaretleri");
                break;
            case R.id.btn2:

                intocan.putExtra("title","Durma Ve Park Etme İşaretleri");
                break;
            case R.id.btn3:

                intocan.putExtra("title","Tehlike Ve Uyarı İşaretleri");
                break;
            case R.id.btn4:

                intocan.putExtra("title","Trafik İşaretleri");
                break;
            case R.id.btn5:

                intocan.putExtra("title","Trafik Tanzim İşaretleri");
                break;
            case R.id.btn6:

                intocan.putExtra("title","Yatay İşaretler");
                break;
        }

        intocan.putExtras(extras);
        startActivity(intocan);

    }
}
