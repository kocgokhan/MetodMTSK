package com.metodmtsk.Activitiy;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.metodmtsk.Adapter.AppointmentAdapter;
import com.metodmtsk.Adapter.NotificationAdapter;
import com.metodmtsk.Pojo.Appointment;
import com.metodmtsk.Pojo.AppointmentClaim;
import com.metodmtsk.Pojo.AppointmentDetail;
import com.metodmtsk.Pojo.Notification;
import com.metodmtsk.Pojo.User;
import com.metodmtsk.R;
import com.metodmtsk.Request.AqJSONObjectRequest;
import com.metodmtsk.Util.MyApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;

import static com.metodmtsk.Util.StaticFields.BASE_URL;
import static com.metodmtsk.Util.StaticFields.HASH;

public class AppointmentActivity extends AppCompatActivity  implements View.OnClickListener{
    private static final String TAG = "AppoAct ";
    private String driver_id;
   // private String count;
   private TextView counts;
    ImageView appoBtn;
    private ArrayList<AppointmentClaim> appointmentsArrayList = new ArrayList<>();
    private ArrayList<User> usr = new ArrayList<>();


    PrettyDialog dialog;


    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);


        Bundle bundle = getIntent().getExtras();
        driver_id = bundle.getString("driver_id");


        recyclerView = (RecyclerView) findViewById(R.id.recylerview);

        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#273D52\" >" + getString(R.string.appointmentTitle) + "</font>")));


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_toolbar_arrow);

       counts = (TextView) findViewById(R.id.count);






        appoBtn=(ImageView)findViewById(R.id.appoBtn);
        appoBtn.setOnClickListener(this);



/*if (appointment_count==0){

    appoBtn.setVisibility(View.INVISIBLE);
}
else{
    appoBtn.setVisibility(View.VISIBLE);
}*/


        String loginResponse = MyApplication.get().getPreferences().getString("loginResponse", null);
        if (loginResponse != null) {
            try {
                JSONObject jsonObject = new JSONObject(loginResponse);
                User user = new User(jsonObject, true);
                requestJson(String.valueOf(user.getDriverID()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private void requestJson(String userid) {
        JSONObject params = new JSONObject();
        try {
            params.put("driver_id", userid);
            params.put("hash", HASH);
            params.put("aqGokhan", 1);

            Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.wtf(TAG, "onResponse : " + response);
                    try {
                        JSONArray jsonArray = response.getJSONArray("aqArrayi");
                        JSONObject jsonObject;
                        for (int i = 0; i < jsonArray.length(); i++) {
                            jsonObject = jsonArray.getJSONObject(i);
                            AppointmentClaim appointmentClaim = new AppointmentClaim(jsonObject);
                            appointmentsArrayList.add(appointmentClaim);

                            counts.setText(appointmentClaim.getAppointment_count());
                        }

                        if (appointmentsArrayList.get(0).getClaimEqual()==false){

                            setup();

                        }else{ drawCart(appointmentsArrayList);}

                        MyApplication.get().getRequestQueue().getCache().clear();

                    } catch (JSONException e) {
                        Log.wtf(TAG, "response catch e.getMessage() : " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            };

            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.wtf(TAG, "onErrorResponse : " + error);
                }
            };

            AqJSONObjectRequest aqJSONObjectRequest = new AqJSONObjectRequest(TAG, BASE_URL + "appointmentClaim", params, listener, errorListener);
            MyApplication.get().getRequestQueue().add(aqJSONObjectRequest);
        } catch (JSONException e) {
            Log.wtf(TAG, "request params catch e.getMessage() : " + e.getMessage());
            e.printStackTrace();
        }
    }



    public void drawCart(ArrayList<AppointmentClaim> list) {


        AppointmentAdapter appointmentDetail = new AppointmentAdapter(this, AppointmentClaim.getDriverAppintmentList(list));
        recyclerView.setAdapter(appointmentDetail);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);



        Log.wtf("appointment", String.valueOf(list));

    }

    public void drawCount(ArrayList<AppointmentClaim> list) {

        try {










                // quey1 = list.get(i).getIdentifier();








        } catch (Exception e) {
            Log.wtf(TAG, e);
        }


    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.appoBtn:

                Intent intocan = new Intent(AppointmentActivity.this, AppintmentSignActivity.class);
                Bundle extras = new Bundle();
                intocan.putExtra("driver_id",driver_id);
                intocan.putExtras(extras);
                startActivity(intocan);
                finish();
                break;

        }

    }

    private void setup(){
        setup_titleMessageDialog();

    }

    private void setup_titleMessageDialog(){
        dialog = new PrettyDialog(this)
                .setIcon(
                        R.drawable.pdlg_icon_info,    // Icon resource
                        R.color.pdlg_color_green,      // Icon tint
                        new PrettyDialogCallback() {  // Icon OnClick listener
                            @Override
                            public void onClick() {
                                // Do what you gotta do
                            }
                        })
                .setTitle("PrettyDialog Title")
                .setMessage("PrettyDialog Message")
                .addButton(
                        "OK",
                        R.color.pdlg_color_white,
                        R.color.pdlg_color_green,
                        new PrettyDialogCallback() {
                            @Override
                            public void onClick() {
                                onBackPressed();
                                finish();
                            }
                        }
                )

                .setTitle("Randevular")
                .setTitleColor(R.color.pdlg_color_blue)
                .setAnimationEnabled(true)
                .setMessage("Henüz randevunuz bulunmamaktadır.")
                .setMessageColor(R.color.pdlg_color_gray)
                .setTypeface(Typeface.createFromAsset(getResources().getAssets(),"myfont.ttf"));
        dialog.show();
    }

}
