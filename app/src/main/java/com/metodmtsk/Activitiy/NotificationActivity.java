package com.metodmtsk.Activitiy;

import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.metodmtsk.Adapter.NotificationAdapter;
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

public class NotificationActivity extends AppCompatActivity {
    private static final String TAG = "NotifyAct ";
    private String driver_id;
    private TextView title;
    private TextView date;
    private TextView message;


    private RequestQueue requestQueue;

    private String titles;
    private String dates;
    private String messages;
     PrettyDialog dialog;

    ArrayList<String> mylist;
    RecyclerView recyclerView;

    AppCompatButton btn_titleMessage, btn_okCancel, btn_allCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);



        Bundle bundle = getIntent().getExtras();
        driver_id = bundle.getString("driver_id");

        title = findViewById(R.id.title);
        message = findViewById(R.id.message);


        recyclerView = (RecyclerView) findViewById(R.id.recylerview);
        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#273D52\" >" + getString(R.string.notificationTitle) + "</font>")));


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_toolbar_arrow);




        String loginResponse = MyApplication.get().getPreferences().getString("loginResponse", null);
        if (loginResponse != null) {
            try {
                JSONObject jsonObject = new JSONObject(loginResponse);
                User user = new User(jsonObject, true);
                requestJsons(String.valueOf(user.getDriverID()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private void requestJsons(String userid) {
        ArrayList<Notification> notificationArrayList = new ArrayList<>();
        JSONObject params = new JSONObject();
        try {
            params.put("userid", userid);
            params.put("hash", HASH);
            params.put("aqGokhan", 1);

            Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.wtf(TAG, "onResponse : " + response);

                    JSONArray jsonArray = null;
                    try {
                        jsonArray = response.getJSONArray("aqArrayi");


                        JSONObject jsonObject;



                        for (int i = 0; i < jsonArray.length(); i++) {
                            jsonObject = jsonArray.getJSONObject(i);
                            Notification notification = new Notification(jsonObject, false);
                            notificationArrayList.add(notification);


                        }
                            if (notificationArrayList.get(0).isNotificationData()==false){

                                setup();

                            }else{ drawCart(notificationArrayList);}




                        MyApplication.get().getRequestQueue().getCache().clear();
                    } catch (JSONException e) {
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

            AqJSONObjectRequest aqJSONObjectRequest = new AqJSONObjectRequest(TAG,BASE_URL + "notification", params, listener, errorListener);
            MyApplication.get().getRequestQueue().add(aqJSONObjectRequest);
        } catch (JSONException e) {
            Log.wtf(TAG, "request params catch e.getMessage() : " + e.getMessage());
            e.printStackTrace();
        }
    }



    public void drawCart(ArrayList<Notification> list){





            NotificationAdapter notificationAdapter = new NotificationAdapter(this, Notification.getData(list));
            recyclerView.setAdapter(notificationAdapter);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);

            Log.wtf("notification", String.valueOf(list));








    }
    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();

        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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

                .setTitle("Bildirimler")
                .setTitleColor(R.color.pdlg_color_blue)
                .setAnimationEnabled(true)
                .setMessage("Henüz bildiriminiz bulunmamaktadır.")
                .setMessageColor(R.color.pdlg_color_gray)
                .setTypeface(Typeface.createFromAsset(getResources().getAssets(),"myfont.ttf"));
                dialog.show();
    }


}
