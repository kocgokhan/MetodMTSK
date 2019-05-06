package com.metodmtsk.Activitiy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.metodmtsk.R;

public class AfterSplash extends AppCompatActivity implements View.OnClickListener{

    ImageView loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);// hide status bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);// hide status bar
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_splash);


        try
        {

            View bView = getWindow().getDecorView();// hide hardware buttons
            bView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);// hide hardware buttons
            this.getSupportActionBar().hide();// hide status bar
        }
        catch (NullPointerException e){}

        loginBtn = (ImageView) findViewById(R.id.login);
        loginBtn.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {

        Intent intentSign = new Intent(AfterSplash.this, LoginActivity.class);
        startActivity(intentSign);

    }

}
