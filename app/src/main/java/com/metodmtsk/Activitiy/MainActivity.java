package com.metodmtsk.Activitiy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


import com.metodmtsk.Pojo.LinkList;
import com.metodmtsk.Pojo.User;
import com.metodmtsk.R;
import com.metodmtsk.Util.MyApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView notification,video,survey,appointment,logout,sign;

    ArrayList<LinkList> mLinklist;
    private String names;
    private String surnames;
    private String driver_id;
    private int appointment_count;

    private TextView name;

    private ArrayList<LinkList> linkList= new ArrayList<LinkList>();
    private String linkLists;

    private static final String TAG = "MainAct ";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







        Bundle bundle = getIntent().getExtras();
        names = bundle.getString("name");
        surnames = bundle.getString("surname");
        driver_id = bundle.getString("driver_id");
        appointment_count = bundle.getInt("appointment_count");
        linkLists = bundle.getParcelable("linkList");

        name = (TextView) findViewById(R.id.name);

        name.setText(names+"  "+surnames);

        logout = (ImageView)findViewById(R.id.logout);

        logout.setOnClickListener(this);

        notification = (ImageView)findViewById(R.id.notification);
        video = (ImageView)findViewById(R.id.video);
        survey = (ImageView)findViewById(R.id.survey);
        appointment = (ImageView)findViewById(R.id.appointment);
        sign = (ImageView)findViewById(R.id.sign);

        notification.setOnClickListener(this);
        video.setOnClickListener(this);
        survey.setOnClickListener(this);
        appointment.setOnClickListener(this);
        sign.setOnClickListener(this);



    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.logout:
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("driver_id");
                editor.remove("name");
                editor.remove("surname");
                editor.remove("appointment_count");
                editor.remove("mEmail");
                editor.remove("mPassword");
                editor.apply();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                break;

            case R.id.notification:

                Intent intocan = new Intent(MainActivity.this, NotificationActivity.class);
                Bundle extras = new Bundle();
                intocan.putExtra("driver_id",driver_id);
                intocan.putExtras(extras);
                startActivity(intocan);

                break;

            case R.id.video:

                Intent intentVideo = new Intent(MainActivity.this, VideoMainActivity.class);
                startActivity(intentVideo);

                break;

            case R.id.survey:

                Intent intentSurvey = new Intent(MainActivity.this, TestActivity.class);
                Bundle extraSurvey = new Bundle();
                intentSurvey.putExtra("driver_id",driver_id);
                intentSurvey.putExtras(extraSurvey);
                startActivity(intentSurvey);

                break;

            case R.id.appointment:

                Intent intentAppo = new Intent(MainActivity.this, AppointmentActivity.class);
                Bundle extraAppo = new Bundle();
                intentAppo.putExtra("driver_id",driver_id);
                intentAppo.putExtra("appointment_count",appointment_count);
                intentAppo.putExtras(extraAppo);
                startActivity(intentAppo);

                break;
            case R.id.sign:

                Intent intentSign = new Intent(MainActivity.this, SignActivity.class);
                Bundle extraSign = new Bundle();
                intentSign.putExtra("driver_id",driver_id);
                intentSign.putExtra("linkList", linkList);
                intentSign.putExtras(extraSign);
                startActivity(intentSign);

                break;
        }

    }


}
