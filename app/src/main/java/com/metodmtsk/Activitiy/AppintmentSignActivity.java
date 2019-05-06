package com.metodmtsk.Activitiy;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.metodmtsk.Pojo.AppointmentClaim;
import com.metodmtsk.Pojo.AppointmentSectionList;
import com.metodmtsk.R;
import com.metodmtsk.Request.AqJSONObjectRequest;
import com.metodmtsk.Util.MyApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

import static com.metodmtsk.Util.StaticFields.BASE_URL;
import static com.metodmtsk.Util.StaticFields.HASH;

public class AppintmentSignActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AppoAct ";


    private String driver_id;
    private String vehicle_id;
    private String section;

    private ImageView button;

    ArrayList<String> items = new ArrayList<>();
    ArrayList<String> teach = new ArrayList<>();
    SpinnerDialog spinnerDialog;
    SpinnerDialog spinnerDialog2;
    private TextView selectedTeach;
    private TextView selectedItems;

    private String selectedDateStr;

    private HorizontalCalendar horizontalCalendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appintment_sign);


        Bundle bundle = getIntent().getExtras();
        driver_id = bundle.getString("driver_id");

        vehicle_id="1";

        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#273D52\" >" + getString(R.string.appointmentTitle) + "</font>")));


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_toolbar_arrow);


        //btnShowCalendar=(ImageView) findViewById(R.id.btnShowCalendar);
        //btnShowCalendar.setOnClickListener(this);



        /* start 2 months ago from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DAY_OF_WEEK, -1);

        /* end after 2 months from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DAY_OF_WEEK, 7);

        // Default Date set to Today.
        final Calendar defaultSelectedDate = Calendar.getInstance();

        horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .configure()
                .formatTopText("MM")
                .formatMiddleText("dd")
                .showTopText(true)
                .showBottomText(true)
                .textColor(Color.LTGRAY, Color.BLACK)
                .end()
                .defaultSelectedDate(defaultSelectedDate)

                .build();

        Log.i("Default Date", DateFormat.format("yyyy, MM ,dd ", defaultSelectedDate).toString());

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                selectedDateStr = DateFormat.format("yyyy-MM-dd ", date).toString();

                 requestClaim(selectedDateStr);



            }

        });


        //horizontalCalendar.refresh();

        selectedItems = (TextView) findViewById(R.id.txt);






        spinnerDialog = new SpinnerDialog(AppintmentSignActivity.this, items,
                "Kurs Seansı Seçiniz");

        spinnerDialog.setCancellable(true);
        spinnerDialog.setShowKeyboard(false);

        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                selectedItems.setText(item);
                section=item;
            }
        });

        findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog.showSpinerDialog();
            }
        });


        selectedTeach = (TextView) findViewById(R.id.text);

        teach.add("Gökhan KOÇ");
        teach.add("Mete Göksu Çelik");
        teach.add("Saaadiye Hacıoğlu");
        spinnerDialog2 = new SpinnerDialog(AppintmentSignActivity.this, teach,
                "Kurs Seansı Seçiniz");

        spinnerDialog2.setCancellable(true);
        spinnerDialog2.setShowKeyboard(false);

        spinnerDialog2.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                selectedTeach.setText(item);

            }
        });

        findViewById(R.id.show2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog2.showSpinerDialog();
            }
        });


        button = (ImageView) findViewById(R.id.button);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                requestJson(driver_id,selectedDateStr,section,vehicle_id);


            }
        });




    }

    @Override
    public void onClick(View v) {


    }


    private void requestJson(String driver_id, String selectedDateStr, String section, String vehicle_id) {
        JSONObject params = new JSONObject();
        try {
            params.put("driver_id", driver_id);
            params.put("course_time", selectedDateStr);
            params.put("course_section", section);
            params.put("vehicle_id", vehicle_id);
            params.put("hash", HASH);

            Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.wtf(TAG, "onResponse : " + response);

                 /* SharedPreferences.Editor editor = MyApplication.get().getPreferencesEditor();
                    editor.putString("loginResponse", response + "");
                    editor.apply();*/

                    Intent intent = new Intent(AppintmentSignActivity.this, AppointmentResultActivity.class);
                    Bundle extras = new Bundle();


                        intent.putExtra("result", "true");



                    intent.putExtras(extras);
                    startActivity(intent);
                    finish();


                }
            };

            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.wtf(TAG, "onErrorResponse : " + error);
                    Intent intent = new Intent(AppintmentSignActivity.this, AppointmentResultActivity.class);
                    Bundle extras = new Bundle();

                        intent.putExtra("result", "false");



                    intent.putExtras(extras);
                    startActivity(intent);
                    finish();
                }
            };

            AqJSONObjectRequest aqJSONObjectRequest = new AqJSONObjectRequest(TAG,BASE_URL + "appointmentAction", params, listener, errorListener);
            MyApplication.get().getRequestQueue().add(aqJSONObjectRequest);
        } catch (JSONException e) {
            Log.wtf(TAG, "request params catch e.getMessage() : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void requestClaim(String course_time) {
         ArrayList<AppointmentSectionList> sectionArrayList = new ArrayList<>();
        JSONObject params = new JSONObject();
        try {
            params.put("course_time", course_time);
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
                            AppointmentSectionList appointmentSectionList = new AppointmentSectionList(jsonObject, false);
                            sectionArrayList.add(appointmentSectionList);

                        }
                        drawSection(sectionArrayList);

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

            AqJSONObjectRequest aqJSONObjectRequest = new AqJSONObjectRequest(TAG,BASE_URL + "appointmentSectionList", params, listener, errorListener);
            MyApplication.get().getRequestQueue().add(aqJSONObjectRequest);
        } catch (JSONException e) {
            Log.wtf(TAG, "request params catch e.getMessage() : " + e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }
    public void drawSection(ArrayList<AppointmentSectionList> list) {

        ArrayList<String> myArray = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            myArray.add( list.get(i).getCourse_section());
        }

        if(list.size() !=0){

            if(!myArray.contains("Sabah")) {

                items.add("Sabah");
            }
            if(!myArray.contains("Akşam")) {
                items.add("Akşam");
            }

            if(!myArray.contains("Öğle")) {

                items.add("Öğle");
            }
        }

        else {

            items.add("Sabah");
            items.add("Öğle");
            items.add("Akşam");
        }
    }
}
