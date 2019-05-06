package com.metodmtsk.Activitiy;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.metodmtsk.R;
import com.onesignal.OneSignal;

import me.wangyuwei.particleview.ParticleView;

public class SplashScreenActivity extends AppCompatActivity {

    private ParticleView mPv1;


    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// hide status bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);// hide status bar
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        View bView = getWindow().getDecorView();// hide hardware buttons
        bView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);// hide hardware buttons

        try
        {
            this.getSupportActionBar().hide();// hide status bar
        }
        catch (NullPointerException e){}


        mPv1 = (ParticleView) findViewById(R.id.pv_1);

        mPv1.setOnParticleAnimListener(new ParticleView.ParticleAnimListener() {
            @Override
            public void onAnimationEnd() {
                Intent intent = new Intent(SplashScreenActivity.this, AfterSplash.class);
                SplashScreenActivity.this.startActivity(intent);
                finish();
            }
        });

        mPv1.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPv1.startAnim();
            }
        }, 200);


    }
}
