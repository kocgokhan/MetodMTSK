package com.metodmtsk.Activitiy;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;

import com.metodmtsk.R;

public class VideoMainActivity  extends AppCompatActivity implements View.OnClickListener {

    ImageView btn1;
    ImageView btn2;
    ImageView btn3;
    ImageView btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_main);


        btn1 = (ImageView) findViewById(R.id.btn1);
        btn2 = (ImageView) findViewById(R.id.btn2);
        btn3 = (ImageView) findViewById(R.id.btn3);
        btn4 = (ImageView) findViewById(R.id.btn4);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);


        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#273D52\" >" + getString(R.string.videotTitle) + "</font>")));


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_toolbar_arrow);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public void onClick(View v) {


        Intent intocan = new Intent(VideoMainActivity.this, VideoActivity.class);
        Bundle extras = new Bundle();
        switch (v.getId()) {

            case R.id.btn1:

                intocan.putExtra("title","Ilk Yardim");
                break;
            case R.id.btn2:

                intocan.putExtra("title","Trafik");
                break;
            case R.id.btn3:

                intocan.putExtra("title","Motor");
                break;
            case R.id.btn4:

                intocan.putExtra("title","TrafikAdabi");
                break;
        }

        intocan.putExtras(extras);
        startActivity(intocan);
    }
}
